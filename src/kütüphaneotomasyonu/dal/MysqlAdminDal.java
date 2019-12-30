package kütüphaneotomasyonu.dal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import kütüphaneotomasyonu.data.Admins;
import kütüphaneotomasyonu.data.DepositBooks;
import kütüphaneotomasyonu.data.Members;

public class MysqlAdminDal extends MysqlPersonDal implements IAdmin {

    DbHelper helper = new DbHelper();

    @Override
    public ArrayList<Admins> adminList() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Admins> admins = null;
        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from adminler");
            admins = new ArrayList<Admins>();
            while (resultSet.next()) {
                admins.add(new Admins(resultSet.getInt("adminno"),
                                  resultSet.getString("ad"),
                                  resultSet.getString("soyad"),
                                  resultSet.getString("kullanıcıadı"),
                                  resultSet.getString("sıfre")
                ));

            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
        return admins;
    }

    @Override
    public ArrayList<Members> memberList() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Members> members = null;
        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from uyeler");
            members = new ArrayList<Members>();
            while (resultSet.next()) {
                members.add(new Members(resultSet.getInt("uyeno"),
                                  resultSet.getString("ad"),
                                  resultSet.getString("soyad"),
                                  resultSet.getString("kullanıcıadı"),
                                  resultSet.getString("sıfre")
                ));

            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
        return members;
    }

    @Override
    public void bookAdd(String bookName, String authorName, String publishingHouse, String bookType) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = helper.getConnection();
            String sql = "insert into kıtaplar (kıtapadı,yazaradı,yayınevi,kıtapturu) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, bookName);
            statement.setString(2, authorName);
            statement.setString(3, publishingHouse);
            statement.setString(4, bookType);
            statement.executeUpdate();

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();

        }

    }

    @Override
    public void memberAdd(String name, String surname, String userName, String userPassword) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = helper.getConnection();
            String sql = "insert into uyeler (ad,soyad,kullanıcıadı,sıfre) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, userName);
            statement.setString(4, userPassword);

            statement.executeUpdate();

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();

        }

    }

    @Override
    public ArrayList<DepositBooks> depositBookList() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<DepositBooks> depositBooks = null;

        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from emanetkitaplar");
            depositBooks = new ArrayList<DepositBooks>();
            while (resultSet.next()) {
                depositBooks.add(new DepositBooks(resultSet.getInt("barkod"),
                                  resultSet.getString("kıtapadı"),
                                  resultSet.getString("yazaradı"),
                                  resultSet.getString("yayınevi"),
                                  resultSet.getString("kitapturu"),
                                  resultSet.getString("kıtapsahıbı"),
                                  resultSet.getString("alıstarihi"),
                                  resultSet.getString("verilistarihi")
                ));

            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
        return depositBooks;

    }

    @Override
    public void announcementAdd(String announcement) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = helper.getConnection();
            String sql = "insert into duyurular (duyuru) values (?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, announcement);
            statement.executeUpdate();

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();

        }

    }

    @Override
    public void populateTableAdmins(JTable tblAdmins) {

        model = (DefaultTableModel) tblAdmins.getModel();
        try {
            ArrayList<Admins> admins = adminList();
            for (Admins admins1 : admins) {
                Object[] row = {admins1.getAdminNumber(), admins1.getName(), admins1.getSurname(), admins1.getUserName(), admins1.getUserPassword()};
                model.addRow(row);
            }
        } catch (SQLException exception) {

        }

    }

    @Override
    public void populateTableMembers(JTable tblMembers) {

        model = (DefaultTableModel) tblMembers.getModel();
        model.setRowCount(0);
        try {
            ArrayList<Members> members = memberList();
            for (Members members1 : members) {
                Object[] row = {members1.getMemberNumber(), members1.getName(), members1.getSurname(), members1.getUserName(), members1.getUserPassword()};
                model.addRow(row);
            }
        } catch (SQLException exception) {

        }

    }

    @Override
    public void populateTableDepositBooks(JTable tblDepositBooks) {
        model = (DefaultTableModel) tblDepositBooks.getModel();
        try {
            ArrayList<DepositBooks> depositBooks = depositBookList();
            for (DepositBooks depositBooks1 : depositBooks) {
                Object[] row = {depositBooks1.getBarkod(), depositBooks1.getBookName(), depositBooks1.getAuthorName(), depositBooks1.getPublishingHouse(), depositBooks1.getBookType(), depositBooks1.getBookOwner(), depositBooks1.getStrDate(), depositBooks1.getStrDate2()};
                model.addRow(row);
            }
        } catch (SQLException exception) {

        }
    }

    @Override
    public void adminRegister(String name, String surname, String userName, String userPassword) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = helper.getConnection();
            String sql = "insert into adminler (ad,soyad,kullanıcıadı,sıfre) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, userName);
            statement.setString(4, userPassword);

            statement.executeUpdate();

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();

        }

    }

    @Override
    public boolean adminLogin(String userName, String userPassword) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from adminler where BINARY kullanıcıadı='" + userName + "' "
                              + "AND BINARY sıfre='" + userPassword + "';");

            while (resultSet.next()) {
                return true;

            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
        return false;
    }

    @Override
    public void memberDelete(Object index) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = helper.getConnection();

            String sql = "delete from uyeler where uyeno=?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, index);
            statement.executeUpdate();

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();

        }

    }

    @Override
    public void memberUpdate(Object index, String name, String surname, String userName, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = helper.getConnection();

            String sql = "UPDATE uyeler SET ad=?,soyad=?,kullanıcıadı=?,sıfre=? WHERE uyeno= '" + index + "' ";
            statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, userName);
            statement.setString(4, password);

            statement.executeUpdate();

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();

        }

    }

}
