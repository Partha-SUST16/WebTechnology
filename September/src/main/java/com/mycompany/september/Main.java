/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.september;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class Main {
    public static int port = 15001;
    public static List<Book>arr = new ArrayList<>();
    public static void main(String[] args) {
        SiHttpServer serv = new SiHttpServer();
        serv.Start(port);
    }
}
