package SocketLink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Linker
{
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    public Linker(Socket skt)
    {
        try {
            socket = skt;
            //客户端输出流，向服务器发消息
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            //客户端输入流，接收服务器消息
            in=new BufferedReader(new InputStreamReader(socket.getInputStream(),StandardCharsets.UTF_8));
            out=new PrintWriter(bw,true); //装饰输出流，及时刷新
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String read()
    {
        try {
            return in.readLine();
        }catch (Exception e){
            //e.printStackTrace();
            UnlinkWarning unlinkWarning = new UnlinkWarning();
        }
        return null;
    }
    public void write(String sendMessage)
    {
        out.println(sendMessage);
        out.flush();
    }
    public JSONObject readJSON()
    {
        String str = read();
        //System.out.println("read:"+str);
        return JSON.parseObject(str);
    }
    public void writeJSON(JSONObject json)
    {
        write(json.toJSONString());
        //System.out.println("write:"+json);
    }
    public void close()
    {
        try {
            in.close();
            out.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
