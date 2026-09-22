package com.practice.library;

import java.util.Objects;

public class KitabKhana {
    private int id;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public KitabKhana(int id,String title,String author,int totalCopies,int availableCopies) {
        this.id = id;
        this.title=title;
        this.author=author;
        this.totalCopies=totalCopies;
        this.availableCopies=availableCopies;
    }

    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public int getAvailableCopies() {return availableCopies;}
    public int getTotalCopies() {return totalCopies;}
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "KitabKhana{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        KitabKhana that = (KitabKhana) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
