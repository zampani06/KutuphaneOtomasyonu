package kütüphaneotomasyonu.data;

public class Books {

    private int barkod;
    private String bookName;
    private String authorName;
    private String publishingHouse;
    private String bookType;

    public Books(int barkod, String bookName, String authorName, String publishingHouse, String bookType) {
        this.setBarkod(barkod);
        this.setBookName(bookName);
        this.setAuthorName(authorName);
        this.setPublishingHouse(publishingHouse);
        this.setBookType(bookType);

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

}
