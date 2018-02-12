package com.ckey.shijing.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.domain.Picture;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.repository.CollectionRepository;
import com.ckey.shijing.repository.PictureRepository;
import com.ckey.shijing.util.GDUtils;
import com.ckey.shijing.util.PointUtil;
import com.ckey.shijing.util.UploadTools;
import com.ckey.shijing.vo.PictureVO;

@RestController
@RequestMapping(value = "/picture")
public class PictureController extends BaseController {
	@Autowired
	PictureRepository pictureRes;
	@Autowired
	private AccountRepository accountRes;
	@Autowired
	private CollectionRepository collectionRes;
	@Autowired
	PointUtil pu;

	@RequestMapping(method = RequestMethod.POST)
	public Object picture(@RequestParam("file") MultipartFile file,
			@RequestParam("userId") int userId,
			@RequestParam("status") boolean status,
			@RequestParam(value = "token") String token,
			@RequestParam(value = "des") String des,
			@RequestParam(value = "latitude") String latitude,
			@RequestParam(value = "longitude") String longitude,
			@RequestParam(value="name")String name,
			HttpServletRequest request) throws Exception { // 发布
		Picture picture = new Picture();
		Account account = new Account();
		String fileName = file.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
				.toLowerCase();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = sdf.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		String path = request.getContextPath();
		String uploadPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/upload" + "/" + userId;
		File filedir = new File(request.getServletContext().getRealPath(
				"/upload/" + userId));
		if (!filedir.exists())
			filedir.mkdir();
		UploadTools.saveMaxFile(filedir.getPath(), newFileName, file);
		UploadTools.saveMidFile(filedir.getPath(), newFileName, file);
		UploadTools.saveMinFile(filedir.getPath(), newFileName, file);
		String maxSize = uploadPath + "/max/" + newFileName;
		String minSize = uploadPath + "/min/" + newFileName;
		String midSize = uploadPath + "/min/" + newFileName;
		account = accountRes.findById(userId);
		picture.setAccount(account);
		picture.setMaxSize(maxSize);
		picture.setMidSize(midSize);
		picture.setMinSize(minSize);
		picture = pictureRes.save(picture); // 保存在数据库,图片的发布信息
		GDUtils.createSingleDate(longitude, latitude, name, minSize, maxSize,
				des);
		if (status) {
			boolean tag = pu.consume("publish_upload", userId); // 悬赏上传
		} else { // 普通上传
			boolean tag = pu.consume("normal_upload", userId); // 普通上传
		}
		String result = "success";
		return result;
	}

	@RequestMapping(value = "/my", method = RequestMethod.POST)
	public List<PictureVO> myPic(@RequestParam("userId") int userId,
			@RequestParam(value = "token") String token) {
		List<PictureVO> pv = new ArrayList<PictureVO>();
		Account account = accountRes.findById(userId);
		List<Picture> items = pictureRes.findByAccount(account);
		for (Picture item : items) {
			PictureVO data = new PictureVO();
			data.setMidSize(item.getMidSize());
			data.setName(item.getDes());
			pv.add(data);
		}
		return pv;
	}
}
