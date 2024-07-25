package com.c.Services;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.c.helper.AppConstants;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private Cloudinary cloudinary;

	@Override
	public String uploadimage(MultipartFile profileimage , String fileName) {
		  if (profileimage.isEmpty()) {
	            throw new RuntimeException("Empty file received");
	        }

	      

	        try {
	            byte[] data = profileimage.getBytes();
	            Map<String, Object> params = ObjectUtils.asMap(
	                "public_id", fileName
	            );

	            Map uploadResult = cloudinary.uploader().upload(data, params);
	            return uploadResult.get("url").toString(); // Return the URL of the uploaded image

	        } catch (IOException e) {
	            throw new RuntimeException("Failed to upload file to Cloudinary: " + e.getMessage());
	        }
	    }
	


	@Override
	public String getUrlFromPublicId(String publicid) {

		return cloudinary.url()
		        .transformation(new Transformation<>().width(AppConstants.CONTACT_IMAGE_WIDTH)
		                .height(AppConstants.CONTACT_IMAGE_HEIGHT).crop(AppConstants.CONTACT_IMAGE_CROP))
		        .generate(publicid);

	}

}
