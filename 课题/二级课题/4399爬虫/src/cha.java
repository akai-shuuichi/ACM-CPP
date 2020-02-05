import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class cha extends JFrame{
    cha(){};
    static Vector<String> ls=new Vector<>();
    public cha(String pp,String p2,String p3,String p4,String p5,int count ) throws Exception{
        super();
        Image icon = Toolkit.getDefaultToolkit().getImage("3.jpg");
        this.setIconImage(icon);
        setTitle("查询结果");
        setLocation((1100),0);
        setResizable(true);
        setSize(800,800);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        gamedao temp=new gamedao();
        readhtml r=new readhtml();
        boolean flag= r.read(1,pp);
        Vector<Vector<String>> body=new Vector<Vector<String>>();
         games p=new games();
        List<games>  kise=temp.find(pp,p2,p3,p4,p5,count);
        int len=kise.size();
        System.out.println(len);
        DefaultTableModel tm=null;
        if(flag){
        for(int i=0;i<len;i++){
            Vector<String> tp=new Vector<>();
            kise.get(i);

            tp.add(kise.get(i).getGamename());
            tp.add(kise.get(i).getGamekind());
            tp.add(kise.get(i).getIntro());
            tp.add(kise.get(i).getLiking()+"");
            tp.add(kise.get(i).getSubject());
            tp.add(kise.get(i).getUpdatatime());
            boolean fl=true;
            //System.out.println(fl);
            for(int j=0;j<ls.size();j++){
                if(kise.get(i).getGamename().indexOf(ls.get(j))!=-1){fl=false;
                   // System.out.println(kise.get(i).getGamename()+ls.get(j));
                }

            }
          //  System.out.println(fl);
           if(fl) body.add(tp);
        }
        Vector<String> head=new Vector<String>();
        head.add("游戏名称");
        head.add("游戏类型");
        head.add("游戏简介");
        head.add("游戏热度");
        head.add("游戏专栏");
        head.add("更新时间");
         tm=new DefaultTableModel(body,head);}
        else {
            Vector<String> head=new Vector<String>();
            head.add("游戏名称");
            head.add("游戏类型");
            head.add("游戏简介");
            head.add("游戏热度");
            head.add("游戏专栏");
            head.add("更新时间");
             tm=new DefaultTableModel(body,head);

        }
        JTable tp=new JTable(tm);

        tp.setBackground(Color.PINK);
        JScrollPane  jsp=new JScrollPane(tp);
        add(jsp);
        setVisible(true);
    }
}