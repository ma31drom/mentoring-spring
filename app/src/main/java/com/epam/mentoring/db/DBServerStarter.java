package com.epam.mentoring.db;

import org.h2.tools.Server;
import org.springframework.beans.factory.InitializingBean;

public class DBServerStarter implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		final Server webServer = Server.createWebServer();
		final Server tcpServer = Server.createTcpServer();
		webServer.start();
		tcpServer.start();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			webServer.stop();
			tcpServer.stop();
		}));
	}

}
