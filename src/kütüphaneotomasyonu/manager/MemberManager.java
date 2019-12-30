package kütüphaneotomasyonu.manager;

import kütüphaneotomasyonu.form.MemberLogin;
import kütüphaneotomasyonu.form.MemberHomePage;
import kütüphaneotomasyonu.dal.IPerson;
import kütüphaneotomasyonu.dal.IMember;
import kütüphaneotomasyonu.dal.MysqlMemberDal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import kütüphaneotomasyonu.dal.IAdmin;
import kütüphaneotomasyonu.dal.MysqlAdminDal;

public class MemberManager extends PersonManager implements IMemberManager {

    @Override
    public void memberLoginValidator(String userName, String userPassword, String txtUserName) {
        IMember memberLoginDatabase = new MysqlMemberDal();
        MemberLogin staticUserName = new MemberLogin();
        try {

            if (memberLoginDatabase.memberLogin(userName, userPassword)) {

                staticUserName.userName = txtUserName;

                MemberHomePage memberhomeform = new MemberHomePage();

                memberhomeform.setVisible(true);

                MemberLogin memberLoginDispose = new MemberLogin();

                memberLoginDispose.dispose();
               
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Hesap Bilgileri Yanlış", "Hata", JOptionPane.ERROR_MESSAGE);

            }

        } catch (SQLException exception) {
            exception.getErrorCode();
            exception.getMessage();
        }
    }

    @Override
    public void memberPopulateDepositBookList(JTable tblMyDepositBooksList) {
        MemberLogin staticUserName = new MemberLogin();
        IMember memberBooksList = new MysqlMemberDal();
        try {

            memberBooksList.depositBookList(staticUserName.userName);
            memberBooksList.populateTableMyDepositBooks(tblMyDepositBooksList, staticUserName.userName);
        } catch (SQLException exception) {
            exception.getErrorCode();
            exception.getMessage();
        }
    }

    @Override
    public void memberRegisterControl(String userPasswordV, String userPasswordRepeatV, String name, String surname, String userName, String userPassword) {
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "AD BOŞ BIRAKILAMAZ", "BOŞ BIRAKILAMAZ", JOptionPane.INFORMATION_MESSAGE);
        } else if (surname.length() == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "SOYAD BOŞ BIRAKILAMAZ", "BOŞ BIRAKILAMAZ", JOptionPane.INFORMATION_MESSAGE);
        } else if (userName.length() == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "KULLANICI ADI BOŞ BIRAKILAMAZ", "BOŞ BIRAKILAMAZ", JOptionPane.INFORMATION_MESSAGE);
        } else if (userPassword.length() == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "ŞİFRE BOŞ BIRAKILAMAZ", "BOŞ BIRAKILAMAZ", JOptionPane.INFORMATION_MESSAGE);
        } else if (userPasswordRepeatV.length() == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "ŞİFRE TEKRAR BOŞ BIRAKILAMAZ", "BOŞ BIRAKILAMAZ", JOptionPane.INFORMATION_MESSAGE);
        } else {

            if (userPasswordV.equals(userPasswordRepeatV)) {
                IMember memberRegisterDatabase = new MysqlMemberDal();
                try {
                    memberRegisterDatabase.memberRegister(name, surname, userName, userPassword);
                } catch (SQLException exception) {
                    exception.getErrorCode();
                    exception.getMessage();
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Şifre ve Şifre Tekrar aynı olmak zorunda", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void bookTake(JTable tblBooksList) {
        IMember bookTake1 = new MysqlMemberDal();
        MemberLogin staticUserName = new MemberLogin();
        Date creationTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(creationTime);
        Calendar c = Calendar.getInstance();
        c.setTime(creationTime);
        c.add(Calendar.DATE, 15);
        creationTime = c.getTime();
        String strDate2 = formatter.format(creationTime);

        try {
            int index2 = tblBooksList.getSelectedRow();
            tblBooksList.getValueAt(index2, 0);

            bookTake1.bookTake(tblBooksList.getValueAt(index2, 0), staticUserName.userName, strDate, strDate2);
            IPerson bookTake = new MysqlMemberDal();
            bookTake.populateTableBooks(tblBooksList);

        } catch (SQLException exception) {
            exception.getErrorCode();
            exception.getMessage();
        }
    }

    @Override
    public void bookGive(JTable tblBooksList) {
        IMember bookGive1 = new MysqlMemberDal();
        MemberLogin userName = new MemberLogin();
        try {
            int index2 = tblBooksList.getSelectedRow();
            tblBooksList.getValueAt(index2, 0);

            bookGive1.bookGive(tblBooksList.getValueAt(index2, 0), userName.userName);

            IMember bookGive2 = new MysqlMemberDal();
            bookGive2.populateTableMyDepositBooks(tblBooksList, userName.userName);

        } catch (SQLException exception) {
            exception.getErrorCode();
            exception.getMessage();
        }
    }
}
