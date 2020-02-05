package com.newedu.Reptile.Mysql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;
import java.util.Vector;

public class AccountMysql {

    private static QueryRunner queryRunner = new QueryRunner(JDPC.getDataSource());

    public boolean insert(String username, String pwd) throws Exception {
        //获取
        String sql1="select count(*) from account where username =?";
        String sql= "insert into account (username,pwd) values (?,?)";//插入
        Object[] ob=new Object[]{username,pwd};
        Object query = queryRunner.query(sql1, new ScalarHandler<>(),ob[0]);//0:成功      1：失败
        int a=query.hashCode();
        if(a==0){
            int a1=queryRunner.update(sql,ob);//0:插入成功      1：插入失败
            return a1!=0;
        }else {
            return false;
        }
    }//插入

    public List<SysUser> query()throws Exception{
        String sql="select * from account";
        List<SysUser> list= queryRunner.query(sql,new BeanListHandler<SysUser>(SysUser.class));
        return list;
    }//查询

    public boolean update(String username, String pwd) throws Exception {
        //获取
        String sql= "update account set username=?,pwd=? where username= ? ";//修改
        Object[] ob=new Object[]{username,pwd,username};
        int a=queryRunner.update(sql,ob);//0:修改成功      1：修改失败
        return a!=0;
    }//修改

    public boolean updateadmin(String username, String pwd,String name) throws Exception {
        //获取
        String sql= "update account set username=?,pwd=? where username= ? ";//修改
        Object[] ob=new Object[]{username,pwd,name};
        int a=queryRunner.update(sql,ob);//0:修改成功      1：修改失败
        return a!=0;
    }//修改

    public boolean login(String username, String pwd) throws Exception{
        String sql = "select count(*) from account where username =? and pwd =?";//查找
        Object[] ob=new Object[]{username,pwd};
        Object query = queryRunner.query(sql, new ScalarHandler<>(),ob);//0:成功      1：失败
        int a=query.hashCode();
        return a!=0;
    }//登录

    public SysUser querylogin(String username)throws Exception{
        String sql="select * from account where username =?";
        Object ob=username;
        SysUser sy= queryRunner.query(sql,new BeanHandler<SysUser>(SysUser.class), ob);
        return sy;
    }//登录查询

    public boolean delete(String username,String pwd) throws Exception{
        String sql="delete  from account where username=? and pwd=?";
        Object[] ob=new Object[]{username,pwd};
        int a=queryRunner.execute(sql,ob);//0:失败     1：成功
        return a!=0;
    }//删除

    public static void main(String[] args) {
        try {
            List<SysUser> list=new AccountMysql().query();
            Vector<String[]> v=new Vector<>();
            for(int i=0;i<list.size();i++){
                v.add(list.get(i).getall());
                System.out.println(list.get(i).getall());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


