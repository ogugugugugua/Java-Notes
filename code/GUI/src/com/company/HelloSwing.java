package com.company;

import javax.swing.*;

public class HelloSwing {
    public static void main(String args[]){
        JFrame jFrame = new JFrame("Test");
        jFrame.setSize(400, 400);
        jFrame.setLocation(500, 500);
        jFrame.setLayout(null);
        JButton jButton = new JButton("Button");
        jButton.setBounds(50, 50, 280, 40);
        jFrame.add(jButton);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        Thread thread = new Thread(){
            public void run(){
                while (true){
                    try{
                        Thread.sleep(100);
                        System.out.println("x: "+jFrame.getX()+" y:"+jFrame.getY());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}
