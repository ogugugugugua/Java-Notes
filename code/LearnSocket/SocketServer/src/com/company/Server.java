package com.company;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8087;
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            Socket socket = ss.accept();

            InputStream is = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream("test.jpg");
            byte[] buff = new byte[1024];
            int length = 0;//actual received length
            while((length = is.read(buff))!=-1){
                fos.write(buff,0,length);
            }
            fos.flush();

            OutputStream os = socket.getOutputStream();
            os.write("Done!".getBytes());
            socket.close();
            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
