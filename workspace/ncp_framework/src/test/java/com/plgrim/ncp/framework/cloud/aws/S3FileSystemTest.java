package com.plgrim.ncp.framework.cloud.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.plgrim.ncp.framework.TestConfiguration;
import com.plgrim.ncp.framework.cloud.CloudFileMetadata;
import com.plgrim.ncp.framework.cloud.CloudFileSystem;
import com.plgrim.ncp.framework.cloud.CloudFileSystem.Permission;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { TestConfiguration.class })
@ActiveProfiles("local")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class S3FileSystemTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private CloudFileSystem cloudFileSystem;

	private static String bucketName = "dev-static-online-mall";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void oneTimeTearDown() {
		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

		for (S3ObjectSummary file : s3client.listObjects(bucketName, "test/images/").getObjectSummaries()) {
			s3client.deleteObject(bucketName, file.getKey());
		}
		for (S3ObjectSummary file : s3client.listObjects(bucketName, "test/copy/").getObjectSummaries()) {
			s3client.deleteObject(bucketName, file.getKey());
		}
		for (S3ObjectSummary file : s3client.listObjects(bucketName, "test/move/").getObjectSummaries()) {
			s3client.deleteObject(bucketName, file.getKey());
		}
	}
	
	@Test
	public void test01StoreForPrivate() throws IOException {
		Resource resource = applicationContext
				.getResource("classpath:/com/plgrim/ncp/framework/cloud/TEST-PRD-001.png");
		File file = resource.getFile();
		String sourceFile = file.getAbsolutePath();

		String targetFile = bucketName + ":/test/images/";

		cloudFileSystem.store(sourceFile, targetFile);

		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
		S3Object s3Object = s3client.getObject(bucketName, "test/images/TEST-PRD-001.png");
		assertEquals("image/png", s3Object.getObjectMetadata().getContentType());
		assertEquals(file.length(), s3Object.getObjectMetadata().getContentLength());

		String location = s3client.getUrl(bucketName, "test/images/TEST-PRD-001.png").toExternalForm();
		URL url = new URL(location);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		int statusCode = http.getResponseCode();
		assertNotEquals(200, statusCode);
	}

	@Test
	public void test02StoreForPublic() throws IOException {
		Resource resource = applicationContext
				.getResource("classpath:/com/plgrim/ncp/framework/cloud/TEST-PRD-002.png");
		File file = resource.getFile();
		String sourceFile = file.getAbsolutePath();

		String targetFile = bucketName + ":/test/images/";

		cloudFileSystem.store(sourceFile, targetFile, CloudFileSystem.Permission.PUBLIC);

		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
		S3Object s3Object = s3client.getObject(bucketName, "test/images/TEST-PRD-002.png");
		assertEquals("image/png", s3Object.getObjectMetadata().getContentType());
		assertEquals(file.length(), s3Object.getObjectMetadata().getContentLength());

		String location = s3client.getUrl(bucketName, "test/images/TEST-PRD-002.png").toExternalForm();
		URL url = new URL(location);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		int statusCode = http.getResponseCode();
		assertEquals(200, statusCode);
		assertEquals("image/png", http.getContentType());
		assertEquals(file.length(), http.getContentLength());
	}

	@Test
	public void test03CopyForPrivate() throws IOException {
		String sourcePath = bucketName + ":test/images/TEST-PRD-001.png";
		String targetPath = bucketName + ":test/copy/TEST-COPY-001.png";
		cloudFileSystem.copy(sourcePath, targetPath);

		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
		S3Object s3Object = s3client.getObject(bucketName, "test/copy/TEST-COPY-001.png");
		assertEquals("image/png", s3Object.getObjectMetadata().getContentType());

		String location = s3client.getUrl(bucketName, "test/copy/TEST-COPY-001.png").toExternalForm();
		URL url = new URL(location);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		int statusCode = http.getResponseCode();
		assertNotEquals(200, statusCode);
	}

	@Test
	public void test03CopyForPublic() throws IOException {
		String sourcePath = bucketName + ":test/images/TEST-PRD-002.png";
		String targetPath = bucketName + ":test/copy/TEST-COPY-002.png";
		cloudFileSystem.copy(sourcePath, targetPath, Permission.PUBLIC);

		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
		S3Object s3Object = s3client.getObject(bucketName, "test/copy/TEST-COPY-002.png");
		assertEquals("image/png", s3Object.getObjectMetadata().getContentType());

		String location = s3client.getUrl(bucketName, "test/copy/TEST-COPY-002.png").toExternalForm();
		URL url = new URL(location);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		int statusCode = http.getResponseCode();
		assertEquals(200, statusCode);
	}

	@Test
	public void test04Move() throws IOException {
		String sourcePath = bucketName + ":test/copy/TEST-COPY-002.png";
		String targetPath = bucketName + ":test/move/TEST-COPY-002.png";
		cloudFileSystem.move(sourcePath, targetPath);

		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();

		String location = s3client.getUrl(bucketName, "test/copy/TEST-COPY-002.png").toExternalForm();
		URL url = new URL(location);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		int statusCode = http.getResponseCode();
		assertEquals(403, statusCode);

		thrown.expect(AmazonS3Exception.class);
		s3client.getObject(bucketName, "test/copy/TEST-COPY-002.png");
	}

	@Test
	public void test05Delete() {
		cloudFileSystem.delete(bucketName + ":test/move/TEST-COPY-002.png");

		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
		thrown.expect(AmazonS3Exception.class);
		s3client.getObject(bucketName, "test/move/TEST-COPY-002.png");
	}

	@Test
	public void tes() throws IOException {
		Resource resource = applicationContext
				.getResource("classpath:/com/plgrim/ncp/framework/cloud/TEST-PRD-002.png");
		File file = resource.getFile();

		String cloudFilePath = bucketName + ":test/images/TEST-PRD-002.png";
		CloudFileMetadata meta = cloudFileSystem.getFileInformation(cloudFilePath);
		assertEquals(file.length(), meta.getLength());
		assertEquals("image/png", meta.getContentType());
		assertNotEquals(file.lastModified(), meta.getLastModified());
	}

	@Test
	public void testStoreToLocal() throws IOException {
		String sourceCloudPath = bucketName + ":test/images/TEST-PRD-001.png";
		File file = File.createTempFile("test", "png");
		cloudFileSystem.storeToLocal(sourceCloudPath, file.getAbsolutePath());

		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
		S3Object s3Object = s3client.getObject(bucketName, "test/images/TEST-PRD-001.png");

		assertEquals(s3Object.getObjectMetadata().getContentLength(), file.length());
	}

	@Test
	public void testStoreZip() throws Exception {
		Resource resource = applicationContext.getResource("classpath:/com/plgrim/ncp/framework/cloud/Product_Image_sample (2018_05_17 01_22_06 UTC).zip");
		File file = resource.getFile();
		String targetFile = bucketName + ":/pec/temp/zip/";

		cloudFileSystem.store(file.getAbsoluteFile(), targetFile, CloudFileSystem.Permission.PUBLIC);
	}

	@Test
	public void testUnzipZip() throws Exception {
		String targetFile = bucketName + ":/pec/temp/zip/";
		String sourceFile = bucketName + ":" + "/pec/temp/Product_Image_sample (2018_05_17 01_22_06 UTC).zip";
		S3FileSystem s3FileSystem = new S3FileSystem();

		s3FileSystem.unzip(sourceFile, targetFile, CloudFileSystem.Permission.PUBLIC);

        testGetObjectList();
	}

	@Test
	public void testDeleteFolder() throws Exception {

		String sourceFolder = bucketName + ":/pec/temp/zip/";

		cloudFileSystem.deleteFolder(sourceFolder);

	}

	@Test
	public void testGetObjectList() throws Exception {

		String sourceFile = bucketName + ":/pec/temp/zip/";

		Collection<CloudFileMetadata> listObjects = cloudFileSystem.getListObjects(sourceFile);

		log.info("listObjects size {}", listObjects.size());
		log.info("listObjects {}", listObjects);

	}

}
