package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book", nullable=false, unique=true, updatable=false)
    private long id;
    
    @Column(name = "name_book", unique=true, length=150, nullable=false)
    private String namebook;
    
    @Column(name = "description_book", unique=false, length=300, nullable=true)
    private String descriptionbook;
    
    @Column(name = "author_book", unique=false, length=150, nullable=false)
    private String authorbook;
    
    @Column(name = "date_publish_book", unique=false, nullable=true)
    private Date datepublishbook;
    
    @Column(name = "number_book", unique=false, nullable=true)
    private int numberbook;
    
    @Column(name = "price_book", unique=false, nullable=true)
    private double pricebook;    

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", namebook=" + namebook + ", descriptionbook=" + descriptionbook + ", authorbook=" + authorbook + ", datepublishbook=" + datepublishbook + ", numberbook=" + numberbook + ", pricebook=" + pricebook + '}';
    }

    
    public Book() {

    }

    public Book(String namebook, String descriptionbook, String authorbook, Date datepublishbook, int numberbook, double pricebook) {
        this.namebook = namebook;
        this.descriptionbook = descriptionbook;
        this.authorbook = authorbook;
        this.datepublishbook = datepublishbook;
        this.numberbook = numberbook;
        this.pricebook = pricebook;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamebook() {
        return namebook;
    }

    public void setNamebook(String namebook) {
        this.namebook = namebook;
    }

    public String getDescriptionbook() {
        return descriptionbook;
    }

    public void setDescriptionbook(String descriptionbook) {
        this.descriptionbook = descriptionbook;
    }

    public String getAuthorbook() {
        return authorbook;
    }

    public void setAuthorbook(String authorbook) {
        this.authorbook = authorbook;
    }

    public Date getDatepublishbook() {
        return datepublishbook;
    }

    public void setDatepublishbook(Date datepublishbook) {
        this.datepublishbook = datepublishbook;
    }

    public int getNumberbook() {
        return numberbook;
    }

    public void setNumberbook(int numberbook) {
        this.numberbook = numberbook;
    }

    public double getPricebook() {
        return pricebook;
    }

    public void setPricebook(double pricebook) {
        this.pricebook = pricebook;
    }

    
}
