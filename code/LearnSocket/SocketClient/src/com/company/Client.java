package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private static final String IP = "10.188.244.45";
    private static final int PORT = 8087;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP,PORT);
            FileInputStream fileInputStream = new FileInputStream("D:\\Java_WorkSpace\\SocketClient\\src\\com\\company\\sent.jpg");
            OutputStream outputStream = socket.getOutputStream();

            byte[] buff = new byte[512];
            int length = 0;
            while((length = fileInputStream.read(buff))!=-1){
                outputStream.write(buff,0,length);
            }
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            byte[] readbuff = new byte[1024];
            length = is.read(readbuff);
            String message = new String(readbuff,0,length);
            System.out.println(message);

            fileInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
