package com.c.Services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	
	String uploadimage(MultipartFile profileimage , String fileName);
	
	String getUrlFromPublicId(String publicid);
}
