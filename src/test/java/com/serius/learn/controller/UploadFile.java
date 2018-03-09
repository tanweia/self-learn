package com.serius.learn.controller;

import org.junit.Test;
/*import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.RequestBuilder;

import com.serius.learn.utils.FileUtil;*/

public class UploadFile {
	
	@Test
	public void uploadMaterialFileTest() throws Exception {
		/*String api_path = "/adv/creative/uploadMaterialFile";
		File originFile = new File("E:/工作资料/TG/0228/test/素材.zip");
		MockMultipartFile file = new MockMultipartFile("materialFile", "素材.zip" , null, FileUtil.file2byteByNio(originFile));  
		RequestBuilder builder = fileUpload(api_path).file(file).param("resourceId", "112933");
		mockMvc.perform(builder).andDo(print()).andExpect(status().isOk());*/
	}
}
