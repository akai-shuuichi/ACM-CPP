import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class readhtml {

    public  void read(String tiaojian)throws Exception{
        read(1,tiaojian);
    }

    public  boolean read(int page,String tiaojian) throws Exception {
        Document doc = Jsoup.connect("http://so2.4399.com/search/search.php?p="+page+"&k="+tiaojian+"&view=list&sort=rel").get();
        Elements es = doc.getElementsByClass("fr_d");
        Elements next=doc.getElementsByClass("pag_so");
        if(next.last()==null)return false;
        boolean flag=nextpage(next.last().html());
        gamedao dao=new gamedao();
        System.out.println(page);
        for (int i = 0; i < es.size(); i++) {
            games tpgame=new games();
            String stmsg=es.get(i).getElementsByClass("pop").get(0).html();//人气 时间 名称 简介
            String[] msg3=stmsg.split("span>人气：<i class=");
            tpgame.setLiking(getstar(msg3[1]));
            msg3=stmsg.split("更新时间：");
            String time[]= msg3[1].split("</span>");
            tpgame.setUpdatatime(time[0]);
            tpgame.setGamename(getname(msg3[1]));
            tpgame.setIntro(es.get(i).getElementsByTag("p").html());
            tpgame.setGamekind(es.get(i).getElementsByClass("green1").get(0).html());
            tpgame.setSubject(getsubject(es.get(i).getElementsByClass("green1")));
            dao.insert(tpgame);
        }
        if(nextpage(next.last().html()))read(page+1,tiaojian);
        return true;
    }
    public static boolean nextpage(String temp){
        if(temp.indexOf("下一页")==-1)return false;
        else return true;


    }
    public static String getsubject(Elements temp){
int len=temp.size();
String ans="";
for(int i=1;i<len;i++){
    ans+=temp.get(i).html()+" ";
}
return ans;
  }
    public static String getintro(String temp){
        String p1[]=temp.split("<p>");
        String p2[]=p1[1].split("</p>");
        return p2[0];
    }
    public static String getname(String temp){
        //System.out.println(temp);
        String tname[]=temp.split(";return true;\">");
        String name[]=tname[1].split("</a></b>");
        String s1[]=name[0].split("</font>");
        String ans;
        if(s1.length==2){
            String s2[]=s1[0].split("<font color=\"red\">");
            ans=s2[0]+s2[1]+s1[1];
            //System.out.println(ans);

            return ans;
        }
        //System.out.println(s1[0]);
        String t[]=s1[0].split("<font color=\"red\">");
        String k="";
        for(int i=0;i<t.length;i++)k+=t[i];
        //System.out.println(k);
        return k;


    }

    public static int getstar(String temp){
        int ans=temp.charAt(2)-'0';
        if(temp.charAt(3)=='0')return 10;
        else return ans;
  }
}