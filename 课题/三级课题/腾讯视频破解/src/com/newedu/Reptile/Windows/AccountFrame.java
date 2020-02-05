package com.newedu.Reptile.Windows;

import com.newedu.Reptile.Music.Mainmusic;
import com.newedu.Reptile.Mysql.SysUser;
import com.newedu.Reptile.Mysql.AccountMysql;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.util.List;

public class AccountFrame extends JFrame{

    public static void main(String[] args) {
        new AccountFrame();
    }
    private static JTable bg;
    private static  Vector<String> heng =null;
    private static Vector<Vector<String>> body=null;
    public AccountFrame() {
        try {
            init();

        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);//窗口可见  在最后
    }

    public static Vector<String> ve() {
        Vector<String> heng =new Vector<>();
        heng.add("账号");
        heng.add("密码");
        return heng;
    }

    public Vector<Vector<String>> vve() throws Exception {
        AccountMysql cc = new AccountMysql();
        List<SysUser> list=cc.query();
        body=new Vector<Vector<String>>();
        int a=list.size();
        for(int i=0;i<a;i++) {
            SysUser str=list.get(i);
            Vector<String> v=new Vector();
            v.add(str.getUsername());
            v.add(str.getPwd());
            body.add(v);
        }
        return body;
    }

    public static void initJTable(Vector<Vector<String>> body, Vector<String> heng) {
        DefaultTableModel bgmr = new DefaultTableModel(body, heng);
        bg.setModel(bgmr);
    }

    public  void init() throws Exception {

        setBackground(Color.pink);
        setTitle("管理员");//窗口标题
        setResizable(true);//窗口大小是否可改变 false不可1     true可改
        setSize(300,400);//窗口大小
        //setLocation(1000,100);//位置
        setLocationRelativeTo(null);//窗口居中
        //HIDE_ON_CLOSE    点击关闭时隐藏
        //EXIT_ON_CLOSE    点击关闭时退出
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel jp1=new JPanel();
        JPanel jp2=new JPanel();
        JPanel jp3=new JPanel();
        jp1.setLayout(new GridLayout(1, 2));
        jp2.setLayout(new GridLayout(1, 1));
        jp3.setLayout(new GridLayout(1, 5));

//        jp1.setOpaque(false);

        heng = ve();
        body = vve();

//        Vector<String> heng =new Vector<>();
//        heng.add("账号");
//        heng.add("密码");
//        AccountMysql cc = new AccountMysql();
//
//        List<SysUser> list=cc.query();
//        Vector<Vector<String>>   body=new Vector<Vector<String>>();
//        int a=list.size();
//        for(int i=0;i<a;i++) {
//            SysUser str=list.get(i);
//            Vector<String> v=new Vector();
//            v.add(str.getUsername());
//            v.add(str.getPwd());
//            body.add(v);
//        }
//
////        List<SysUser> list=cc.query();
////        Vector<String[]>   body=new Vector<String[]>();
////        int a=list.size();
////        for(int i=0;i<a;i++) {
////            body.add(list.get(i).getall());
////        }
//
//        DefaultTableModel bgmr = new DefaultTableModel(body,heng);
        bg =new JTable();  //表格
        initJTable(body, heng);
        bg.setBackground(Color.pink);//改颜色
        JScrollPane  jsp=new JScrollPane(bg);//滚动条




        JTextField jt = new JTextField(10);//输入长度
 //       JButton an1 = new JButton("查询");
        JButton an2 = new JButton("修改");

        JButton an11 = new JButton("添加");
        JButton an12 = new JButton("删除");
        JButton an13 = new JButton("退出");

        jp1.add(jt);
//        jp1.add(an1);
        jp1.add(an2);

        jp2.add(jsp);

        jp3.add(an11);
        jp3.add(an12);
        jp3.add(an13);

        add(BorderLayout.NORTH,jp1);
        add(jp2);
        add(BorderLayout.SOUTH,jp3);

        an2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = bg.getSelectedRow(); //获得选中行索引
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "请选择", "警告", 0);
                } else {
                    int col = bg.getSelectedColumn();//获得选中列索引
                    Vector<String> str = body.get(row);
                    String s = jt.getText();
                    if (s.equals("") || s == null) {
                        JOptionPane.showMessageDialog(null, "请输入所改的内容", "警告", 0);
                    } else {
                        if (col == 1) {
                            try {
                                if (new AccountMysql().update(str.get(0), s)) {
                                    JOptionPane.showMessageDialog(null, "修改成功", "恭喜", 1);
                                    initJTable(vve(), ve());
                                } else {
                                    JOptionPane.showMessageDialog(null, "修改失败", "警告", 0);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                SysUser sy = new AccountMysql().querylogin(s);

                                if (sy == null) {
                                    if (new AccountMysql().updateadmin(s, str.get(1), str.get(0))) {
                                        JOptionPane.showMessageDialog(null, "修改成功", "恭喜", 1);
                                        initJTable(vve(), ve());
                                    } else {
                                        JOptionPane.showMessageDialog(null, "账号已存在", "警告", 0);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "账号已存在", "警告", 0);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        an13.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        an11.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AccountModify(false);
            }
        });//注册

        an12.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = bg.getSelectedRow(); //获得选中行索引
                if(row==-1){
                    JOptionPane.showMessageDialog(null, "请选择", "警告", 0);
                }else{
                    Vector<String> str=body.get(row);
                    String username=str.get(0);
                    String pwd=str.get(1);
                    try {
                        if (new AccountMysql().delete(username,pwd)) {
                            JOptionPane.showMessageDialog(null, "删除成功", "恭喜", 1);
                            initJTable(vve(), ve());
                        } else {
                            JOptionPane.showMessageDialog(null, "删除失败", "警告", 0);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        bg.addMouseListener(new MouseAdapter() {    //鼠标事件
            public void mouseClicked(MouseEvent e) {
                int row = bg.getSelectedRow(); //获得选中行索引
                Vector<String> str=body.get(row);
                int col = bg.getSelectedColumn();//获得选中列索引
                jt.setText(str.get(col));
            }
        });
    }

}
