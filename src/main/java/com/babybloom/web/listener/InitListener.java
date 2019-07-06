package com.babybloom.web.listener;

import com.babybloom.web.utility.LogUtility;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		LogUtility.businessLog().info("监听器已启动");
	}

}
