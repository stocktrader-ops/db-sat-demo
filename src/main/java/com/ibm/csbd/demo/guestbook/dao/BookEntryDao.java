package com.ibm.csbd.demo.guestbook.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.ibm.csbd.demo.guestbook.model.BookEntry;

@ApplicationScoped
public class BookEntryDao {
    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Transactional
    public void createBookEntry(BookEntry entry) {
        em.persist(entry);
    }

    @Transactional
    public BookEntry getBookEntry(int entryId) {
        return em.find(BookEntry.class, entryId);
    }

    @Transactional
    public List<BookEntry> getAllEntries() {
        return em.createNamedQuery("BookEntry.findAll", BookEntry.class).getResultList();
    }    
}
