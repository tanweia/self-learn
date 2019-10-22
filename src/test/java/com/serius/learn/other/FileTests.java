package com.serius.learn.other;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class FileTests {
	
	@Test
	public void fileSizeTest() throws IOException {
		FileInputStream fis = new FileInputStream("test.java");
		System.out.println(fis.read());
		fis.close();
	}
	
	@Test
	public void tmpDirTest() {
		File file = new File(FileUtils.getTempDirectory() + File.separator + "test.txt");
		System.out.println(file.getName());
		System.out.println(file.getPath());
	}
}
