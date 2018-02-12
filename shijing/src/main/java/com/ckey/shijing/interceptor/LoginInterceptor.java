package com.ckey.shijing.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ckey.shijing.controller.BaseController;
import com.ckey.shijing.domain.Account;
import com.ckey.shijing.exception.LoginException;
import com.ckey.shijing.exception.MissPassException;
import com.ckey.shijing.exception.MissUserNameException;
import com.ckey.shijing.repository.AccountRepository;
import com.ckey.shijing.util.Md5Utils;

public class LoginInterceptor extends BaseController implements
		HandlerInterceptor {
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
		
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		if (phone == null) { 
			throw new MissUserNameException();
		} else if (password == null) { 
			throw new MissPassException();
		} else {
			return true;
		}
	}

}
