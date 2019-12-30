package kütüphaneotomasyonu.manager;

import java.sql.SQLException;
import javax.swing.JTable;

public interface IAdminManager {

    void adminLoginValidator(String userName, String userPassword);

    void adminRegisterControl(String userPasswordV, String userPasswordRepeatV, String name, String surname, String userName, String userPassword);

    void announcementsControl(String areaAnnouncements);

    void booksAddControl(String bookName, String authorName, String publishingHouse, String bookType);

    void membersAddControl(String userPasswordV, String userPasswordRepeatV, String name, String surname, String userName, String userPassword);

    void memberDelete(JTable tblBooksList) throws SQLException;

    void memberUpdate(JTable tblBooksList, String name, String surname, String userName, String password) throws SQLException;
}
