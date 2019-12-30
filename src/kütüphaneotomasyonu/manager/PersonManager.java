package kütüphaneotomasyonu.manager;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class PersonManager extends IPersonManager {

    @Override
    public void booksListSearchKey(String txtSearch, JTable tblBooksList, int itemİndex) {
        DefaultTableModel model;
        model = (DefaultTableModel) tblBooksList.getModel();
        String searchKey = txtSearch;
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(model);
        tblBooksList.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey, itemİndex));

    }
}
 