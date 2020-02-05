package com.newedu.Reptile.Music;

import com.newedu.Reptile.Windows.LoginFrame;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.annotation.Repeatable;
import java.net.URL;
import java.util.List;
import java.util.Vector;

public class Mainmusic extends JFrame {
    QQ qq;
    private static JProgressBar pb;
    private static Timer timer;
    private static int times=0;//歌词总时间
    private static int row=0;//第几首歌
    private static boolean ge=true;//
    private static JLabel jl=new JLabel("",JLabel.CENTER);//播放的歌名
    private static int max=0;//共多少首歌
    private static int prttern=0;//模式  0：播放完停止 1：单曲循环 2：随机播放 3：顺序播放

    public static void main(String[] args) {
        new Mainmusic();
    }

    public Mainmusic() {
        setUndecorated(true);//不显示标题栏
        this.getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);//采用指定的窗口装饰风格
        setTitle("音乐");//窗口标题
        setResizable(true);//窗口大小是否可改变 false不可1     true可改
        setSize(800, 550);  //窗口大小
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
        JPanel jp31 = new JPanel();
        JPanel jp32 = new JPanel();
        jp1.setLayout(new GridLayout(2, 1));
        jp11.setLayout(new GridLayout(1, 6));
        jp12.setLayout(new GridLayout(1, 1));
        jp2.setLayout(new GridLayout(1, 1));
        jp3.setLayout(new GridLayout(2, 1));
        jp31.setLayout(new GridLayout(1, 1));
        jp32.setLayout(new GridLayout(1, 5));
        jp1.setOpaque(false);
        jp11.setOpaque(false);
        jp12.setOpaque(false);

        Vector<String> heng = ve();

        Vector<Vector<String>> body =vve();

        JTable bg = new JTable();
        DefaultTableModel bgmr = new DefaultTableModel(body, heng);
        bg.setModel(bgmr);
        bg.setForeground(Color.black);
        //bg.setBackground(Color.pink);//改颜色
        JScrollPane jsp = new JScrollPane(bg);//滚动条
        jsp.setForeground(Color.cyan);

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


        jl.setForeground(Color.black);
        //jl.setBackground(Color.blue);

        JButton an1 = new JButton("单曲循环");
        JButton an2 = new JButton("随机播放");
        JButton an3 = new JButton("按顺序播放");

        an1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prttern=1;
            }
        });

        an2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prttern=2;
            }
        });
        an3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prttern=3;
            }
        });
        pb = new JProgressBar();
        //pb.setSize(500,10);
        pb.setMinimum(0);
        pb.setValue(0);//变量

        //bg.setAutoResizeMode(0);//横 有滑轮 但是有最大

        JButton an11 = new JButton("上一首歌");
        //JButton an12 = new JButton("后退");
        JButton an13 = new JButton("播放/停止");
        //JButton an14 = new JButton("快进");
        JButton an15 = new JButton("下一首歌");

        bg.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        an13.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ge=!ge;
                row = bg.getSelectedRow(); //获得选中行索引
                if(row==-1){row=0;}
                QQQ();
            }
        });//播放或停止

        an11.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(row>0){
                    ge=true;
                    QQQ();
                    ge=false;
                    row--;
                    QQQ();
                }else {
                    JOptionPane.showMessageDialog(null,"已经是第一首歌","警告",0);
                }
            }
        });//上一首歌

        an15.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(row+""+max);
                if(row<max-1){
                    if(timer!=null) {
                        ge = true;
                        QQQ();
                    }
                    ge=false;
                    row++;
                    QQQ();
                }else {
                    JOptionPane.showMessageDialog(null,"已经是最后一首歌","警告",0);
                }
            }
        });//下一首歌


        URL resource = LoginFrame.class.getResource("/qq.jpg");//找到图片
        Image image=Toolkit.getDefaultToolkit().getImage(resource);
        setIconImage(image);//换头像

        jp11.add(an1);//↑
        jp11.add(an2);//↑
        jp11.add(an3);//↑

        jp12.add(jl);

        jp1.add(jp11);
        jp1.add(jp12);

        jp2.add(jsp);

        jp3.add(jp31);
        jp3.add(jp32);

        jp31.add(pb);

        jp32.add(an11);//↓
        //jp32.add(BorderLayout.SOUTH, an12);//↓
        jp32.add(an13);//↓
        //jp32.add(an14);//↓
        jp32.add(an15);//↓

        jp2.getAutoscrolls();

        add(BorderLayout.NORTH, jp1);
        add(jp2);
        add(BorderLayout.SOUTH, jp3);

        getContentPane().add(jsp, 0); // 0表示放在面板的最顶层
        initComponents1();
        setVisible(true);
    }

    public static Vector<String> ve() {
        Vector<String> heng = new Vector<>();
        heng.add("序号");
        heng.add("歌名");
        return heng;
    }

    public static Vector<Vector<String>> vve() {
        List<String[]> list = li2();
        int a = list.size();
        Vector<Vector<String>> body = new Vector<Vector<String>>();
        for (int i = 0; i < a; i++) {
            String[] str = list.get(i);
            Vector<String> v = new Vector<>();
            for (int index = 0; index < str.length; index++) {
                v.add(str[index]);
            }
            body.add(v);
        }
        return body;
    }

    public static Vector<String[]> li2() {
        Vector<String[]> heng = new Vector<>();
        String[][] ss=lis2();
        for(int i=0;i<ss.length;i++){
            String[] s = ss[i];
            heng.add(s);
        }
        return heng;
    }

    public static String[][] lis2() {

        int[] time ={235,142,210,201,251,281,313,275,180,241

        };
        times=time[row];
       String[][] heng = {
               {"1","G.E.M. 邓紫棋 - 光年之外"},
               {"2","Lulleaux"},
               {"3","Star - 流星花园"},
               {"4","冯提莫 - 刚好遇见你"},
               {"5","潘玮柏 _ G.E.M. 邓紫棋 _ 艾热 - 攀登 (Live)"},
               {"6","平野綾 (ひらの あや) - WHITE ALBUM"},
               {"7","双笙 - 我的一个道姑朋友"},
               {"8","王旭鹏 - 英雄本色"},
               {"9","吴莫愁 - 冲一波"},
               {"10","许嵩 - 有何不可"}
       };
       max=10;
        return heng;
    }//歌词信息

    public void QQQ(){
        final int[] a = {0};
        String[] mu=li2().get(row);
        if(!ge) {
            pb.setMaximum(times);
            //jl.setText(""+mu[1]+""+"时间"+a[0]+"/"+times+"");
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    a[0]++;
                    jl.setText(""+mu[1]+"");
                    if (a[0] > times) {
                        a[0] = 0;
                        stop();
                        if(prttern==0){
                        }else if(prttern==1){
                            QQQ();
                        }else if(prttern==2){
                            row= (int)Math.random()*max;
                            QQQ();
                        }else {
                            row++;
                            if(row==max){ row=0; }
                            QQQ();
                        }
                    }
                    pb.setValue(a[0]);
                    pb.setStringPainted(true);//设置进度条显示提示信息
                    pb.setString(""+a[0]+"/"+times+"");
                }
            });
            timer.start();
            qq = new QQ(mu[1]);
            qq.start();
        }else{
           stop();
            //qq.stop();//关闭线程
        }
    }

    public void stop(){
        qq.stopnu();
        timer.stop();
    }

    private void initComponents1() {
        // 获取 窗体的内容图层
        JPanel jPanel = (JPanel)getContentPane();
        //内容图层设置为透明的
        jPanel.setOpaque(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.createImage(Mainmusic.class.getResource("/music2.jpg"));
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));

//
    }
}