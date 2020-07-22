package com.plgrim.ncp.framework.cloud.aws;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.plgrim.ncp.framework.cloud.CloudFileMetadata;
import com.plgrim.ncp.framework.cloud.CloudFileSystem;
import com.plgrim.ncp.framework.utils.MimeTypes;

import lombok.extern.slf4j.Slf4j;

/**
 * S3 implementation of CloudFileSystem
 *
 * @author Park Chulhui <charles@plgrim.com>
 *
 */
@Component
@Slf4j
public class S3FileSystem implements CloudFileSystem {
	public static final String URI_SCHEME = "s3://";

    private static final char PATH_DELIMETER = '/';

	public static S3FileInfo convert(final String cloudPath) {
		String path = cloudPath;
		
		if (path.startsWith(URI_SCHEME)) {
			path = cloudPath.substring(URI_SCHEME.length());
		}
		int idx = path.indexOf(':');
		if (idx == 0) {
			throw new IllegalArgumentException(
					"invalud s3 cloudPath cloudPath[" + path + "]. valid format($bucketName:$fullpath)");
		}

		S3FileInfo s3File = new S3FileInfo();
		s3File.setBucketName(path.substring(0, idx));
		String filePath = path.substring(idx + 1, path.length());
		if (filePath.charAt(0) == PATH_DELIMETER) {
			filePath = filePath.substring(1, filePath.length());
		}
		s3File.setPath(filePath);

		return s3File;
	}

	@Override
	public void store(String filename, String cloudPath) throws IOException {
		store(filename, cloudPath, Permission.PRIVATE);
	}

