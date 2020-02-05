package com.newedu.Reptile.Mysql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public  class DataMysql {
//    private Connection conn;
//    private PreparedStatement stmt = null;
//    private ResultSet rs = null;


    private static QueryRunner queryRunner = new QueryRunner(JDPC.getDataSource());

    public static void main(String[] args) {
        try {
            System.out.println(new DataMysql().querysome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insert(SysUsers sss) throws Exception {
        String sql = "insert into tencent (name,type,number,link,spare) values (?,?,?,?,?)";//插入
        Object[] ob={sss.getName(), sss.getType(),
                sss.getNumber(), sss.getLink(), sss.getSpare()};
        return queryRunner.update(sql,ob)!=0;
    }//插入

    public boolean insert1(SysUsers sss) throws Exception {
        String sql = "insert into  tencent (id,name,type,number,link,spare) values (?,?,?,?,?)";//插入
        Object[] ob={sss.getName(), sss.getType(),
                sss.getNumber(), sss.getLink(), sss.getSpare()};
        return queryRunner.update(sql,ob)!=0;
    }


    public int querysome() throws Exception {
        String sql = "select count(*) from tencent";
        Object query = queryRunner.query(sql, new ScalarHandler<>());//0:成功      1：失败
        int a=query.hashCode();
        return a;
    }//查询总页

    public int querypart(int m, String na) throws Exception {
        String sql = null;
        if(m==0){
            sql = "select count(*) from tencent where id like ?";
        } else if(m==1){
            sql = "select count(*) from tencent where name like ?";
        }else if(m==2){
            sql = "select count(*) from tencent where type like ?";
        } else if(m==3){
            sql = "select count(*) from tencent where number like ? ";
        } else if(m==4){
            sql = "select count(*) from tencent where link like ?";
        }else if(m==5){
            sql = "select count(*) from tencent where spare like ?";
        }else {
            return 0;
        }
        Object ob="%"+na+"%";
        Object query = queryRunner.query(sql, new ScalarHandler<>(),ob);//0:成功      1：失败
        int a=query.hashCode();
        return a;
    }//查询总页


    public List<SysUsers> querysome(int m, int n) throws Exception {
        if(querysome()!=0) {
            String sql = "select distinct * from tencent limit ?,?";
            Object[] ob = new Object[]{m * n, n};
            List<SysUsers> list = queryRunner.query(sql, new BeanListHandler<SysUsers>(SysUsers.class), ob);

            if (list.isEmpty()) {
                list = null;
            }
            return list;
        }
        else {
            return null;
        }
    }//查询

    public List<SysUsers> querypart(int m, String na, int k, int kk) throws Exception {
        String sql=null;
        if(m==0){
            sql = "select distinct * from tencent where id like ? limit ?,? ";
        } else if(m==1){
            sql = "select distinct * from tencent where name like ? limit ?,? ";
        }else if(m==2){
            sql = "select distinct * from tencent where type like ? limit ?,? ";
        } else if(m==3){
            sql = "select distinct * from tencent where number like ? limit ?,? ";
        } else if(m==4){
            sql = "select distinct * from tencent where link like ? limit ?,? ";
        }else if(m==5){
            sql = "select distinct * from tencent where spare like ? limit ?,? ";
        }else {
            return null;
        }
        Object[] ob=new Object[]{"%"+na+"%",k*kk,kk};
        List<SysUsers> list= queryRunner.query(sql,new BeanListHandler<SysUsers>(SysUsers.class),ob);
        if(list.isEmpty()){
            list=null;
        }
        return list;
    }//查询

    public boolean delete(SysUsers sss) throws Exception {
        //获取

        String sql = "delete  from tencent where id=? and name=? " +
                "and type=? and number=? and link=? and spare=? ";

        Object[] ob={sss.getId(),sss.getName(), sss.getType(),
                sss.getNumber(), sss.getLink(), sss.getSpare()};
        return queryRunner.update(sql,ob)!=0;
    }//删除

    public boolean update(SysUsers sss, SysUsers sss2) throws Exception {
        //获取
        String sql = "update tencent set id=?,name=?,type=?,number=?,link=?,spare=?" +
                "where id=? and name=? and type=? and number=? and link=? and spare=? ";
        Object[] ob={sss.getId(),sss.getName(), sss.getType(),
                sss.getNumber(), sss.getLink(), sss.getSpare(),
                sss2.getId(),sss2.getName(), sss2.getType(),
                sss2.getNumber(), sss2.getLink(), sss2.getSpare(),};
        return queryRunner.update(sql,ob)!=0;
    }//修改
}