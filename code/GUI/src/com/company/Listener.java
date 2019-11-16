package com.company;

import javax.swing.*;
import java.awt.event.*;

public class Listener {
    public static void main(String args[]){
        //frame
        JFrame jFrame = new JFrame("Test");
        jFrame.setSize(400, 400);
        jFrame.setLocation(500, 500);
        jFrame.setLayout(null);

        //image
        ImageIcon imageIcon = new ImageIcon("C:\\java笔记\\code\\GUI\\src\\com\\company\\shana.png");
        final JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        jLabel.setBounds(50,50,imageIcon.getIconWidth(),imageIcon.getIconHeight());

        //button
        JButton jButton = new JButton("Disappear");
        jButton.setBounds(150, 250, 100, 40);

        //button action
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jLabel.isVisible()){
                    jLabel.setVisible(false);
                    jButton.setText("Appear");
                }else {
                    jLabel.setVisible(true);
                    jButton.setText("Disappear");
                }
                jFrame.setFocusable(true);
            }
        });

        //这里存在不知名的冲突！！待解决！
//        jFrame.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
//                System.out.println(e.getKeyCode());
//                if (e.getKeyCode() == 39) {
//                    // 图片向右移动 （y坐标不变，x坐标增加）
//                    jLabel.setLocation(jLabel.getX() + 10, jLabel.getY());
//                }
//            }
//        });

        jFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if (e.getKeyCode() == 39) {
                    // 图片向右移动 （y坐标不变，x坐标增加）
                    jLabel.setLocation(jLabel.getX() + 10, jLabel.getY());
                }
            }
        });

        jFrame.add(jButton);  //如果这行存在就会和键盘监听冲突
        jFrame.add(jLabel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

    }
}
