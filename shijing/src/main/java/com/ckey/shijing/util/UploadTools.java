package com.ckey.shijing.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class UploadTools {
	public static void saveMaxFile(String root, String fileName,
			MultipartFile img) {
		File file = new File(root + "/max/" + fileName);
		try {
			FileUtils.writeByteArrayToFile(file, img.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void saveMidFile(String root, String fileName,
			MultipartFile img) {
		File file = new File(root+"/mid/");
		if(!file.exists()){
			file.mkdir();
		}
		try {
			saveMinPhoto(img,root+"/mid/"+fileName,500,0.9d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void saveMinFile(String root, String fileName,
			MultipartFile img) {
		File file = new File(root+"/min/");
		if(!file.exists()){
			file.mkdir();
		}
		try {
			saveMinPhoto(img,root+"/min/"+fileName,300,0.9d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
			return true;
		}
		return false;
	}
	/**
	 * 等比例压缩算法： 
	 * 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
	 * @param srcURL 原图地址
	 * @param deskURL 缩略图地址
	 * @param comBase 压缩基数
	 * @param scale 压缩限制(宽/高)比例  一般用1：
	 * 当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width=comBase,height按原图宽高比例
	 */
	public static void saveMinPhoto(MultipartFile file, String deskURL, double comBase,
			double scale) throws Exception {
		Image src = ImageIO.read(file.getInputStream());
		int srcHeight = src.getHeight(null);
		int srcWidth = src.getWidth(null);
		int deskHeight = 0;// 缩略图高
		int deskWidth = 0;// 缩略图宽
		double srcScale = (double) srcHeight / srcWidth;
		/**缩略图宽高算法*/
		if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
			if (srcScale >= scale || 1 / srcScale > scale) {
				if (srcScale >= scale) {
					deskHeight = (int) comBase;
					deskWidth = srcWidth * deskHeight / srcHeight;
				} else {
					deskWidth = (int) comBase;
					deskHeight = srcHeight * deskWidth / srcWidth;
				}
			} else {
				if ((double) srcHeight > comBase) {
					deskHeight = (int) comBase;
					deskWidth = srcWidth * deskHeight / srcHeight;
				} else {
					deskWidth = (int) comBase;
					deskHeight = srcHeight * deskWidth / srcWidth;
				}
			}
		} else {
			deskHeight = srcHeight;
			deskWidth = srcWidth;
		}
		BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
		tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //绘制缩小后的图
		FileOutputStream deskImage = new FileOutputStream(deskURL); //输出到文件流
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
		encoder.encode(tag); //近JPEG编码
		deskImage.close();
	}
}