	@Override
	public void store(String filename, String cloudPath, Permission permission) throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			throw new IOException("filename[" + filename + "] doesn't exist in local file");
		}
		store(file, cloudPath, permission);
	}

	@Override
	public void store(File file, String cloudPath) throws IOException {
		store(file, cloudPath, Permission.PRIVATE);
	}

	@Override
	public void store(File file, String cloudPath, Permission permission) throws IOException {
		String filename = file.getName();
		String cloudPathForDirectory = cloudPath;
		if (cloudPath.charAt(cloudPath.length() - 1) != PATH_DELIMETER) {
			int idx = cloudPath.lastIndexOf(PATH_DELIMETER);
			cloudPathForDirectory = cloudPath.substring(0, idx - 1);
			filename = cloudPath.substring(idx, cloudPath.length());
		}

		try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
			store(in, file.length(), cloudPathForDirectory, filename, permission);
		}
	}

	@Override
	public void store(InputStream input, long sourceFileSize, String cloudPathForDirectory, String filename)
			throws IOException {
		store(input, sourceFileSize, cloudPathForDirectory, filename, Permission.PRIVATE);
	}

	@Override
	public void store(InputStream input, long sourceFileSize, String cloudPathForDirectory, String filename,
			Permission permission) throws IOException {
		InputStream in = input;
		if (!(in instanceof BufferedInputStream)) {
			in = new BufferedInputStream(in);
		}
		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

		// validate cloudPath format
		if (cloudPathForDirectory.charAt(cloudPathForDirectory.length() - 1) != PATH_DELIMETER) {
			throw new IllegalArgumentException(
					"invalid cloudPath[" + cloudPathForDirectory + "]: must be ends with '/' character");
		}

		S3FileInfo s3FileInfo = convert(cloudPathForDirectory + filename);

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(sourceFileSize);
		String contentType = MimeTypes.getMimeType(FilenameUtils.getExtension(filename));
		objectMetadata.setContentType(contentType);
		PutObjectRequest poreq = new PutObjectRequest(s3FileInfo.getBucketName(), s3FileInfo.getPath(), in, objectMetadata);

		if (permission == Permission.PUBLIC) {
			poreq.setCannedAcl(CannedAccessControlList.PublicRead);
		}
		s3client.putObject(poreq);
	}

	@Override
	public void copy(String sourcePath, String targetPath) {
		copy(sourcePath, targetPath, Permission.PRIVATE);
	}

	@Override
	public void copy(String sourcePath, String targetPath, Permission permission) {
		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

		S3FileInfo sourceS3 = convert(sourcePath);
		S3FileInfo targetS3 = convert(targetPath);
		CopyObjectRequest copyObjectRequest = new CopyObjectRequest(sourceS3.getBucketName(), sourceS3.getPath(),
				targetS3.getBucketName(), targetS3.getPath());
		if (permission == Permission.PUBLIC) {
			copyObjectRequest.setCannedAccessControlList(CannedAccessControlList.PublicRead);
		}
		s3client.copyObject(copyObjectRequest);
	}

	@Override
	public void move(String sourcePath, String targetPath) {
		move(sourcePath, targetPath, Permission.PRIVATE);
	}

	@Override
	public void move(String sourcePath, String targetPath, Permission permission) {
		copy(sourcePath, targetPath, permission);
		delete(sourcePath);
	}

	@Override
	public void delete(String sourcePath) {
		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

		S3FileInfo sourceS3 = convert(sourcePath);
		s3client.deleteObject(sourceS3.getBucketName(), sourceS3.getPath());
	}

	@Override
	public void deleteFolder(String sourcePath) {
		Collection<CloudFileMetadata> keyList = this.getListObjects(sourcePath);

		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
		S3FileInfo sourceS3 = convert(sourcePath);

		for (CloudFileMetadata objectSummary : keyList) {
			s3client.deleteObject(sourceS3.getBucketName(), objectSummary.getPath());
		}
	}
	
	private CloudFileMetadata toCloudFileMetadata(S3ObjectSummary summary) {
		CloudFileMetadata metadata = new CloudFileMetadata();
		metadata.setUri(summary.getBucketName()+":"+summary.getKey());
		metadata.setLastModified(summary.getLastModified().getTime());
		metadata.setLength(summary.getSize());
		
		return metadata;
	}

	@Override
	public Collection<CloudFileMetadata> getListObjects(String sourcePath){
		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
		S3FileInfo sourceS3 = convert(sourcePath);

		LinkedList<CloudFileMetadata> metadatas = new LinkedList<>();
		String bucketName = sourceS3.getBucketName();

		if (!s3client.doesBucketExistV2(bucketName)) {
			return metadatas;
		}
		
		// s3에서 지정한 폴더의 파일을 한꺼번에 모두 가져오지 못하고 최대 1,000건으로 제한되어 있음.
		// isTruncated는 folder에 아직 listing한 파일이 있는지 여부를 체크.
		ObjectListing objects = s3client.listObjects(bucketName, sourceS3.getPath());
		addToCollection(metadatas, objects);

		
		while (objects.isTruncated()) {
			objects = s3client.listNextBatchOfObjects(objects);
			addToCollection(metadatas, objects);
		}

		return metadatas;
	}

	private void addToCollection(Collection<CloudFileMetadata> metadatas, ObjectListing listing) {
		List<S3ObjectSummary> objectSummaries = listing.getObjectSummaries();
		for (S3ObjectSummary summary : objectSummaries) {
			metadatas.add(toCloudFileMetadata(summary));
		}
	}


	@Override
	public CloudFileMetadata getFileInformation(String cloudFilePath) {
		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

		S3FileInfo sourceS3 = convert(cloudFilePath);
		S3Object s3Object = s3client.getObject(sourceS3.getBucketName(), sourceS3.getPath());
		ObjectMetadata s3Meta = s3Object.getObjectMetadata();

		CloudFileMetadata cloudMeta = new CloudFileMetadata();
		cloudMeta.setContentType(s3Meta.getContentType());
		cloudMeta.setContentDisposition(s3Meta.getContentDisposition());
		cloudMeta.setLength(s3Meta.getContentLength());
		cloudMeta.setLastModified(s3Meta.getLastModified().getTime());

		return cloudMeta;
	}

	@Override
	public InputStream getInputStream(String cloudFilePath) {
		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

		S3FileInfo sourceS3 = convert(cloudFilePath);
		S3Object s3Object = s3client.getObject(sourceS3.getBucketName(), sourceS3.getPath());
		InputStream in = s3Object.getObjectContent();
		if (!(in instanceof BufferedInputStream)) {
			return new BufferedInputStream(in);
		}
		return in;
	}

	@Override
	public void storeToLocal(String sourceCloudPath, String targetLocalPath) throws IOException {
		File file = new File(targetLocalPath);
		if (!file.getParentFile().exists()) {
			file.mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		try (InputStream in = getInputStream(sourceCloudPath);
				OutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
			IOUtils.copyLarge(in, out);
		}
	}

    @Override
    public void unzip(String sourcePath, String targetPath) throws Exception {
		this.unzip(sourcePath, targetPath, Permission.PRIVATE);
	}

	@Override
	public void unzip(String sourcePath, String targetPath, Permission permission) throws Exception {
		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

		S3FileInfo sourceS3 = convert(sourcePath);
		S3Object s3Object = s3client.getObject(sourceS3.getBucketName(), sourceS3.getPath());

		/**
		 * 한글 Encoding을 위해 EUC-KR 사용
		 */
		ZipInputStream in = new ZipInputStream(s3Object.getObjectContent(), Charset.forName("EUC-KR"));
		ZipEntry entry;

		try {
			while ((entry = in.getNextEntry()) != null) {
				this.store(new CloseIgnoreBufferedInputStream(in), entry.getSize(), targetPath, entry.getName(), permission);
			}
		} catch (Exception e) {
			log.error("{} unzip Exception {}", this.getClass().getSimpleName(), e.getStackTrace());
			throw e;
		} finally {
			in.closeEntry();
			in.close();
		}
	}

	@Override
	public String getUrl(String bucketName, String key) {
		AmazonS3Client s3client = (AmazonS3Client) AmazonS3ClientBuilder.defaultClient();
		return s3client.getUrl(bucketName, key).toExternalForm();
	}

}
