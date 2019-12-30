package kütüphaneotomasyonu.dal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;
import kütüphaneotomasyonu.data.DepositBooks;

public class MysqlMemberDal extends MysqlPersonDal implements IMember {

    @Override
    public ArrayList<DepositBooks> depositBookList(String userName) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<DepositBooks> depositBooks = null;

        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from emanetkitaplar where kıtapsahıbı='" + userName + "';");
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
    public void populateTableMyDepositBooks(JTable tblDepositBooks, String userName) {
        model = (DefaultTableModel) tblDepositBooks.getModel();
        model.setRowCount(0);
        try {
            ArrayList<DepositBooks> depositBooks = depositBookList(userName);
            for (DepositBooks depositBooks1 : depositBooks) {
                Object[] row = {depositBooks1.getBarkod(), depositBooks1.getBookName(), depositBooks1.getAuthorName(), depositBooks1.getPublishingHouse(), depositBooks1.getBookType(), depositBooks1.getStrDate(), depositBooks1.getStrDate2()};
                model.addRow(row);
            }
        } catch (SQLException exception) {
            exception.getMessage();
            exception.getErrorCode();
        }
    }

    @Override
    public void memberRegister(String name, String surname, String userName, String userPassword) throws SQLException {
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
    public boolean memberLogin(String userName, String userPassword) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from uyeler where BINARY kullanıcıadı='" + userName + "' AND BINARY sıfre='" + userPassword + "';");

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
    public void bookTake(Object index, String userName, String strDate, String strDate2) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = helper.getConnection();
            //String sql1="insert into emanetkitaplar (barkod,kıtapadı,yazaradı,yayınevı,kıtapturu) select barkod,kıtapadı,yazaradı,yayınevi,kıtapturu from kıtaplar where barkod='" + index + "';";
            //statement=connection.prepareStatement(sql1);
            //statement.executeUpdate();

            String sql1 = "insert into emanetkitaplar (barkod,kıtapadı,yazaradı,yayınevı,kıtapturu) select barkod,kıtapadı,yazaradı,yayınevi,kıtapturu from kıtaplar where barkod='" + index + "';";
            statement = connection.prepareStatement(sql1);
            statement.executeUpdate();

            String sql2 = "UPDATE emanetkitaplar SET kıtapsahıbı='" + userName + "' WHERE barkod='" + index + "'";
            statement = connection.prepareStatement(sql2);
            statement.executeUpdate();

            String sql3 = "UPDATE emanetkitaplar SET alıstarihi='" + strDate + "' WHERE barkod='" + index + "'";
            statement = connection.prepareStatement(sql3);
            statement.executeUpdate();

            String sql4 = "UPDATE emanetkitaplar SET verilistarihi='" + strDate2 + "' WHERE barkod='" + index + "'";
            statement = connection.prepareStatement(sql4);
            statement.executeUpdate();

            String sql = "delete from kıtaplar where barkod=?";
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
    public void bookGive(Object index, String userName) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = helper.getConnection();
            String sql1 = "insert into kıtaplar (barkod,kıtapadı,yazaradı,yayınevi,kıtapturu)"
                    + " select barkod,kıtapadı,yazaradı,yayınevı,kıtapturu from emanetkitaplar where barkod='" + index + "';";
            statement = connection.prepareStatement(sql1);
            statement.executeUpdate();

            String sql = "delete from emanetkitaplar where barkod=?";
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

}
