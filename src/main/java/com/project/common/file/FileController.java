package com.project.common.file;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.common.util.FileUtils;

@RestController
public class FileController {
	
	
	@Resource(name="uploadFolder")
	String uploadFolder;
	
	@Autowired
	ServletContext context;
	
	@PostConstruct
	public void initController() {
		uploadFolder = context.getRealPath(File.separator + uploadFolder);
		File file = new File(uploadFolder);
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println("업로드 폴더 " + file.getPath());
	}
	
	@PostMapping(value="user/uploadProfile",produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		ResponseEntity<String> entity = null;
		String name = FileUtils.uploadFile(file.getOriginalFilename(), uploadFolder, file.getBytes());
		entity = new ResponseEntity<>(name,HttpStatus.CREATED);
		return entity;
	}
}
