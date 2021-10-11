package dev.scarnati.repositories;



import dev.scarnati.utils.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseRepo {
    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    private static final Logger logger = LogManager.getLogger(CourseRepo.class);


    public Float getById(Integer id)  {
        try (Connection conn = cu.getConnection()) {

            String sql = "select reimbursementPercent from \"P1\".Courses where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                logger.info("Getting Reimbursement Percentage");
                return rs.getFloat("reimbursementPercent");

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());

        }
        return null;
    }

}

