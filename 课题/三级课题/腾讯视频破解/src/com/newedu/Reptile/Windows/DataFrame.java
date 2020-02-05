package com.newedu.Reptile.Windows;

import com.newedu.Reptile.Music.Mainmusic;
import com.newedu.Reptile.Mysql.DataMysql;
import com.newedu.Reptile.Mysql.SysUsers;
import com.newedu.Reptile.Selenium.Craw;
import com.newedu.Reptile.Selenium.ReptileFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 工作信息表主页面
 */
public class DataFrame extends JFrame {
    private static boolean aBoolean=true;
    private static int kk = 40;
    private static int k = 0;//页数
    private static int co = -1;
    private static String coo = null;
    private static JTable bg;
    private static JLabel jl=new JLabel("",0);

    public static void main(String[] args) {
        new DataFrame();
    }

    public DataFrame() {
        init(0);
        initComponents1();
        setUndecorated(true);//不显示标题栏
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);//采用指定的窗口装饰风格
        setVisible(true);//窗口可见  在最后
    }

    public static void initJTable(Vector<Vector<String>> body, Vector<String> heng) {
        if(body!=null) {
            DefaultTableModel bgmr = new DefaultTableModel(body, heng);
            bg.setModel(bgmr);
        }
        //bg.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 20));
        jl.setForeground(Color.blue);
        bg.setForeground(Color.black);
        //bg.setFont(new Font("微软雅黑", Font.BOLD, 20));
        numb();
    }

    public static Vector<String> ve() {
        Vector<String> heng = new Vector<>();
        heng.add("ID");
        heng.add("电影名字");
        heng.add("类型");
        heng.add("第几集");
        heng.add("链接");
        heng.add("备注");
        return heng;
    }

    public static void numb(){
        int a= 0;
        try {
            if(aBoolean){
                a = new DataMysql().querysome();
            }else {
                a=new DataMysql().querypart(co,coo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if((a/kk)*kk==a){
            jl.setText("第" + (k + 1) +"/"+(a/kk)+ "页");
            if(k+1>a/kk){
                k--;
                initJTable(vve(), ve());
            }
        }else{
            jl.setText("第" + (k + 1) +"/"+(a/kk+1)+ "页");
        }
    }//页码管理

    public static List<String[]> li2() {
        DataMysql c = new DataMysql();
        List<String[]> list = new ArrayList<String[]>();
        List<SysUsers> list1=null;
        try {
            if(aBoolean==true){
                list1 = c.querysome(k, kk);
               if(list1!=null)
                for(int i=0;i<list1.size();i++){
                    list.add(list1.get(i).getall());
                }
            }else {
                list1=c.querypart(co,coo,k,kk);
                if(list1!=null)
                for(int i=0;i<list1.size();i++){
                    list.add(list1.get(i).getall());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }//获取数据库数据

    public static Vector<Vector<String>> vve() {
        List<String[]> list = li2();
        int a = list.size();
        Vector<Vector<String>> body = new Vector<Vector<String>>();
        for (int i = 0; i < a; i++) {
            String[] str = list.get(i);
            Vector<String> v = new Vector<>();
            for (int index = 0; index < str.length; index++) {
//                v.add(str);
                v.add(str[index]);
            }
            body.add(v);
        }

        if(body.isEmpty()){
            return null;
        }
        else {
            return body;
        }
    }//把List<String[]>类型转换成Vector<Vector<String>>

    public void init(int kkk) {
        k=kkk;
        //jf.setBackground(Color.red);//窗口颜色
        setTitle("大数据");//窗口标题
        setResizable(true);//窗口大小是否可改变 false不可1     true可改
        setSize(900, 700);  //窗口大小
        //jf.setLocation(1000,100);    //位置
        setLocationRelativeTo(null);//窗口居中
        //HIDE_ON_CLOSE    点击关闭时隐藏
        //EXIT_ON_CLOSE    点击关闭时退出
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel jp1 = new JPanel();
        JPanel jp11 = new JPanel();
        JPanel jp12 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        jp1.setLayout(new GridLayout(2, 1));
        jp11.setLayout(new GridLayout(1, 1));
        jp12.setLayout(new GridLayout(1, 6));
        jp2.setLayout(new GridLayout(1, 1));
        jp3.setLayout(new GridLayout(1, 5));

        jp1.setOpaque(false);
        jp11.setOpaque(false);
        jp12.setOpaque(false);
        jp2.setOpaque(false);
        jp3.setOpaque(false);//把此类型变透明

        Vector<String> heng = ve();
        Vector<Vector<String>> body = vve();

        bg = new JTable();
        initJTable(body, heng);
        //bg.setBackground(Color.pink);//改颜色
        JScrollPane jsp = new JScrollPane(bg);//滚动条

        /**
         * 把表格省魔的弄透明
         */

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setOpaque(false); //将渲染器设置为透明
        JTableHeader header = bg.getTableHeader();//获取头部
        header.setOpaque(false);//设置头部为透明
        header.getTable().setOpaque(false);//设置头部里面的表格透明
        bg.setDefaultRenderer(Object.class,render);
        header.setDefaultRenderer(render);
        jsp.setOpaque(false);
        jsp.setColumnHeaderView(header);//设置头部（HeaderView部分）
        jsp.getColumnHeader().setOpaque(false);//再取出头部，并设置为透明
        jsp.getViewport().setOpaque(false);//将JScrollPane设置为透明


        JTextField jt = new JTextField(10);//输入长度
        JButton an1 = new JButton("查询");
        JButton an2 = new JButton("添加");
        JButton an3 = new JButton("修改");
        JButton an4 = new JButton("删除");
        JButton an5 = new JButton("爬取");
        JButton an6 = new JButton("打开链接");

        JMenuBar jmb = new JMenuBar();// 菜单栏  创造菜单
        JMenu  jm1=new JMenu("账号管理");
        JMenu  jm2=new JMenu("娱乐");

        JMenuItem jmi=new JMenuItem("音乐列表");
        JMenuItem jmi1=new JMenuItem("修改密码");
        JMenuItem jmi2=new JMenuItem("账号注销");
        JMenuItem jmi3=new JMenuItem("退出登录");
        jmb.add(jm1);
        jmb.add(jm2);
        jm2.add(jmi);
        jm1.add(jmi1);
        jm1.addSeparator();//分开
        jm1.add(jmi2);
        jm1.addSeparator();//分开
        jm1.add(jmi3);


        //bg.setAutoResizeMode(0);//横 有滑轮 但是有最大
        //JScrollBar js=new JScrollBar();
        //JScrollBar(bg);

        JButton an11 = new JButton("首页");
        JButton an12 = new JButton("上一页");
        JButton an13 = new JButton("下一页");
        JButton an14 = new JButton("尾页");



        an11.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                k=0;
                initJTable(vve(), ve());
            }
        });//首页

        an12.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (k > 0) {
                    k--;
                    initJTable(vve(), ve());
                } else {
                    JOptionPane.showMessageDialog(null, "已经是第一页", "警告", 2);
                }
            }
        });//上一页

        an13.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                k++;
                if(vve()!=null){
                    initJTable(vve(), ve());
                } else {
                    k--;
                    JOptionPane.showMessageDialog(null, "已经是最后一页", "警告", 2);
                }
            }
        });//下一页
        an14.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                while(vve()!=null){
                    k++;
                }
                k--;
                initJTable(vve(), ve());
            }
        });//尾页

        //bg.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        an1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                co=-1;
                co = bg.getSelectedColumn();//获得选中列索引
                coo=jt.getText();
                if(co==-1){
                    JOptionPane.showMessageDialog(null, "请选择", "警告", 0);
                    aBoolean = true;
                    initJTable(vve(), ve());
                } else {
                    if (coo.equals("") || coo == null) {
                        JOptionPane.showMessageDialog(null, "请输入查询的内容", "警告", 0);
                        aBoolean = true;
                        initJTable(vve(), ve());
                    } else {
                        aBoolean = false;
                        k = 0;
                        if (vve() == null) {
                            JOptionPane.showMessageDialog(null, "查询不到", "警告", 0);
                            aBoolean = true;
                            initJTable(vve(), ve());
                        } else {
                            initJTable(vve(), ve());
                        }
                    }
                }

            }
        });//查询

        an2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AddData();
            }
        });//添加

        an4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<String[]> listSy1 = li2();
                int row = bg.getSelectedRow(); //获得选中行索引
                if(row==-1){
                    JOptionPane.showMessageDialog(null, "请选择", "警告", 0);
                } else {
                    String[] str = listSy1.get(row);
                    //String[] str = listSy.get(row).getall();
                    try {
                        if (new DataMysql().delete(new SysUsers().getall(str))) {
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
        });//删除

        an3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<String[]> listSy = li2();
                List<String[]> listSy1 = li2();
                int row = bg.getSelectedRow(); //获得选中行索引
                int col = bg.getSelectedColumn();//获得选中列索引
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "请选择", "警告", 0);
                } else if(col==0){
                    JOptionPane.showMessageDialog(null, "序号不可修改", "警告", 0);
                    jt.setText("");
                } else {
                    String[] str = listSy.get(row);
                    String[] str2 = listSy1.get(row);
                    String s = jt.getText();
                    if(str2[col].equals(s)){
                        JOptionPane.showMessageDialog(null, "修改的数据与原数据一样", "警告", 0);
                    }else {
                        str2[col] = s;
                        try {
                            if (new DataMysql().update(new SysUsers().getall(str2), new SysUsers().getall(str))) {
                                JOptionPane.showMessageDialog(null, "修改成功", "恭喜", 1);
                                initJTable(vve(), ve());
                            } else {
                                JOptionPane.showMessageDialog(null, "修改失败", "警告", 0);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });//修改
        bg.addMouseListener(new MouseAdapter() {    //鼠标事件
            public void mouseClicked(MouseEvent e) {
                int row = bg.getSelectedRow(); //获得选中行索引
                String[] str = li2().get(row);
                int col = bg.getSelectedColumn();//获得选中列索引
                jt.setText(str[col]);
            }
        });

        an6.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = bg.getSelectedRow(); //获得选中行索引
                String[] str = li2().get(row);
                //new Craw(str[4]);
                if(str[5].equals("need urls")){
                    new Craw(str[4]).start();
                }else {
                    try {
                        ProcessBuilder proc = new ProcessBuilder(
                                "C:\\Users\\张蛟龙\\AppData\\R" +
                                        "oaming\\360se6\\App" +
                                        "lication\\360se.exe",
                                "https://www.8090g.cn/?url=" + str[4]);
                        proc.start();
                    } catch (Exception e) {
                    }
                }
            }
        });//打开链接

        jp12.add(jt);
        jp12.add(an1);//↑
        jp12.add(an2);//↑
        jp12.add(an3);//↑
        jp12.add(an4);//↑
        jp12.add(an5);//↑
        jp12.add(an6);//↑
        jp11.add(jmb);//↑
        jp1.add(jp11);
        jp1.add(jp12);

        jp2.add(jsp);
        //jp.add(bg);

        jp3.add(an11);//↓
        jp3.add(BorderLayout.SOUTH, an12);//↓
        jp3.add(jl);
        jp3.add(an13);//↓
        jp3.add(an14);//↓

        //jf.setLayout(new GridLayout(3,1));
        jp2.getAutoscrolls();

        this.add(BorderLayout.NORTH, jp1);
        add(jp2);
        add(BorderLayout.SOUTH, jp3);

        an5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ReptileFrame();
                dispose();
            }
        });

        //音乐列表
        jmi.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Mainmusic();
            }
        });

        jmi1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AccountModify(true);
            }
        });//修改密码

        jmi2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AccountLogoff();
            }
        });//账号注销

        jmi3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new LoginFrame();
                dispose();
            }
        });//退出登录
    }

    private void initComponents1() {
        // 获取 窗体的内容图层
        JPanel jPanel = (JPanel)getContentPane();
        //内容图层设置为透明的
        jPanel.setOpaque(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.createImage(Mainmusic.class.getResource("/4.png"));
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
    }//透明
}
