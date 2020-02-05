package com.newedu.Reptile.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReptileFrame extends JFrame {

    public static void main(String[] args) {
        new ReptileFrame();
    }
    public ReptileFrame(){
        init();
        setVisible(true);//窗口可见  在最后
    }
    private void init() {
        setTitle("爬虫");//窗口标题
        setResizable(true);//窗口大小是否可改变 false不可1     true可改
        setSize(500,550);  //窗口大小
        //setLocation(1000,100);    //位置
        setLocationRelativeTo(null);//窗口居中
        //HIDE_ON_CLOSE    点击关闭时隐藏
        //EXIT_ON_CLOSE    点击关闭时退出
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        JPanel jp1=new JPanel();
        JPanel jp2=new JPanel();
        JPanel jp3=new JPanel();
        jp1.setLayout(new GridLayout(1,4));
        jp2.setLayout(new GridLayout(1,1));
        jp3.setLayout(new GridLayout(1,5));


        JTextArea bg= new JTextArea();
        bg.setBackground(Color.pink);//改颜色
        JScrollPane  jsp=new JScrollPane(bg);//滚动条
        jsp.isWheelScrollingEnabled();//可以鼠标滚动

        JTextField jt1 = new JTextField();
        JTextField jt2 = new JTextField();
        jt2.setText("输入具体");
        jt1.setText("输入城市");
        jt1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jt1.setText("");
            }
        });
        jt2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jt2.setText("");
            }
        });
        JButton an1=new JButton("获取数据");
        JButton an2=new JButton("重置");

        JButton an11=new JButton("退出程序");
        JButton an12=new JButton("返回");

        jp1.add(BorderLayout.NORTH,jt1);
        jp1.add(BorderLayout.NORTH,jt2);
        jp1.add(BorderLayout.NORTH,an1);//↑
        jp1.add(BorderLayout.NORTH,an2);//↑


        jp2.add(BorderLayout.CENTER,jsp);

        jp3.add(BorderLayout.SOUTH,an11);//↓
        jp3.add(BorderLayout.SOUTH,an12);//↓

        an2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    jt1.setText("");
                    jt2.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        an12.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new DataFrame();
            }
        });
        an11.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        //jf.setLayout(new GridLayout(3,1));
        jp2.getAutoscrolls();
        add(BorderLayout.NORTH,jp1);
        add(jp2);
        add(BorderLayout.SOUTH,jp3);
    }

}
