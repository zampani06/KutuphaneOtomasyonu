package kütüphaneotomasyonu.data;

public class Announcements {

    private int announcementsNumber;
    private String announcements;

    public Announcements(int announcementsNumber, String announcements) {
        this.setAnnouncements(announcements);
        this.setAnnouncementsNumber(announcementsNumber);
    }

    public int getAnnouncementsNumber() {
        return announcementsNumber;
    }

    public void setAnnouncementsNumber(int announcementsNumber) {
        this.announcementsNumber = announcementsNumber;
    }

    public String getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(String announcements) {
        this.announcements = announcements;
    }

}
