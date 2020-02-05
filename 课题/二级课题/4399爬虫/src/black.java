import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class black extends JFrame {

    Vector<String> ls=new Vector<>();
    public black() throws Exception{
            super();
            Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\apc15\\Desktop\\你好我叫文件夹\\bf.jpg") ;
            this.setIconImage(icon);
            setTitle("黑名单系统");
            setLocation(300,300);
            setResizable(false);
            setSize(300,500) ;
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            JPanel  jp=new JPanel();
            jp.setBackground(Color.PINK);
            JLabel  jn=new  JLabel("输入黑名单");
        Font  f=new Font("华文琥珀", Font.BOLD, 30);
        jn.setFont(f);
            jn.setForeground(Color.blue);
            final JTextField  tn=new JTextField(20);
            tn.getText();
        JLabel  tn2=new  JLabel("　　　　　　　　　　　　　　");
        tn2.setForeground(Color.blue);
            JButton J1=new JButton("添加黑名单");
        J1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new cha();
                    cha.ls.add(tn.getText());
                    JLabel jo=new JLabel(tn.getText());
                    jp.add(jo);
                    setVisible(false);
                    setVisible(true);

                }
                catch(Exception ep){
                    ep.printStackTrace();
                }

            }

        });
        J1 .setFont(new  Font("华文行楷",  1,  15));
        J1.setForeground(Color.red);
        J1.setBackground(Color.yellow);
            jp.add(jn);
            jp.add(tn);
            jp.add(J1);
            jp.add(tn2);
            add(jp);
            setVisible(true);
    }
}
