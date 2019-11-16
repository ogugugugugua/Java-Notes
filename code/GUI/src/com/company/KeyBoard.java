package com.company;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * 本类通多个按钮
 * 来实现练习键盘监听接口的使用
 * @author xiaoqiang
 * @Time 2017年4月30日
 */
public class KeyBoard extends JFrame implements KeyListener,MouseListener{
    Container con;
    JButton[] [] jButton;
    JPanel jp;
    JTextArea textArea;
    JButton reset;
    private static String[] keys={"1234567890-=","QWERTYUIOP[]","ASDFGHJKL'","ZXCVBNM,./"};
    public KeyBoard(){
        super("键盘模拟器");
        this.draw();
    }
    private void draw(){
        con=this.getContentPane();
        con.setLayout(null);
        jButton=new JButton[keys.length][];
        for(int i=0;i<keys.length;i++){
            jButton[i]=new JButton[keys[i].length()];
            for(int j=0;j<keys[i].length();j++){
                jButton[i][j]=new JButton(String.valueOf(keys[i].charAt(j)));
                jButton[i][j].setBounds(10+j*80, 30+i*80, 70, 70);
                jButton[i][j].setBackground(Color.BLUE);
                con.add(jButton[i][j]);
                jButton[i][j].addKeyListener(this);
                jButton[i][j].addMouseListener(this);
            }
        }
        textArea=new JTextArea();
        textArea.setBounds(20, 350, 900, 375);
        textArea.setEditable(false);
        con.add(textArea);
        reset=new JButton("重置");
        reset.setBounds(810,190,150,150);
        reset.addMouseListener(this);
        con.add(reset);
        this.setSize(1000, 800);
        this.setVisible(true);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    /**
     * 在按下见键盘时，选择按下按键
     * 把指定按键变红
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        for(int i=0;i<keys.length;i++){
            for(int j=0;j<keys[i].length();j++){
                if(jButton[i][j].getText().equals(String.valueOf(Character.toUpperCase(e.getKeyChar())))){
                    jButton[i][j].setBackground(Color.red);
                    textArea.setText(textArea.getText()+jButton[i][j].getText());
                }
            }
        }

    }
    /**
     * 在释放按下键盘时，
     * 把按下字母恢复到原来状态
     */
    @Override
    public void keyReleased(KeyEvent e) {
        for(int i=0;i<keys.length;i++){
            for(int j=0;j<keys[i].length();j++){
                if(jButton[i][j].getText().equals(String.valueOf(Character.toUpperCase(e.getKeyChar())))){
                    jButton[i][j].setBackground(Color.blue);
                }
            }
        }

    }

    public static void main(String[] args) {
        new KeyBoard();
    }
    /**
     * 鼠标点击把内容放入文本框
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(reset)){
            textArea.setText("");
        }else{
            JButton button=(JButton) e.getSource();
            for(int i=0;i<keys.length;i++){
                for(int j=0;j<keys[i].length();j++){
                    if(jButton[i][j].getText().equals(button.getText())){
                        textArea.setText(textArea.getText()+button.getText());
                    }
                }
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    /**
     * 鼠标进入相应组件
     * 按钮变红
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        JButton button=(JButton) e.getSource();
        for(int i=0;i<keys.length;i++){
            for(int j=0;j<keys[i].length();j++){
                if(jButton[i][j].getText().equals(button.getText())){
                    jButton[i][j].setBackground(Color.red);
                }
            }
        }
    }
    /**
     * 鼠标退出组件
     * 按钮恢复蓝色
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        JButton button=(JButton) e.getSource();
        for(int i=0;i<keys.length;i++){
            for(int j=0;j<keys[i].length();j++){
                if(jButton[i][j].getText().equals(button.getText())){
                    jButton[i][j].setBackground(Color.blue);
                }
            }
        }
    }
}

