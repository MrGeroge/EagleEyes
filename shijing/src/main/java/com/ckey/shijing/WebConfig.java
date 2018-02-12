package com.ckey.shijing;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ckey.shijing.interceptor.LoginInterceptor;
import com.ckey.shijing.interceptor.TokenInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	private static final Logger logger = Logger.getLogger(WebConfig.class);

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("BeginRegInterceptor");
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns(
				"/account/*", "/picture", "/index", "/award", "/point",
				"/collection", "/comment", "/path", "/path/*", "/collection/*","/picture/*");
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/auth");
		// registry.addInterceptor(new
		// TokenInterceptor()).addPathPatterns("/user/logout","/user/feed/hot","/user/feed/near","/user/feed/collect/list","/user/feed/collect","/user/feed/love","/user/feed/collect/num","/app/feedback/android/add");
		// registry.addInterceptor(new CharInterceptor()).addPathPatterns("/*");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations(
				"/upload/");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
