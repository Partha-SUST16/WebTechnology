/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.september;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class Handlers {
    public static class RootHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange he) throws IOException {
			String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " + Main.port + "</h1>";
			he.sendResponseHeaders(200, response.length());
			OutputStream os = he.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
    public static class CreateBook implements HttpHandler{

        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("CreateBook");
           Map<String, Object> parameters = new HashMap<String, Object>();
			InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String query = br.readLine();
                        System.out.println(query);
                        JSONObject js = new JSONObject(query.toString());
            String id = js.getString("id");
            String name = js.getString("name");
            String authod = js.getString("author");
            System.out.println(id+" "+name+" "+authod);
            Main.arr.add(new Book(id,name,authod));
            String response = id+" "+name+ " "+authod+"  inserted Successfull";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        
    }
    public static class GetBook implements HttpHandler{

        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("GetBook");
             Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();
            System.out.println("query " + query);
            parseQuery(query, parameters);
            String id = parameters.get("id").toString();
            Book book = null;
            for(Book t : Main.arr)
            {
                if(t.getId().equalsIgnoreCase(id))
                    book = t;
            }
            
            String response = "";
            if(book==null)
                response = "No Book Found";
            else {
               JSONObject js = new JSONObject(book);
               response = js.toString();
            }
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        
    }
    public static class DeleteBook implements HttpHandler{

        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("DeleteBook");
             Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = he.getRequestURI();
            String query = requestedUri.getRawQuery();
            System.out.println("query " + query);
            parseQuery(query, parameters);
            String id = parameters.get("id").toString();
            for(Book b: Main.arr){
                if(b.getId().equalsIgnoreCase(id)){
                    Main.arr.remove(b);
                    break;
                }
            }
            String response = "Delete Successfull";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        
    }
    public static class UpdateBook implements HttpHandler{
        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("UpdateBook");
            Map<String, Object> parameters = new HashMap<String, Object>();
            InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();
            System.out.println(query);
            JSONObject js = new JSONObject(query.toString());
            String id = null,name=null,author=null;
            try{
             id = js.getString("id");
             name = js.getString("name");
             author = js.getString("author");
            }catch(Exception e){
                e.printStackTrace();
            }
                
            for(Book b : Main.arr){
                if(b.getId().equalsIgnoreCase(id)){
                    if(name==null)
                        b.setAuthor(author);
                    else if(author==null)
                        b.setName(name);
                    else {
                        b.setAuthor(author);
                        b.setName(name);
                    }
                }
            }
            String response = "Update Successfull";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        
    }
    @SuppressWarnings("unchecked")
	public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

		if (query != null) {
			String pairs[] = query.split("[&]");

			for (String pair : pairs) {
				String param[] = pair.split("[=]");

				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
				}

				if (param.length > 1) {
					value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
				}

				if (parameters.containsKey(key)) {
					Object obj = parameters.get(key);
					if (obj instanceof List<?>) {
						List<String> values = (List<String>) obj;
						values.add(value);
					} else if (obj instanceof String) {
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} else {
					parameters.put(key, value);
				}
			}
		}
	}
}
