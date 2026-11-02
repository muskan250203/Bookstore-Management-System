package com.bookstore.dao;

import com.bookstore.model.Book;
import com.bookstore.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/** Hibernate-based DAO: full CRUD + search for books. */
public class BookDAO {

    public void save(Book book) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.persist(book);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void update(Book book) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.merge(book);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void delete(int id) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Book b = s.find(Book.class, id);
            if (b != null) s.remove(b);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Book getById(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.find(Book.class, id);
        }
    }

    public List<Book> getAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("FROM Book ORDER BY id DESC", Book.class).list();
        }
    }

    public List<Book> search(String keyword) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> q = s.createQuery(
                "FROM Book WHERE lower(title) LIKE :k OR lower(author) LIKE :k OR lower(category) LIKE :k ORDER BY id DESC",
                Book.class);
            q.setParameter("k", "%" + keyword.toLowerCase() + "%");
            return q.list();
        }
    }
}
