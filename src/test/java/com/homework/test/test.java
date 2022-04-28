package com.homework.test;

import com.homework.pojo.MySqlCon;
import com.homework.pojo.User;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author czm
 * @version 1.0
 */

public class test {

    @Test
    public void testCon() throws Exception {
        MySqlCon mySqlCon = new MySqlCon();
        Connection con = mySqlCon.getCon();
        System.out.println(con);
    }

    @Test
    public void testShowSport() throws Exception {
        new User().showSports();
    }

    @Test
    public void testShowScoreBySport() throws Exception {
        new User().showScoreByChooseSport();
    }
    @Test
    public void testSignSport() throws Exception {
        new User().signSport();
    }
    @Test
    public void showSportPeople() throws Exception {
        new User().showSportPeoPle();
    }

}
