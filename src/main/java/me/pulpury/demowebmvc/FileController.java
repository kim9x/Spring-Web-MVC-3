package me.pulpury.demowebmvc;

import java.io.File;
import java.io.IOException;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping("/file")
	public String fileUploadForm(Model model) {
		return "files/index";
	}
	
	@PostMapping("/file")
	public String fileUpload(@RequestParam MultipartFile file
							, RedirectAttributes attributes) {
		
		System.out.println("file name: " + file.getName());
		System.out.println("file Original Filename: " + file.getOriginalFilename());
		
		// save
		String message = file.getOriginalFilename() + " is uploaded";
		attributes.addFlashAttribute("message", message);
		return "redirect:/file";
	}
	
	@GetMapping("/file/{filename}")
//	@ResponseBody
//	@ResponseBody를 해도 되고 해도 된다.
//	ResponseEntity<Resource>을 응답 본문으로 작성한 것이기 때문에
//	return 값에 있는 것들 자체가 응답이 된다.
	public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:" + filename);
		File file = resource.getFile();
		
		Tika tika = new Tika();
		String mediaType = tika.detect(file);
		
		return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.header(HttpHeaders.CONTENT_TYPE, mediaType)
					.header(HttpHeaders.CONTENT_LENGTH, file.length() + "")
					.body(resource);
	}

}
