package kütüphaneotomasyonu.data;



public class DepositBooks {

    private int barkod;
    private String bookName;
    private String authorName;
    private String publishingHouse;
    private String bookType;
    private String bookOwner;
    private String strDate;
    private String strDate2;

    public DepositBooks(int barkod, String bookName, String authorName, String publishingHouse, String bookType, String bookOwner,String strDate,String strDate2) {
        this.setBarkod(barkod);
        this.setBookName(bookName);
        this.setAuthorName(authorName);
        this.setPublishingHouse(publishingHouse);
        this.setBookType(bookType);
        this.setBookOwner(bookOwner);
        this.strDate=strDate;
        this.strDate2=strDate2;
        
    }

    public int getBarkod() {
        return barkod;
    }

    public void setBarkod(int barkod) {
        this.barkod = barkod;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookOwner() {
        return bookOwner;
    }

    public void setBookOwner(String bookOwner) {
        this.bookOwner = bookOwner;
    }

    /**
     * @return the strDate
     */
    public String getStrDate() {
        return strDate;
    }

    /**
     * @param strDate the strDate to set
     */
    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    /**
     * @return the strDate2
     */
    public String getStrDate2() {
        return strDate2;
    }

    /**
     * @param strDate2 the strDate2 to set
     */
    public void setStrDate2(String strDate2) {
        this.strDate2 = strDate2;
    }

}
