package dev.scarnati.repositories;
import dev.scarnati.models.Employee;
import dev.scarnati.utils.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepo {
    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    private static final Logger logger = LogManager.getLogger(EmployeeRepo.class);


    public Employee getByUsername(String username)  {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"P1\".Employee where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                logger.info("Getting Login info By Username");
                return new Employee(
                        rs.getString("username"),
                        rs.getString("pass")

                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }
    public Employee getById(Integer id)  {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"P1\".Employee where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                logger.info("Getting Employee by Id");
                return new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getFloat(8),
                        rs.getFloat(9)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }
    public Employee getEmployeeByUsername(String username)  {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"P1\".Employee where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                logger.info("Getting Full Employee Object");
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("title"),
                        rs.getFloat(7),
                        rs.getFloat(8),
                        rs.getFloat(9)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }
    public Employee checkByUsername(String username) {



        try (Connection conn = cu.getConnection()) {
            String sql = "select id, title from \"P1\".Employee where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                logger.info("Checking Employee title");
                return new Employee(
                        rs.getInt(1),
                        rs.getString(2)
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());

        }
        return null;
    }
    public Boolean updatePending(Employee employee){
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Employee set pendingbalance = ? " +
                    " where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, employee.getPendingAmount());
            ps.setInt(2, employee.getId());
            logger.info("Changing Pending Balance of Employee");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean updateApproved(Employee employee){
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Employee set pendingbalance = ?, usedBalance = ? " +
                    " where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, employee.getPendingAmount());
            ps.setFloat(2, employee.getApprovedAmount());
            ps.setInt(3, employee.getId());
            logger.info("Moving Course Cost from Pending to Used Balance");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
}
