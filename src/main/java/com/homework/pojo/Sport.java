//package com.homework.pojo;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
///**
// * @author czm
// * @version 1.0
// */
//
//public class Sport {
//    private String event;
//    private String eventRules;
//    private String place;
//    private String startDate;
//    private String endRegisterDate;
//    private String maxNum;
//    private Connection con = new MySqlCon().getCon();
//
//    public Sport() throws Exception {
//    }
//    public void showSport(){
//        String sql = "select * from sport_event_message";
//        Statement statement = con.createStatement();
//        ResultSet resultSet = statement.executeQuery(sql);
//        while (resultSet.next()){
//            resultSet.getInt()
//        }
//    }
//}
