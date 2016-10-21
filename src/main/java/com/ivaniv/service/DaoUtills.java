package com.ivaniv.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by iivaniv on 21.10.2016.
 */
public class DaoUtills {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    public static void closeResources(Connection con,  Statement stmt, ResultSet rs) {
        try {
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        } catch (SQLException e) {
            log.error("Error while closing resources ",e);
        }
    }

}
