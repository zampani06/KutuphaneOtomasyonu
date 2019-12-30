package kütüphaneotomasyonu.manager;

import javax.swing.JTable;

public interface IMemberManager {

    public void memberLoginValidator(String userName, String userPassword, String txtUserName);

    public void memberPopulateDepositBookList(JTable tblMyDepositBooksList);

    public void memberRegisterControl(String userPasswordV, String userPasswordRepeatV, String name, String surname, String userName, String userPassword);

    public void bookTake(JTable tblBooksList);

    public void bookGive(JTable tblBooksList);

}
