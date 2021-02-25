package com.project.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class FileUtils {
	
	public static String uploadFile(String originalName, String uploadPath, byte[] fileData) throws Exception{
		String uploadFileName = "";
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString().replace("-","")+"_"+originalName;
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		file = new File(uploadPath,savedName);
		FileCopyUtils.copy(fileData, file);
		uploadFileName = makeThumnail(uploadPath, savedName);
		return uploadFileName;
	}
	
	public static String makeThumnail(String uploadPath, String savedName) throws Exception {
		File file = new File(uploadPath, savedName);
		System.out.println(file.getPath());
		BufferedImage image = ImageIO.read(file);
		BufferedImage sourceImage = Scalr.resize(image, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		String thumnailImage = uploadPath+File.separator+"s_"+savedName;
		File file1 = new File(thumnailImage);
		String formatName = savedName.substring(savedName.lastIndexOf(".")+1);
		ImageIO.write(sourceImage, formatName, file1);
		String name = thumnailImage.substring(uploadPath.length()).replace(File.separatorChar, '/');
		return name;
	}
	
}
