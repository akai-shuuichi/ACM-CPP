package com.newedu.Reptile.Windows;
import com.newedu.Reptile.Mysql.AccountMysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class AccountModify extends JFrame {//密码修改和注册

    public static void main(String[] args) {
        new AccountModify(false);
        new AccountModify(true);
    }
    public AccountModify(boolean b){
        init(b);
        initComponents1();
        setVisible(true);//窗口可见  在最后
    }

    private void init(boolean b) {

        JPanel jp=new JPanel();
        setTitle("状态");//窗口标题
        setResizable(true);//窗口大小是否可改变 false不可1     true可改
        setSize(200,300);

        JLabel jl;
        if(b) {
            jl=new JLabel("密码修改",JLabel.CENTER);
        }
        else {
            jl=new JLabel("账号注册",JLabel.CENTER);
        }

        Font f=new Font("楷体",Font.BOLD,40);
        jl.setForeground(Color.red);
        jl.setFont(f);

        JLabel jl1 = new JLabel("账　　号：");
        JLabel jl2 = new JLabel("密　　码：");
        JLabel jl3 = new JLabel("再次密码：");
        JTextField jt1 = new JTextField(15);//输入长度
        JPasswordField jt2 =new JPasswordField(15);//内容变成***
        JPasswordField jt3 =new JPasswordField(15);//内容变成***


        JButton jb1 = new JButton("确定");//按钮
        JButton jb2 = new JButton("关闭");//按钮


        setLocationRelativeTo(null);//窗口居中
        jb2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        jb1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String zero=jt1.getText();
                    String one=jt2.getText();
                    String two=jt3.getText();
                    if(one.equals("")||zero.equals("")||two.equals("")){
                        JOptionPane.showMessageDialog(null, "请输入账号密码", "警告", 0);
                    }else {
                        if (!one.equals(two)) {
                            JOptionPane.showMessageDialog(null, "二次密码不一致", "警告", 0);
                        } else {
                            if (b) {
                                boolean boo = new AccountMysql().update(zero, two);
                                if (boo) {
                                    JOptionPane.showMessageDialog(null, "修改成功", "恭喜", 1);
                                } else {
                                    JOptionPane.showMessageDialog(null, "账号不存在", "警告", 0);
                                }
                            } else {
                                boolean boo = new AccountMysql().insert(zero, two);
                                if (boo) {
                                    JOptionPane.showMessageDialog(null, "注册成功", "恭喜", 1);
                                } else {
                                    JOptionPane.showMessageDialog(null, "账号已存在", "警告", 0);
                                }

                            }
                        }
                    }
                    jt1.setText("");
                    jt2.setText("");
                    jt3.setText("");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        URL resource = LoginFrame.class.getResource("/an1.jpg");//找到图片
        Image image=Toolkit.getDefaultToolkit().getImage(resource);
        this.setIconImage(image);


        jp.add(jl);
        jp.add(jl1);
        jp.add(jt1);
        jp.add(jl2);
        jp.add(jt2);
        jp.add(jl3);
        jp.add(jt3);
        jp.add(jb1);
        jp.add(jb2);

        this.add(jp);
        jp.setOpaque(false);
    }
    private void initComponents1() {

        // 获取 窗体的内容图层
        JPanel jPanel = (JPanel)this.getContentPane();

        //内容图层设置为透明的
        jPanel.setOpaque(false);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.createImage(LoginFrame.class.getResource("/data2.jpg"));
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon);

        label.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
        // layer图层
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));

    }//差背景图片
}
