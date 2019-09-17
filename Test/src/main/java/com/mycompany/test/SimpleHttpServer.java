/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 *
 * @author USER
 */
public class SimpleHttpServer {
	private int port;
	private HttpServer server;

        HostnameVerifier allHostsValid = new HostnameVerifier() {
      @Override
      public boolean verify(String hostname, SSLSession session) {
        return true;
      }
    };
	public void Start(int port) {
		try {
			this.port = port;
			server = HttpServer.create(new InetSocketAddress(port), 0);
			System.out.println("server started at " + port);
			server.createContext("/", new Handlers.RootHandler());
			server.createContext("/echoHeader", new Handlers.EchoHeaderHandler());
			server.createContext("/echoGet", new Handlers.EchoGetHandler());
			server.createContext("/echoPost", new Handlers.EchoPostHandler());
			server.setExecutor(null);
                        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Stop() {
		server.stop(0);
		System.out.println("server stopped");
	}
}
