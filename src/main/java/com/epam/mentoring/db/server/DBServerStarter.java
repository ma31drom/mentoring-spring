package com.epam.mentoring.db.server;

import org.h2.tools.Server;
import org.springframework.beans.factory.InitializingBean;

public class DBServerStarter implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		final Server WebServer = Server.createWebServer();
		final Server TCPServer = Server.createTcpServer();
		WebServer.start();
		TCPServer.start();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			WebServer.stop();
			TCPServer.stop();
		}));
	}

}
