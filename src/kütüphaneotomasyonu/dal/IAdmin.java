package kütüphaneotomasyonu.dal;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import kütüphaneotomasyonu.data.Admins;
import kütüphaneotomasyonu.data.Books;
import kütüphaneotomasyonu.data.DepositBooks;
import kütüphaneotomasyonu.data.Members;

public interface IAdmin {

    ArrayList<Books> bookList() throws SQLException;

    ArrayList<Admins> adminList() throws SQLException;

    ArrayList<Members> memberList() throws SQLException;

    ArrayList<DepositBooks> depositBookList() throws SQLException;

    void adminRegister(String name, String surname, String userName, String userPassword) throws SQLException;

    void announcementAdd(String announcement) throws SQLException;

    void bookAdd(String bookName, String authorName, String publishingHouse, String bookType) throws SQLException;

    void memberAdd(String name, String surname, String userName, String userPassword) throws SQLException;

    void populateTableAdmins(JTable tblBooks);

    void populateTableMembers(JTable tblMembers);

    void populateTableDepositBooks(JTable tblDepositBooks);

    boolean adminLogin(String userName, String userPassword) throws SQLException;

    void memberDelete(Object index) throws SQLException;

    void memberUpdate(Object index, String name, String surname, String userName, String password) throws SQLException;

}
