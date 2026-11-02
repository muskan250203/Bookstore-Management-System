package com.bookstore.dao;

import com.bookstore.util.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** JDBC-based DAO for dashboard statistics (aggregate SQL). */
public class ReportDAO {

    /** Returns {totalTitles, totalStock, inventoryValue}. */
    public double[] getStats() {
        String sql = "SELECT COUNT(*), COALESCE(SUM(quantity),0), COALESCE(SUM(price*quantity),0) FROM books";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return new double[]{rs.getInt(1), rs.getInt(2), rs.getDouble(3)};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new double[]{0, 0, 0};
    }
}
