package com.plgrim.ncp.framework.commons;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class IdGenServiceTest {
	
	@Test
	public void test() throws Exception {
		for (String origin : FileUtils.readLines(new File("src.txt"))) {
			System.out.println(IdGenService.generateSHA256(origin));
		}
	}

}
