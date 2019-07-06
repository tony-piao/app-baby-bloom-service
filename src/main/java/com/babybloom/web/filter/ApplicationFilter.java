package com.babybloom.web.filter;

import com.babybloom.web.utility.LogUtility;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 应用过滤器(包含本应用所有的对过滤器的设置)
 * 
 * @author ZZ
 *
 */
@Component
public class ApplicationFilter implements Filter {

	// 请求和响应编码
//	private final String APL_CHARACT_ENCODING = "utf-8";
	// 域检
	private final String APL_REQUEST_METHOD = "OPTIONS";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;

		// 处理字符编码问题
//		req.setCharacterEncoding(APL_CHARACT_ENCODING);
//		rsp.setCharacterEncoding(APL_CHARACT_ENCODING);

		// 解决跨域问题
		setCross(rsp);
		// 预检
		if (APL_REQUEST_METHOD.equalsIgnoreCase(req.getMethod())) {
			return;
		}

		chain.doFilter(req, rsp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		LogUtility.businessLog().info("字符编码,跨域过滤器已启动!!");
	}

	/**
	 * 设置解决跨域方案
	 * 
	 * @param response
	 */
	private void setCross(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "86400");
		response.setHeader("Access-Control-Allow-Headers", "content-type,token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
	}
}
