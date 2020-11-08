package com.org.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.org.models.Response;
import com.org.models.User;
import com.org.services.FileStorageService;
import com.org.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FileUploadController {
	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
	private UserService userService;

    @PostMapping(value = "/uploadFile/{id}", consumes = { "multipart/form-data", MediaType.APPLICATION_JSON_VALUE }) 
    public Response uploadFile(@PathVariable int id, @RequestParam("imageFile") MultipartFile file) throws Exception {
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/downloadFile/")
            .path(fileName)
            .toUriString();
        User user = userService.getUser(id);
        if(user != null) {
        	user.setProfilepic(fileDownloadUri);
        	userService.updateUser(user);
        }
        
        Response res = new Response();
        res.setFileDownloadUri(fileDownloadUri);
        res.setFileName(fileName);
        res.setFileType(file.getContentType());
        res.setSize(file.getSize());
        return res;
    }
    
//    @PostMapping("/uploadMultipleFiles")
//    public List< Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//            .stream()
//            .map(file -> {
//				try {
//					return uploadFile(file);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return null;
//			})
//            .collect(Collectors.toList());
//    }
}
