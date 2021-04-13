package me.pulpury.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {
	
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

}
