package kütüphaneotomasyonu.dal;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import kütüphaneotomasyonu.data.Books;
import kütüphaneotomasyonu.data.DepositBooks;

public interface IMember {

    ArrayList<Books> bookList() throws SQLException;

    ArrayList<DepositBooks> depositBookList(String userName) throws SQLException;

    void populateTableMyDepositBooks(JTable tblDepositBooks, String userName);

    void memberRegister(String name, String surname, String userName, String userPassword) throws SQLException;

    boolean memberLogin(String userName, String userPassword) throws SQLException;

    void bookTake(Object index, String userName, String strDate, String strDate2) throws SQLException;

    void bookGive(Object index, String userName) throws SQLException;

}
