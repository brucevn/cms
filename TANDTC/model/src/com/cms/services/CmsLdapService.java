package com.cms.services;


import com.cms.utils.MyBatisSqlSessionFactory;

import java.io.IOException;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.JspWriter;

import oracle.jdbc.OracleTypes;

import org.apache.ibatis.session.SqlSession;


public class CmsLdapService {
    public CmsLdapService() {
        super();
    }

    public String[] getPortalUsers(int start, int end, String user) {

        String[] results = new String[100];
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        Connection conn=null;
        CallableStatement cstm=null;
        try {
            conn = sqlSession.getConnection();
            cstm =
                conn.prepareCall("{?=call portal_ldap_utils.get_users(?,?,?)}");
            cstm.registerOutParameter(1, OracleTypes.FLOAT);
            cstm.setInt(2, start);
            cstm.setInt(3, end);
            cstm.setString(4, user);
            cstm.execute();
            int id = cstm.getInt(1);
            cstm =
conn.prepareCall("select tempval from system_portal_temp where id=?");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                Array array = rs.getArray(1);
                results = (String[])array.getArray();
            }
            cstm =
conn.prepareCall("delete from system_portal_temp where id=?");
            cstm.setInt(1, id);
            cstm.execute();
            conn.close();
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cstm!=null){
                cstm=null;
            }
            if(conn!=null){
                conn=null;
            }
            sqlSession.close();
        }
        return results;
    }

    public int countPortalUsers(String user) {
        int count = 0;
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        Connection conn=null;
        CallableStatement cstm=null;
        try {
            conn = sqlSession.getConnection();
            cstm =
                conn.prepareCall("{?=call portal_ldap_utils.count_users(?)}");
            cstm.registerOutParameter(1, OracleTypes.FLOAT);
            cstm.setString(2, user);
            cstm.execute();
            count = cstm.getInt(1);
            conn.close();
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cstm!=null){
                cstm=null;
            }
            if(conn!=null){
                conn=null;
            }
            sqlSession.close();
        }
        return count;
    }

    // Portal Group Function

    public String[] getPortalGroups(int start, int end, String group) {
        String[] results = new String[100];
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        Connection conn=null;
        CallableStatement cstm=null;
        try {
            conn = sqlSession.getConnection();
            cstm =
                conn.prepareCall("{?=call portal_ldap_utils.get_groups(?,?,?)}");
            cstm.registerOutParameter(1, OracleTypes.FLOAT);
            cstm.setInt(2, start);
            cstm.setInt(3, end);
            cstm.setString(4, group);
            cstm.execute();
            int id = cstm.getInt(1);
            cstm =
conn.prepareCall("select tempval from system_portal_temp where id=?");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                Array array = rs.getArray(1);
                results = (String[])array.getArray();
            }
            cstm =
conn.prepareCall("delete from system_portal_temp where id=?");
            cstm.setInt(1, id);
            cstm.execute();
            conn.close();
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cstm!=null){
                cstm=null;
            }
            if(conn!=null){
                conn=null;
            }
            sqlSession.close();
        }
        return results;
    }

    public int countPortalGroups(String name) {
        int count = 0;
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        Connection conn=null;
        CallableStatement cstm=null;
        try {
            conn = sqlSession.getConnection();
            cstm =
                conn.prepareCall("{?=call portal_ldap_utils.count_groups(?)}");
            cstm.registerOutParameter(1, OracleTypes.FLOAT);
            cstm.setString(2, name);
            cstm.execute();
            count = cstm.getInt(1);
            conn.close();
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cstm!=null){
                cstm=null;
            }
            if(conn!=null){
                conn=null;
            }
            sqlSession.close();
        }
        return count;
    }

    public String getPortalUser(int start, int end, String user,
                                JspWriter out) throws IOException {

        String[] results = new String[100];
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        Connection conn=null;
        CallableStatement cstm=null;
        try {
            conn = sqlSession.getConnection();
            cstm =
                conn.prepareCall("{?=call portal_ldap_utils.get_users(?,?,?)}");
            cstm.registerOutParameter(1, OracleTypes.FLOAT);
            cstm.setInt(2, start);
            cstm.setInt(3, end);
            cstm.setString(4, user);
            cstm.execute();
            int id = cstm.getInt(1);
            out.println("ID:" + id);
            cstm =
conn.prepareCall("select tempval from system_portal_temp where id=?");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                Array array = rs.getArray(1);
                results = (String[])array.getArray();
                out.println("result:" + results[0]);
            }
            cstm =
conn.prepareCall("delete from system_portal_temp where id=?");
            cstm.setInt(1, id);
            cstm.execute();
            conn.close();
        } catch (SQLException sqe) {
            out.println(sqe.getMessage());
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            if(cstm!=null){
                cstm=null;
            }
            if(conn!=null){
                conn=null;
            }
            sqlSession.close();
        }
        return "";
    }
    
    public static String getUserGroupName(int id,int isUser) {
        SqlSession sqlSession =
            MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();
        Connection conn=null;
        CallableStatement cstm=null;
        String name="";
        try {
            conn = sqlSession.getConnection();
            cstm = conn.prepareCall("{?=call portal_ldap_utils.GET_USER_GROUP_NAME(?,?)}");
            cstm.registerOutParameter(1, OracleTypes.VARCHAR);
            cstm.setInt(2, id);
            cstm.setInt(3,isUser);
            cstm.execute();
            name = cstm.getString(1);
            conn=null;
            cstm=null;
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cstm!=null){
                cstm=null;
            }
            if(conn!=null){
                conn=null;
            }
            sqlSession.close();
        }
        return name;
    }
}
