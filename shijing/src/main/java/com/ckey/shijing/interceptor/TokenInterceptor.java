package com.ckey.shijing.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ckey.shijing.controller.BaseController;
import com.ckey.shijing.exception.AuthenticationException;
import com.ckey.shijing.exception.MissIdException;
import com.ckey.shijing.exception.TokenException;
import com.ckey.shijing.service.JedisService;

public class TokenInterceptor extends BaseController implements
		HandlerInterceptor {
	@Autowired
	@Qualifier("JedsiService")
	private JedisService jedis;
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {			
		JedisService jedis = null;
		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		if (userId == null) { 
			throw new MissIdException();
		} else if (token == null) { 
			throw new TokenException();
		} else {
			jedis = new JedisService();
			if (jedis.validate(userId, token)) {
				return true;
			} else { 
				throw new AuthenticationException();
			}
		}
	}

}
