//package com.movies.dao;
//
//import com.movies.config.HibernateUtil;
//import com.movies.model.Movie;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.List;
//
//public class MovieDAOImpl implements MovieDAO {
//
//    @Override
//    public void addMovie(Movie movie) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(movie);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Movie getMovie(int id) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.get(Movie.class, id);
//        }
//    }
//
//    @Override
//    public List<Movie> getAllMovies() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("from Movie", Movie.class).list();
//        }
//    }
//
//    @Override
//    public void updateMovie(Movie movie) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(movie);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteMovie(int id) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Movie movie = session.get(Movie.class, id);
//            if (movie != null) {
//                session.delete(movie);
//                transaction.commit();
//            }
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//}
