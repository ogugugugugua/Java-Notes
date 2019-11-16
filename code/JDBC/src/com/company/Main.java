package com.company;

import java.sql.*;
import java.util.Scanner;
class Hero{
    int id;
    String name;
    float hp;
    int damage;
    public Hero(String name,float hp,int damage){
        this.hp = hp;
        this.damage = damage;
        this.name = name;
    }
}
public class Main {

    public static void execute(String sql){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8",
                "root","admin");
            Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void list(int start, int count){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8",
                "root","admin");
            Statement s = c.createStatement()) {
            String sql = "select * from hero limit " + start + ','+ count;
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString(2);
                float hp = resultSet.getFloat("hp");
                int damage = resultSet.getInt(4);
                System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeQuery(String sql){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8",
                "root","admin");
            Statement s = c.createStatement()) {
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString(2);
                float hp = resultSet.getFloat("hp");
                int damage = resultSet.getInt(4);
                System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(Hero h){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "insert into hero values(null,?,?,?)";

        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8", "root", "admin");
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, h.name);
            ps.setFloat(2, h.hp);
            ps.setInt(3, h.damage);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insert(String name, float hp, int damage){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "insert into hero values(null,?,?,?)";

        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8", "root", "admin");
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setFloat(2, hp);
            ps.setInt(3, damage);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertWithManualCommit(String name, float hp, int damage){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);

        String sql = "insert into hero values(null,?,?,?)";

        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=UTF-8", "root", "admin");
             PreparedStatement ps = c.prepareStatement(sql)) {
            c.setAutoCommit(false);

            ps.setString(1, name);
            ps.setFloat(2, hp);
            ps.setInt(3, damage);
            ps.execute();
            System.out.println("not commit yet");

            String command = scanner.next();
            if(command.equals("y")){
                c.commit();
                System.out.println("commited");
            }else {
                System.out.println("commit failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        String sql = "update hero set name = 'change2' where id = 12";
//        execute(sql);

//        String sql = "select * from hero";
//        executeQuery(sql);
//        list(10,5);
//        insertWithManualCommit("commitTest2",250,100);

        insert(new Hero("TryHero",200,30));

    }
}
