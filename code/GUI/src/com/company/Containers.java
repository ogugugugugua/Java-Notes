package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class Containers {
    private JFrame jFrame;
    private JButton jButton;
    private JDialog jDialog;
    private JButton jButton_jd;
    public Containers(){
        jFrame = new JFrame("Back");
        jFrame.setSize(400, 400);
        jFrame.setLocation(300, 300);
        jFrame.setLayout(null);

        jButton = new JButton("Click to open a modal window");
        jButton.setBounds(50, 50, 250, 50);

        jButton_jd = new JButton("Deactivate Resize");
        jButton_jd.setBounds(50, 50, 150, 50);

        jDialog = new JDialog(jFrame);
        jDialog.setSize(300, 300);
        jDialog.setModal(true);
        jDialog.setLocation(300, 300);
        jDialog.setLayout(null);

    }

    public void show(){
        this.jFrame.add(this.jButton);
        this.jDialog.add(this.jButton_jd);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
        //this.jDialog.setVisible(true);
    }

    public void jButtonLisener(){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.setVisible(true);
            }
        });
    }

    public void jButtonLisener_jd(){
        jButton_jd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jDialog.isResizable()){
                    jButton_jd.setText("Activate Resize");
                    jDialog.setResizable(false);
                }else {
                    jButton_jd.setText("Deactivate Resize");
                    jDialog.setResizable(true);
                }
            }
        });
    }

    public static void main(String args[]) {
        Containers c = new Containers();
        c.show();
        c.jButtonLisener();
        c.jButtonLisener_jd();
    }
}
