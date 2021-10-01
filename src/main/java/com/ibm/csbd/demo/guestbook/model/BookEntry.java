package com.ibm.csbd.demo.guestbook.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BookEntry", schema = "guestbook")
@NamedQuery(name = "BookEntry.findAll", query = "SELECT e FROM BookEntry e order by e.id desc")
public class BookEntry {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", schema = "guestbook")
    @Id
    @Column(name = "entryID")
    private int id;

    private String name;

    private String comment;

    @Column(name = "entryDate")
    private Date date;

    public BookEntry() {
        this.name = "Test";
        this.comment = "Noting interesting";
        this.date = new Date();
    }

    public BookEntry(String name, String comment) {
        this.name = name;
        this.comment = comment;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());        
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BookEntry other = (BookEntry) obj;

        if (id != other.id) {
            return false;
        }
        if (comment == null) {
            if (other.comment != null) {
                return false;
            }
        } 
        if (!name.equals(other.name)) {
            return false;
        }
        if (!date.equals(other.date)) {
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "BookEntry [id=" + id + ", name=" + name + ", comment=" + comment + ", date=" + date
                + "]";
    }
    
}
