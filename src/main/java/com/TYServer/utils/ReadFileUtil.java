package com.TYServer.utils;

import java.io.*;

public class ReadFileUtil {
    public static String getString(String path) throws IOException {
        StringBuffer sb = new StringBuffer();
        String encode = "utf-8";
        FileInputStream fis = null;
        InputStreamReader read = null;
        BufferedReader br;
        fis = new FileInputStream(path);
        read = new InputStreamReader(fis,encode);
        br = new BufferedReader(read);
        String textLine;
        while((textLine = br.readLine()) != null){
            sb.append(textLine);
        }
        fis.close();
        read.close();
        //System.out.println(sb.toString());
        return "sb.toString()";
    }
}
