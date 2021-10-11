package dev.scarnati.repositories;

import dev.scarnati.models.Employee;
import dev.scarnati.models.Requests;
import dev.scarnati.utils.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RequestRepo {
    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    private static final Logger logger = LogManager.getLogger(RequestRepo.class);

    public ArrayList<Requests> getById(Integer id)  {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"P1\".Requests where employeeId = ? order by id asc";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ArrayList<Requests> requests = new ArrayList();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 Requests r = new Requests(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getFloat(7),
                        rs.getInt(8),
                        rs.getBoolean(9),
                        rs.getBoolean(10),
                         rs.getBoolean(11),
                         rs.getString(12),
                         rs.getBoolean(13),
                         rs.getString(14),
                         rs.getBoolean(15),
                         rs.getInt(16),
                         rs.getString(17),
                         rs.getString(18)

                 );
                requests.add(r);
                logger.info("Getting an Employees Requests");
            }
            return requests;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }
    public Requests getByReqId(Integer id)  {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"P1\".Requests where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Requests r = new Requests(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getFloat(7),
                        rs.getInt(8),
                        rs.getBoolean(9),
                        rs.getBoolean(10),
                        rs.getBoolean(11),
                        rs.getString(12),
                        rs.getBoolean(13),
                        rs.getString(14),
                        rs.getBoolean(15),
                        rs.getInt(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getBoolean(19)


                );
                logger.info("Getting a specific Request");
                return r;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }
    public ArrayList<Requests> getAllRequests()  {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"P1\".Requests order by id asc";

            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<Requests> requests = new ArrayList();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Requests r = new Requests(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getFloat(7),
                        rs.getInt(8),
                        rs.getBoolean(9),
                        rs.getBoolean(10),
                        rs.getBoolean(11),
                        rs.getString(12),
                        rs.getBoolean(13),
                        rs.getString(14),
                        rs.getBoolean(15),
                        rs.getInt(16),
                        rs.getString(17),
                        rs.getString(18)

                );
                requests.add(r);
                logger.info("Getting All Requests");
            }
            return requests;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    public Boolean addRequest(Requests requests)  {
        try (Connection conn = cu.getConnection()) {

            String sql = "insert into \"P1\".Requests values (default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, requests.getEmployeeId());
                    ps.setString(2, requests.getRequestDate());
                    ps.setString(3, requests.getCourseStart());
                    ps.setString(4, requests.getPassingGrade());
                    ps.setFloat(5, requests.getCourseCost());
                    ps.setFloat(6, requests.getReimbursementAmount());
                    ps.setInt(7, requests.getCourseType());
                    ps.setBoolean(8, requests.isApproval());
                    ps.setBoolean(9, requests.isPass());
                    ps.setBoolean(10, false);
                    ps.setString(11, null);
                    ps.setBoolean(12,false);
                    ps.setString(13,null);
                    ps.setBoolean(14, requests.isUrgent());
                    ps.setInt(15, requests.getHours());
                    ps.setString(16, requests.getLocation());
                    ps.setString(17, requests.getReason());

            logger.info("Creating a Request");
            return ps.executeUpdate() != 0;



        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean updateAmount(Requests requests){
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Requests set reimbursementAmount = ? " +
                    " where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, requests.getReimbursementAmount());
            ps.setInt(2, requests.getId());
            logger.info("Chaning Reimbursement Amount");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean approveReq(Requests requests){
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Requests set Approval = ? " +
                    " where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, requests.isApproval());
            ps.setInt(2, requests.getId());
            logger.info("Approving Request");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean denyReq(Requests requests){
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Requests set Deny = ?, complete = true " +
                    " where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, requests.isDenial());
            ps.setInt(2, requests.getId());
            logger.info("Denying a Request");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }

    public Boolean passOrFail(Requests requests){
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Requests set Pass = ? " +
                    " where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, requests.isPass());
            ps.setInt(2, requests.getId());
            logger.info("Updating whether Employee passed or failed");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean dmCheck(Requests requests){
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Requests set dmcheck = ?, complete = true " +
                    " where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, requests.isDsCheck());
            ps.setInt(2, requests.getId());
            logger.info("DM Checked Grade/Presentation");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean setGrade(Requests requests) {
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Requests set grade = ? " +
                    " where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, requests.getGrade());
            ps.setInt(2, requests.getId());
            logger.info("Adding Course outcome to request");
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return false;
    }
    public Boolean addFile(Requests requests) {
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Requests set file = ? " +
                    " where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, requests.getFile());
            ps.setInt(2, requests.getId());
            logger.info("Adding file attachment to request");
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return false;
    }
}
