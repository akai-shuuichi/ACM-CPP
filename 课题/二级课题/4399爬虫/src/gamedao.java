import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class gamedao {
    private static String ins="insert into game (name,kinds,intro,liking,subject,updatetime) values (?,?,?,?,?,?)";
   // private static String upd="update stu set password=? where name=?";
    public boolean insert(games temp)throws Exception{
        db stm=new db();
        Connection con=stm.getcon();
        PreparedStatement stat = con.prepareStatement(ins);
        stat.setString(1,temp.getGamename());
        stat.setString(2,temp.getGamekind());
        stat.setString(3,temp.getIntro());
        stat.setString(4,temp.getLiking()+"");
        stat.setString(5,temp.getSubject());
        stat.setString(6,temp.getUpdatatime());
        int a=stat.executeUpdate();
        stm.close(con,stat);//释放资源
        return a>0;
    }

  /*  public boolean updata(games kise)throws Exception{
        db stm=new db();
        Connection con=stm.getcon();
        PreparedStatement stat = con.prepareStatement(upd);
        stat.setString(1,kise.getPassword());
        stat.setString(2,kise.getName());
        int a=stat.executeUpdate();
        stm.close(con,stat);
        return a>0;
   }*/
  public void shanbiao () {

     try {
         String sql = "truncate game";
         db stm = new db();
         Connection con = stm.getcon();
         PreparedStatement stat = con.prepareStatement(sql);
         stat.execute();
     }catch (Exception e){
         e.printStackTrace();
     }
  }
   /* public boolean ishave(games temp)throws Exception{
        String sql="select *from stu where name=? and password=?";
        db stm=new db();
        Connection con=stm.getcon();
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1,temp.getName());
        stat.setString(2,temp.getPassword());
        ResultSet ans=stat.executeQuery();
        return ans.next();


    }*/
  public List<games>find(String tiaojian, String tiaojian2, String tiaojian3, String tiaojian4, String tiaojian5, int sum)throws Exception{//查询单体
      List<games> list=new ArrayList<games>();
      String temp="";
      if(tiaojian2!=null){temp+=" or intro LIKE '%"+tiaojian2+"%' or subject LIKE '%"+tiaojian2+"%' or kinds LIKE '%"+tiaojian2+"%'";};
      if(tiaojian3!=null){temp+=" or intro LIKE '%"+tiaojian3+"%' or subject LIKE '%"+tiaojian3+"%' or kinds LIKE '%"+tiaojian3+"%'";};
      if(tiaojian4!=null){temp+=" or intro LIKE '%"+tiaojian4+"%' or subject LIKE '%"+tiaojian4+"%' or kinds LIKE '%"+tiaojian4+"%'";};
      if(tiaojian5!=null){temp+=" or intro LIKE '%"+tiaojian5+"%' or subject LIKE '%"+tiaojian5+"%' or kinds LIKE '%"+tiaojian5+"%'";};
      String sql="select DISTINCT name,kinds,intro,liking,subject,updatetime from game where  liking in(select liking from game where liking = "+sum+") and (intro LIKE '%"+tiaojian+"%' or subject LIKE '%"+tiaojian+"%' or name LIKE '%"+tiaojian+"%'"+temp+") ";



      db stm=new db();
      Connection con=stm.getcon();
      PreparedStatement stat = con.prepareStatement(sql);

      ResultSet ans=stat.executeQuery();
      while(ans.next()){
          games tp=new games(ans.getString(1),ans.getString(2),ans.getString(3),ans.getInt(4),ans.getString(5),ans.getString(6));
          list.add(tp);
      }
      stm.close(con,stat);
      return list;
  }



    public List<games> findall()throws Exception{//查询全部
        List<games> list=new ArrayList<games>();
        String sql="select name,kinds,intro,liking,subject,updatetime from game ";
        db stm=new db();
        Connection con=stm.getcon();
        PreparedStatement stat = con.prepareStatement(sql);
        ResultSet ans=stat.executeQuery();
        while(ans.next()){
            games tp=new games(ans.getString(1),ans.getString(2),ans.getString(3),ans.getInt(4),ans.getString(5),ans.getString(6));
            list.add(tp);
        }
        stm.close(con,stat);
        return list;
    }

    /*public boolean delect (games kise)throws Exception{//删除
        db d=new db();
        Connection con=d.getcon();
        String sql="delete stu from stu where name=? ";
        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1,kise.getName());
        int a= stat.executeUpdate();
        d.close(con,stat);
        if(a>0)return true;
        return false;
    }*/






}

