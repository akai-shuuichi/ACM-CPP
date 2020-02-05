import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class chuang extends JFrame{
    private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
    cha c;
    int count=10;
    public chuang() throws Exception{
        super();





        Image icon = Toolkit.getDefaultToolkit().getImage("3.jpg");
        this.setIconImage(icon);
        setTitle("爬虫搜索");
        setLocation((1920-300)/2,(1080-600)/2);
        setResizable(false);
        setSize(300,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Icon  img=new ImageIcon("3.jpg");
        //通过标签来 添加 图片
        final JLabel  pic=new JLabel(img);
        JPanel  jp=new JPanel();
        jp.setBackground(Color.GRAY);
        JLabel  jname1=new  JLabel("游戏名称❀");
        jname1.setForeground(Color.RED);
        final JTextField  tname1=new JTextField(20);
        tname1.getText();
        JLabel  jname2=new  JLabel("游戏类型◇");
        jname2.setForeground(Color.RED);
        final JTextField  tname2=new JTextField(20);
        tname2.getText();
        JLabel  jname3=new  JLabel("游戏简介△");
        jname3.setForeground(Color.RED);
        final JTextField  tname3=new JTextField(20);
        tname3.getText();
        JLabel  jname4=new  JLabel("　　　　　　　　游戏热度🔥　　　　　　　　");
        jname4.setForeground(Color.RED);
        JRadioButton  jb1=new JRadioButton("①❤");
        JRadioButton  jb2=new JRadioButton("②❤");
        JRadioButton  jb3=new JRadioButton("③❤");
        JRadioButton  jb4=new JRadioButton("④❤");
        JRadioButton  jb5=new JRadioButton("⑤❤");
        jb1.setContentAreaFilled(false);
        jb1.setBorderPainted(false);
        jb2.setContentAreaFilled(false);
        jb2.setBorderPainted(false);
        jb3.setContentAreaFilled(false);
        jb3.setBorderPainted(false);
        jb4.setContentAreaFilled(false);
        jb4.setBorderPainted(false);
        jb5.setContentAreaFilled(false);
        jb5.setBorderPainted(false);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count=0;
                }
            });
        jb2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            count=4;
        }
    });
        jb3.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
        count=6;
        }
        });
        jb4.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
        count=8;
        }
        });
        jb5.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
        count=10;
        }
        });

        jb5.setSelected(true);
        ButtonGroup  bg=new ButtonGroup();
        bg.add(jb1);
        bg.add(jb2);
        bg.add(jb3);
        bg.add(jb4);
        bg.add(jb5);
        JLabel  jname5=new  JLabel("游戏专栏☆");
        jname5.setForeground(Color.RED);
        final JTextField  tname5=new JTextField(20);
        tname5.getText();
        JLabel  jname6=new  JLabel("更新时间□");
        jname6.setForeground(Color.RED);
        final JTextField  tname6=new JTextField(20);
        tname6.getText();
        JButton JB1=new JButton("开始搜索");

        JB1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try{ c=new cha(tname1.getText(),tname2.getText(),tname3.getText(),tname5.getText(),tname6.getText(),count);addIFame(c);}
               catch(Exception ep){

               }

            }
        });
        JButton JB2=new JButton("清空记录");
        JB2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tname1.setText("");
                tname2.setText("");
                tname3.setText("");
                jb5.setSelected(true);
                tname5.setText("");
                tname6.setText("");
                gamedao temp=new gamedao();
                temp.shanbiao();
                c.setVisible(false);
            }
        });
        JB1.setBackground(Color.green);
        JB1.setBounds(50,500,100,30);
        JB1.setBounds(0,0,100,20);
        JB2.setBackground(Color.yellow);
        jp.add(pic);
        jp.add(jname1);
        jp.add(tname1);
        jp.add(jname2);
        jp.add(tname2);
        jp.add(jname3);
        jp.add(tname3);
        jp.add(jname4);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.add(jb4);
        jp.add(jb5);
        jp.add(jname5);
        jp.add(tname5);
        jp.add(jname6);
        jp.add(tname6);
        jp.add(JB1);
        jp.add(JB2);
        add(jp);
        JMenuBar jmb=new JMenuBar();
        JMenu col=new JMenu("颜色");
        JMenuItem red=new JMenuItem("绿色");
        col.add(red);
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.setBackground(Color.GREEN);
            }
        });
        JMenuItem gray=new JMenuItem("灰色");
        col.add(gray);
        gray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.setBackground(Color.GRAY);
            }
        });
        JMenuItem yellow=new JMenuItem("黄色");
        col.add(yellow);
        yellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.setBackground(Color.YELLOW);
            }
        });
        JMenuItem pink=new JMenuItem("粉色");
        col.add(pink);

        pink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.setBackground(Color.PINK);
            }
        });
        JMenuItem black=new JMenuItem("黑色");
        col.add(black);
        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.setBackground(Color.black);
            }
        });
        JMenuItem blue=new JMenuItem("蓝色");
        col.add(blue);
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.setBackground(Color.blue);
            }
        });

         jmb.add(col);
        jp.add(jmb);
        setVisible(true);
    }

    public static void addIFame(JFrame iframe) { // 添加子窗体的方法
        DESKTOP_PANE.add(iframe);
    }
    public static void main(String[] args)throws Exception {
        new chuang();
    }
}
