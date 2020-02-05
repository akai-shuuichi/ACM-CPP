package com.newedu.Reptile.Windows;

import com.newedu.Reptile.Mysql.SysUser;
import com.newedu.Reptile.Mysql.AccountMysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

public class LoginFrame extends JFrame {

    public LoginFrame(){
//        for(int i=0;i<4;i++)
//        JOptionPane.showMessageDialog(null,"欢迎使用","hello",i);
        init();
        initComponents1();
        initjb();
        setVisible(true);//窗口可见  在最后
    }

    private void initjb() {
        AccountMysql my=new AccountMysql();
        JPanel jp=new JPanel();
        jp.setOpaque(false);
        JLabel jl=new JLabel("　猎　马　虫　",JLabel.CENTER);
        Font f=new Font("楷体",Font.BOLD,40);
        jl.setForeground(Color.blue);
        jl.setFont(f);

        JLabel jl1=new JLabel("用户名：",JLabel.CENTER);
        JLabel jl2=new JLabel("密  码：",JLabel.CENTER);

        JTextField jt1 = new JTextField(25);//输入长度
        JPasswordField jt2 =new JPasswordField(25);//内容变成***
//        jt1.setBounds(200,90,50,50);
//        JButton jb1 = new JButton("注册");
        JButton jb2 = new JButton("登录");
        JButton jb33 = new JButton("重置");

        JButton jb3 = new JButton("账号操作管理");
//        jb1.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                new AccountModify(false);
//            }
//        });//注册

        jt2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if('\n'==e.getKeyChar()) {
                    jb2.doClick();
                }
            }
        });//点击按钮

        jb33.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    jt1.setText("");
                    jt2.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });//重置

        jb2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String a=jt1.getText();
                String aa=jt2.getText();
                if(a.equals("")||aa.equals("")){
                    JOptionPane.showMessageDialog(null,"请输入账号密码","警告",0);
                }
                else {
                    try {
                        SysUser sy = my.querylogin(a);
                        if (sy==null) {
                            JOptionPane.showMessageDialog(null, "账号不存在", "警告", 0);
                        } else if (!sy.getPwd().equals(aa)) {
                            JOptionPane.showMessageDialog(null, "密码错误", "警告", 0);
                        } else {
                            JOptionPane.showMessageDialog(null, "登录成功", "警告", 1);
                            dispose();//关闭当前窗口
                            new DataFrame();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        jb3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AdminFrame();
            }
        });

        jp.add(jl);
        jp.add(jl1);
        jp.add(jt1);

        jp.add(jl2);
        jp.add(jt2);

//        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb33);
        jp.add(jb3);

        this.getLayeredPane().setOpaque(false);
        jp.setBackground(Color.pink);//改颜色

        this.add(jp);
    }

    private void initComponents1() {

        // 获取 窗体的内容图层
        JPanel jPanel = (JPanel)this.getContentPane();
        //内容图层设置为透明的
        jPanel.setOpaque(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.createImage(LoginFrame.class.getResource("/account.png"));
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon);

        label.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
        // layer图层
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));


    }//差背景图片

    private void init() {
        setTitle("登录界面");//窗口标题
        setResizable(false);//窗口大小是否可改变 false不可1     true可改
        setSize(300,300);
        setLocationRelativeTo(null);//窗口居中

        URL resource = LoginFrame.class.getResource("/time.jpg");//找到图片
        Image image=Toolkit.getDefaultToolkit().getImage(resource);
        this.setIconImage(image);//换头像

    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
