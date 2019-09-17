/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Main {
	public static int port = 15001;
        public static List<Information> arr ;
	public static void main(String[] args) {
//		// start http server
                arr = new ArrayList<>();
		SimpleHttpServer httpServer = new SimpleHttpServer();
		httpServer.Start(port);
		
		
	}
}
