package com.example.symu_api.COMMON.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class DBUtils {

    public static void CloseConnections(ResultSet rs, CallableStatement cst, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception rse) {
            rse.printStackTrace();
        }
        try {
            if (cst != null) {
                cst.close();
            }
        } catch (Exception sse) {
            sse.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception cse) {
            cse.printStackTrace();
        }
    }
}
