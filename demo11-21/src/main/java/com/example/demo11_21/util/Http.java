package com.example.demo11_21.util;

import com.example.demo11_21.bean.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ASUS on 2017/11/21.
 */

public class Http {

    public static final String start_contact="Http://10.101.1.46:8080/stu/list";
    public static BufferedReader http(String urls){
        HttpURLConnection conn=null;
        InputStream his=null;
        try {
            URL url=new URL(urls);
            conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.connect();
            his=conn.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(his));
            return br;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getJSON(BufferedReader br){
        StringBuffer sb=new StringBuffer();
        String line="";
        try {
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public static ArrayList<User> jsonContact(String json){
        ArrayList<User> contacts=new ArrayList<>();
        try {
            JSONArray jr=new JSONArray(json);
            for(int i=0;i<jr.length();i++){
                JSONObject jo=jr.getJSONObject(i);
                Integer id=jo.getInt("id");
                String name=jo.getString("name");
                String password=jo.getString("password");
                User user=new User(id,name,password);
                contacts.add(user);
            }
            return contacts;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
