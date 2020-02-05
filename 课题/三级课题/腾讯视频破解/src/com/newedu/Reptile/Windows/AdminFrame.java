package com.newedu.Reptile.Windows;


import com.newedu.Reptile.Mysql.AccountMysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {

    public static void main(String[] args) {
        new AdminFrame();
    }
    public AdminFrame(){
        inin();
        initComponents1();
        setVisible(true);//窗口可见  在最后
    }

    private void inin() {
        JPanel jp=new JPanel();
        setTitle("管理员");//窗口标题
        setResizable(true);//窗口大小是否可改变 false不可1     true可改
        setSize(200,200);
        setLocationRelativeTo(null);//窗口居中
        JLabel jl=new JLabel("请输入管理员密码",JLabel.CENTER);
        Font f=new Font("楷体",Font.BOLD,20);
        jl.setForeground(Color.pink);
        jl.setFont(f);
        JPasswordField jpf =new JPasswordField(18);//内容变成***
        JButton jb1=new JButton("确定");
        JButton jb2=new JButton("返回");
        jb1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String a="admin";
                String aa=jpf.getText();
                try {
                    if(new AccountMysql().login(a,aa)){
                        new AccountFrame();
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null,"管理员密码错误","警告",0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        jb2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        jp.add(jl);
        jp.add(jpf);
        jp.add(jb1);
        jp.add(jb2);

        jp.setOpaque(false);
        this.add(jp);
    }


    private void initComponents1() {

        // 获取 窗体的内容图层
        JPanel jPanel = (JPanel)this.getContentPane();
        //内容图层设置为透明的
        jPanel.setOpaque(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.createImage(LoginFrame.class.getResource("/admin.jpg"));
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon);

        label.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
        // layer图层
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));


    }//差背景图片

}
