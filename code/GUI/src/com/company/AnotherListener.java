package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class AnotherListener {
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

        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                Random random = new Random();
                jLabel.setLocation(random.nextInt(jFrame.getWidth() - jLabel.getWidth()),
                        random.nextInt(jFrame.getHeight() - jLabel.getHeight()));

            }
        });

        jFrame.add(jLabel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
