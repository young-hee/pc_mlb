package com.plgrim.ncp.framework.cloud.aws;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.Assert;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;

import lombok.extern.slf4j.Slf4j;

/**
 * AWS의 S3를 spring의 FileSystemResource와 같이 사용할 수 있도록 한 implementation 
 * 
 * @author Park Chulhui <charles@plgrim.com>
 *
 */
@Slf4j
public class S3FileSystemResource implements WritableResource {
	private AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
	
	private String cloudPath;
	private S3FileInfo s3FileInfo;
	private S3Object s3Object;
	/**
	 * cloudPath에는 $bucketName:$path format으로 정의되어야 한다.
	 * 
	 * @param cloudPath full path of s3 file system
	 */
	public S3FileSystemResource(String cloudPath) {
		Assert.notNull(cloudPath, "cloudRootPath must not be null");
		this.cloudPath = cloudPath;
		this.s3FileInfo = S3FileSystem.convert(this.cloudPath);
		if (this.cloudPath.charAt(this.cloudPath.length()-1) == '/') {
			this.s3Object = s3client.getObject(s3FileInfo.getBucketName(), s3FileInfo.getPath());
		}
	}
	
	@Override
	public boolean exists() {
		return s3client.doesObjectExist(s3FileInfo.getBucketName(), s3FileInfo.getPath());
	}

	@Override
	public boolean isReadable() {
		if (this.s3Object == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public URL getURL() throws IOException {
		return s3client.getUrl(s3FileInfo.getBucketName(), s3FileInfo.getPath());
	}

	@Override
	public URI getURI() throws IOException {
		try {
			return getURL().toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public File getFile() throws IOException {
		throw new UnsupportedOperationException("getFile() method can't support in S3FileSystemResource.");
	}

	@Override
	public long contentLength() throws IOException {
		return this.s3Object.getObjectMetadata().getContentLength();
	}

	@Override
	public long lastModified() throws IOException {
		return this.s3Object.getObjectMetadata().getLastModified().getTime();
	}

	@Override
	public Resource createRelative(String relativePath) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFilename() {
		int idx = this.cloudPath.lastIndexOf('/');
		if (idx == 0) {
			return this.cloudPath;
		} else {
			return this.cloudPath.substring(idx+1, this.cloudPath.length());
		}
	}

	@Override
	public String getDescription() {
		if (this.s3Object == null) {
			return this.cloudPath;
		} else {
			try {
				return getURL().toString();
			} catch (Exception e) {
				log.warn(e.getMessage(), e);
				return this.cloudPath;
			}
		}
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream in = s3Object.getObjectContent();
		if (!(in instanceof BufferedInputStream)) {
			return new BufferedInputStream(in);
		}
		return in;
	}

	@Override
	public boolean isWritable() {
		return true;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return null;
	}
	

}
