package com.homework.pojo;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * @author czm
 * @version 1.0
 */

public class User {
    private String id = "123";
    private String name = "123";
    private char sex;
    private int age;
    private String phone;
    private String event;

    private Connection con = new MySqlCon().getCon();
    private Statement statement = con.createStatement();


    public User() throws Exception {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void showSports() throws SQLException {
        String sql = "select * from sport_event_message";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.printf("%-3s%-7s%-10s%-14s", "id", "运动项目", "地点", "开始时间");
        System.out.printf("%-13s%-8s%-8s", "结束时间", "最大报名人数", "运动规则");
        System.out.println();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String event = resultSet.getString(2);
            String eventRules = resultSet.getString(3);
            String place = resultSet.getString(4);
            Date startDate = resultSet.getDate(5);
            Date endRegisterDate = resultSet.getDate(6);
            int maxNum = resultSet.getInt(7);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            System.out.printf("%-3d%-8s%-8s%-16s", id, event, place, f.format(startDate));
            System.out.printf("%-16s%-11d%-8s", f.format(endRegisterDate), maxNum, eventRules);
            System.out.println();
        }

    }

    public void showScoreByChooseSport() throws SQLException {
        repeatM();
        System.out.println("请输入序号查看项目得分及排名(默认降序排名)");

        int choice = 1;

        ResultSet resultSet1 = statement.executeQuery("select table_ from sport_event_message where id=" + choice);
        resultSet1.next();
        ResultSet resultSet2 = statement.executeQuery("select * from " + resultSet1.getString(1) + " order by rank desc");

        while (resultSet2.next()) {
            String name = resultSet2.getString("name_");
            String id = resultSet2.getString("id");
            String x = resultSet2.getString(3);
            String rank = resultSet2.getString(4);
            System.out.print(name + "     \t" + id + "    \t" + x + "    \t" + rank);
            System.out.println();
        }

    }

    public void signSport() throws SQLException {
        showSports();
        System.out.println("请输入要报名的运动项目序号");
        int choice = 1;
        Statement statement = con.createStatement();
        ResultSet resultSet1 = statement.executeQuery("select * from sport_event_message where id=" + choice);
        resultSet1.next();
        //最大报名人数
        int maxNum = resultSet1.getInt(7);
        //获取具体运动表名字
        String tableName = resultSet1.getString("table_");
        ResultSet resultSet2 = statement.executeQuery("select count(*) from " + tableName);
        resultSet2.next();
        //已经报名的人数
        int count = resultSet2.getInt(1) - 1;
        count++;
        if (count >= maxNum) {
            System.out.println("报名已满，请重新选择");
        } else {
            PreparedStatement pS = con.prepareStatement("INSERT INTO " + tableName + "(name_,id) VALUES (?,?)");
            pS.setString(1, getName());
            pS.setString(2, getId());
            int i = pS.executeUpdate();
            if (i != 1) {
                System.out.println("报名失败");
            } else {
                System.out.println("报名成功");
            }

        }

    }

    public void showSportPeoPle() throws SQLException {
        repeatM();
        System.out.println("请输入序号查看项目参赛人员列表");
        int choice = 1;
        ResultSet resultSet1 = statement.executeQuery("select table_ from sport_event_message where id=" + choice);
        resultSet1.next();
        ResultSet resultSet2 = statement.executeQuery("select * from " + resultSet1.getString(1) + " order by rank desc");

        while (resultSet2.next()) {
            String name = resultSet2.getString("name_");
            String id = resultSet2.getString("id");
            System.out.print(name + "     \t" + id);
            System.out.println();
        }

    }

    private void repeatM() throws SQLException {
        String sql = "select * from sport_event_message";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("运动项目");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String event = resultSet.getString("event_");
            System.out.println(id + "." + event);
        }
    }

}
