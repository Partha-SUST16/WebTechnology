/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.september;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 *
 * @author hp
 */
public class SiHttpServer {
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
			server.createContext("/insert", new Handlers.CreateBook());
			server.createContext("/getBook", new Handlers.GetBook());
			server.createContext("/delete", new Handlers.DeleteBook());
                        server.createContext("/update",new Handlers.UpdateBook());
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
