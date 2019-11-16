package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        Statement s = null;
        Connection c = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8",
                    "root","admin"
            );
            s = c.createStatement();
            for(int i =0;i<100;i++){

                String sql = "insert into hero values(null,"+"'hero"+i+"',"+313.0f+","+50+")";
                s.execute(sql);
                System.out.println(sql);
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            if(s!=null){
                try {
                    s.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(c!=null){
                try{
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
