import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class pa extends JFrame {
   chuang c;
    class LoginOKAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try{
            new chuang();
            setVisible(false); }
            catch(Exception p4){}
        }
    }
    public pa()
    {
        Image icon = Toolkit.getDefaultToolkit().getImage("1.jpg");
        this.setIconImage(icon);
        setBak(); //调用背景方法
        Container c = getContentPane(); //获取JFrame面板
        JPanel jp = new JPanel();
        this.addMouseListener(new MouseAdapter() {
            int flag=0;
            @Override
            public void mouseEntered(MouseEvent e) {
                if(flag++==0) {
                    JOptionPane.showMessageDialog(null, "　　　欢迎使用！", "　　　　　4399爬虫系统",
                            JOptionPane.WARNING_MESSAGE);
                    Play0 play0 = new Play0("123.mp3");
                    play0.start();
                while(true){

                    try {
                        Thread.sleep(1000);
                        System.out.println(play0.gett());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                }
            }
        });
        jp.setOpaque(false); //把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
        c.add(jp);
        this.setLayout(null);
        JButton jb1=new JButton("开始使用");
        jb1 .setFont(new  Font("华文行楷",  1,  15));
        jb1.setForeground(Color.red);
        jb1.setBackground(Color.yellow);
        jb1.setBorder(BorderFactory.createLoweredBevelBorder());
        jb1.setBounds(200,150,100,30);
        jb1.addActionListener(new LoginOKAction());
        c.add(jb1);
        JButton jb2=new JButton("进入黑名单");
        jb2 .setFont(new  Font("华文行楷",  1,  15));
        jb2.setForeground(Color.red);
        jb2.setBackground(Color.pink);
        jb2.setBorder(BorderFactory.createRaisedBevelBorder());
        jb2.setBounds(200,350,100,30);
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                new black();
            }catch (Exception e3){

                    e3.printStackTrace();
                }

            }
        });
        c.add(jb2);
        setTitle("　　　　→4399爬虫系统←");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public void setBak()//背景
    {
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("2.jpg"); //添加图片
        JLabel background = new JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
    public static void main(String[] args)
    {
        pa s = new pa();
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}