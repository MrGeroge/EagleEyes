package com.ckey.shijing.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ckey.shijing.domain.Account;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.repository.CollectionRepository;
import com.ckey.shijing.repository.PictureRepository;
import com.ckey.shijing.service.JedisService;
import com.ckey.shijing.util.Md5Utils;
import com.ckey.shijing.util.UploadTools;
import com.ckey.shijing.vo.TokenVO;

import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

	private static JedisPool pool = null; // jedis的一个连接池
	@Autowired
	private PictureRepository pictureRepository;
	@Autowired
	private CollectionRepository collectionRepository;
	@Autowired
	AccountRepository accountRes;
	@Autowired
	private JedisService jedis;

	@RequestMapping(method = RequestMethod.POST)
	public TokenVO regist(
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "nickname", required = true) String nickname) {
		Account account = new Account();
		account.setNickname(nickname);
		account.setPassword(Md5Utils.EncodePass(password));
		account.setPhone(phone);
		account.setRest(50);
		account.setImgPath("/upload/test.jpg");
		accountRes.save(account);
		TokenVO token = new TokenVO();
		token.setUserId(account.getId());
		token.setToken(jedis.authoriz(account.getId()));
		return token;
	}

	@RequestMapping(value = "/pass", method = RequestMethod.POST)
	public Object chanPass(@RequestParam(value="userId") String userId,
			@RequestParam(value = "nPass") String nPass,
			@RequestParam(value = "token") String token) {
		accountRes.updateAccountPass(Md5Utils.EncodePass(nPass), Integer.parseInt(userId));
		String result = "success";
		return result;
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public Object logout(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "token") String token) {
		jedis.delete(userId);
		String result = "success";
		return result;
	}
	@RequestMapping(value="/img",method=RequestMethod.POST)
	public Object updateImg(@RequestParam("file") MultipartFile file,
			@RequestParam("userId") int userId,
			@RequestParam(value = "token") String token,
			HttpServletRequest request){
		Account account = accountRes.getOne(userId);
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
				"/upload/"+userId));
		if (!filedir.exists())
			filedir.mkdir();
		UploadTools.saveMaxFile(filedir.getPath(), newFileName, file);
		accountRes.updataeAccountImgPath(uploadPath+"/max/"+newFileName, userId);
		String result = "success";
		return result;
	}

}
