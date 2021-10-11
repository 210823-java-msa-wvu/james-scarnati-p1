package dev.scarnati.repositories;

import dev.scarnati.models.Approval;

import dev.scarnati.utils.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApprovalRepo {
    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    private static final Logger logger = LogManager.getLogger(ApprovalRepo.class);

    public Boolean createNewApproval() {
        try (Connection conn = cu.getConnection()) {

            String sql = "insert into \"P1\".Approval values(default,?,?,?,?,?,?,?,?,?,?,? )";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setString(2, null);
            ps.setBoolean(3, false);
            ps.setBoolean(4, false);
            ps.setBoolean(5, false);
            ps.setBoolean(6, false);
            ps.setBoolean(7, false);
            ps.setString(8, null);
            ps.setString(9, null);
            ps.setString(10, null);
            ps.setBoolean(11,false);

            logger.info("Creating a new Approval");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public ArrayList<Approval> getAllApproval()  {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"P1\".Approval order by id asc";

            PreparedStatement ps = conn.prepareStatement(sql);
            ArrayList<Approval> a = new ArrayList();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Approval approval = new Approval(
                        rs.getInt(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getBoolean(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getBoolean(12)
                );
                a.add(approval);
            }
            logger.info("Selecting all Approvals");
            return a;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }
    public Approval getApprovalById(Integer id)  {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"P1\".Approval where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Approval approval = new Approval(
                        rs.getInt(1),
                        rs.getBoolean(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getBoolean(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getBoolean(12)

                );
                logger.info("Getting all Approvals for an Employee");
                return approval;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean updateApprovalBc(Approval approval) {
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Approval set " +
                    "bcapproval = ?, inforequeste = ?," +
                    " inforequestds = ?, inforequestdh = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, approval.isBcApproval());
            ps.setBoolean(2, approval.isInfoRequestE());
            ps.setBoolean(3, approval.isInfoRequestDs());
            ps.setBoolean(4, approval.isInfoRequestDh());
            ps.setInt(5, approval.getId());

            logger.info("BC Approval Updated");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean updateApprovalBcForDh(Approval approval) {
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Approval set " +
                    "bcapproval = ?, inforequeste = ?," +
                    " inforequestds = ?, inforequestdh = ?, dhapproval = ?, dsapproval = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, approval.isBcApproval());
            ps.setBoolean(2, approval.isInfoRequestE());
            ps.setBoolean(3, approval.isInfoRequestDs());
            ps.setBoolean(4, approval.isInfoRequestDh());
            ps.setBoolean(5, approval.isDhApproval());
            ps.setBoolean(6, approval.isDhApproval());
            ps.setInt(7, approval.getId());

            logger.info("BC Approval Updated");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean updateApprovalDh(Approval approval) {
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Approval set  " +
                    "dhapproval = ?, inforequeste = ?," +
                    " inforequestds = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, approval.isDhApproval());
            ps.setBoolean(2, approval.isInfoRequestE());
            ps.setBoolean(3, approval.isInfoRequestDs());
            ps.setInt(4, approval.getId());

            logger.info("DH Approval Updated");
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return false;
    }

        public Boolean updateApprovalDhAndDs(Approval approval) {
            try (Connection conn = cu.getConnection()) {

                String sql = "Update \"P1\".Approval set  " +
                        "dhapproval = ?, inforequeste = ?," +
                        " inforequestds = ?, dsapproval = ? where id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setBoolean(1, approval.isDhApproval());
                ps.setBoolean(2, approval.isInfoRequestE());
                ps.setBoolean(3, approval.isInfoRequestDs());
                ps.setBoolean(4,approval.isDsApproval());
                ps.setInt(5, approval.getId());

                logger.info("DH Approval Updated");
                return ps.executeUpdate() != 0;
            }
            catch (SQLException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }

        return false;
    }
    public Boolean updateApprovalDs(Approval approval) {
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Approval set  " +
                    "dsapproval = ?, inforequeste = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, approval.isDsApproval());
            ps.setBoolean(2, approval.isInfoRequestE());
            ps.setInt(3, approval.getId());

            logger.info("DS Approval Updating");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean deny(Approval approval) {
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Approval set denied = ?, denialreason = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, approval.isDenied());
            ps.setString(2, approval.getDsDenialReason());
            ps.setInt(3, approval.getId());

            logger.info("Denying a Request");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean UpdateEInfo(Approval approval) {
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Approval set employeeInfo = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, approval.getEmployeeInfo());
            ps.setInt(2, approval.getId());

            logger.info("Adding Additional info from Employee");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean UpdateDSInfo(Approval approval) {
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Approval set dsInfo = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, approval.getDsInfo());
            ps.setInt(2, approval.getId());

            logger.info("Addind Additiona info from DS");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
    public Boolean UpdateDHInfo(Approval approval) {
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"P1\".Approval set dhInfo = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, approval.getDhInfo());
            ps.setInt(2, approval.getId());

            logger.info("Adding Additional info from DH");
            return ps.executeUpdate() != 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }
}
