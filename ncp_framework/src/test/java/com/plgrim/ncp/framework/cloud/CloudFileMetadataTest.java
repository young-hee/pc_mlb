package com.plgrim.ncp.framework.cloud;

import static org.junit.Assert.*;

import org.junit.Test;

public class CloudFileMetadataTest {

	@Test
	public void testGetPath() {
		CloudFileMetadata metadata = new CloudFileMetadata();
		metadata.setUri("s3://bucketName:1/2/3/file.txt");
		assertEquals(metadata.getPath(), "1/2/3/file.txt");
		
		metadata.setUri("bucketName:1/2/3/file.txt");
		assertEquals(metadata.getPath(), "1/2/3/file.txt");
		
		metadata.setUri("1/2/3/file.txt");
		assertEquals(metadata.getPath(), "1/2/3/file.txt");
	}

}
