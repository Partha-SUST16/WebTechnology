/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

/**
 *
 * @author USER
 */
public class Main {
	public static int port = 9000;
	public static void main(String[] args) {
//		// start http server
		SimpleHttpServer httpServer = new SimpleHttpServer();
		httpServer.Start(port);
		
		// start https server
//		SimpleHttpsServer httpsServer = new SimpleHttpsServer();
//		httpsServer.Start(port);
		
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(Main.class.getClassLoader().getResource("").getPath());
		
	}
}
