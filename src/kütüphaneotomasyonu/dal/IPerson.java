package kütüphaneotomasyonu.dal;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import kütüphaneotomasyonu.data.Announcements;
import kütüphaneotomasyonu.data.Books;

public interface IPerson {

    ArrayList<Books> bookList() throws SQLException;

    public void populateTableBooks(JTable tblBooks);

    public void populateAnnouncement(JTable tblAnnouncement);

    public ArrayList<Announcements> announcementsList() throws SQLException;
}
