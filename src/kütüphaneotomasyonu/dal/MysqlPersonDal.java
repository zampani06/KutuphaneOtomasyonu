package kütüphaneotomasyonu.dal;

import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import kütüphaneotomasyonu.data.Announcements;
import kütüphaneotomasyonu.data.Books;

public class MysqlPersonDal implements IPerson {

    DefaultTableModel model;
    DbHelper helper = new DbHelper();

    @Override
    public ArrayList<Books> bookList() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Books> books = null;

        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from kıtaplar");
            books = new ArrayList<Books>();
            while (resultSet.next()) {
                books.add(new Books(resultSet.getInt("barkod"),
                                  resultSet.getString("kıtapadı"),
                                  resultSet.getString("yazaradı"),
                                  resultSet.getString("yayınevi"),
                                  resultSet.getString("kitapturu")
                ));

            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
        return books;

    }

    @Override
    public void populateTableBooks(JTable tblBooks) {

        model = (DefaultTableModel) tblBooks.getModel();
        model.setRowCount(0);
        try {
            ArrayList<Books> books = bookList();
            for (Books books1 : books) {
                Object[] row = {books1.getBarkod(), books1.getBookName(), books1.getAuthorName(), books1.getPublishingHouse(), books1.getBookType()};
                model.addRow(row);
            }
        } catch (SQLException exception) {

        }

    }

    @Override
    public void populateAnnouncement(JTable tblAnnouncement) {
        model = (DefaultTableModel) tblAnnouncement.getModel();
        try {
            ArrayList<Announcements> announcements = announcementsList();
            for (Announcements announcements1 : announcements) {
                Object[] row = {announcements1.getAnnouncementsNumber(), announcements1.getAnnouncements()};
                model.addRow(row);
            }
        } catch (SQLException exception) {

        }

    }

    @Override
    public ArrayList<Announcements> announcementsList() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Announcements> announcements = null;

        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from duyurular");
            announcements = new ArrayList<Announcements>();
            while (resultSet.next()) {
                announcements.add(new Announcements(resultSet.getInt("duyuruno"),
                                  resultSet.getString("duyuru")
                ));

            }
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close();
        }
        return announcements;
    }
}
