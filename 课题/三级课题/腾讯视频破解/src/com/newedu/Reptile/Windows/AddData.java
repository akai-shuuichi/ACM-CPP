package com.newedu.Reptile.Windows;
import com.newedu.Reptile.Mysql.DataMysql;
import com.newedu.Reptile.Mysql.SysUsers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddData extends JFrame {
    public static void main(String[] args) {
        new AddData();
    }
    public AddData(){
        init();
        initadd();
        initComponents1();
        setVisible(true);//窗口可见  在最后
    }

    private void initadd() {
        JPanel jp=new JPanel();
        JLabel jl1=new JLabel("电影名字",JLabel.CENTER);//改颜色;
        JLabel jl2=new JLabel("类型",JLabel.CENTER);
        JLabel jl3=new JLabel("第几集",JLabel.CENTER);
        JLabel jl4=new JLabel("链接",JLabel.CENTER);
        JLabel jl5=new JLabel("备注",JLabel.CENTER);
        Font f=new Font("楷体",Font.CENTER_BASELINE,12);
        Color colo=Color.black;
        jl1.setForeground(colo);
        jl2.setForeground(colo);
        jl3.setForeground(colo);
        jl4.setForeground(colo);
        jl5.setForeground(colo);





        jl1.setFont(f);
        jl2.setFont(f);
        jl3.setFont(f);
        jl4.setFont(f);
        jl5.setFont(f);
        JTextField jt1 = new JTextField(20);//输入长度
        JTextField jt2 = new JTextField(20);//输入长度
        JTextField jt3 = new JTextField(20);//输入长度
        JTextField jt4 = new JTextField(20);//输入长度
        JTextField jt5 = new JTextField(20);//输入长度

        jp.add(jl1);jp.add(jt1);
        jp.add(jl2);jp.add(jt2);
        jp.add(jl3);jp.add(jt3);
        jp.add(jl4);jp.add(jt4);
        jp.add(jl5);jp.add(jt5);

        JButton jb1=new JButton("确定");
        JButton jb2=new JButton("关闭");
        jb2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        jb1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SysUsers sy=new SysUsers();
                sy.setId(0);
                sy.setName(jt1.getText());
                sy.setType(jt2.getText());
                sy.setNumber(jt3.getText());
                sy.setLink(jt4.getText());
                sy.setSpare(jt5.getText());
                try {
                    new DataMysql().insert(sy);

                    JOptionPane.showMessageDialog(null, "插入成功", "恭喜", 1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        jp.add(jb1);
        jp.add(jb2);

        jp.setOpaque(false);

        add(jp);
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

    private void init() {
        setTitle("添加内容");//窗口标题
        setResizable(false);//窗口大小是否可改变 false不可1     true可改
        setSize(250,500);
        setLocationRelativeTo(null);//窗口居中
    }

}
