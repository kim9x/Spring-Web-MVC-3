package me.pulpury.demowebmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void fileUploadTest() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain"
				, "hello file".getBytes());
		
		this.mockMvc.perform(multipart("/file").file(file))
				.andDo(print())
				.andExpect(status().is3xxRedirection());
	}

}
