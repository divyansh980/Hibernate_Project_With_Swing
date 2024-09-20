package com.movies.dao.impl;

import com.movies.config.HibernateUtil;
import com.movies.dao.MovieDAO;
import com.movies.model.Movie;
//import com.movies.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovieDAOImpl implements MovieDAO {

    @Override
    public void addMovie(Movie movie) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Movie getMovieById(int id) {
        Transaction transaction = null;
        Movie movie = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            movie = session.get(Movie.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public void updateMovie(Movie movie) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMovie(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Movie movie = session.get(Movie.class, id);
            if (movie != null) {
                session.delete(movie);
                System.out.println("Movie deleted successfully.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
