package com.babybloom.web.filter;

import com.babybloom.web.utility.LogUtility;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		LogUtility.businessLog().info("过滤器已销毁");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(req, rsp);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		LogUtility.businessLog().info("身份认证过滤器已启动!!");
	}

}
