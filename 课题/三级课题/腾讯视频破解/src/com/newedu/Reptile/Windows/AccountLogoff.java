package com.newedu.Reptile.Windows;

import com.newedu.Reptile.Mysql.AccountMysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AccountLogoff extends JFrame {//账号注销

    public AccountLogoff() {
        init();
        setVisible(true);//窗口可见  在最后
    }

    private void init() {
        JPanel jp = new JPanel();
        setTitle("状态");//窗口标题
        setResizable(false);//窗口大小是否可改变 false不可1     true可改
        setSize(220, 250);
        setLocationRelativeTo(null);//窗口居中
        JLabel jll=new JLabel("账号注销",JLabel.CENTER);
        Font f=new Font("楷体",Font.BOLD,40);
        jll.setForeground(Color.blue);
        jll.setFont(f);

        JLabel jl1 = new JLabel("账　　号：");
        JTextField jt1 = new JTextField(18);//输入长度
        JLabel jl2 = new JLabel("密　　码：");
        JPasswordField jt2 =new JPasswordField(18);//内容变成***

        JButton jb1 = new JButton("确定");//按钮
        JButton jb2 = new JButton("关闭");//按钮

        jb1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AccountMysql a=new AccountMysql();
                try {
                    String zero = jt1.getText();
                    String one = jt2.getText();
                    jt1.setText("");
                    jt2.setText("");
                    if(a.delete(zero,one))
                    {
                        JOptionPane.showMessageDialog(null,"删除成功","hello",1);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"删除失败","hello",0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //dispose();
            }
        });

        jb2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        jp.add(jll);

        jp.add(jl1);
        jp.add(jt1);
        jp.add(jl2);
        jp.add(jt2);

        jp.add(jb1);
        jp.add(jb2);
        this.add(jp);

    }
}
