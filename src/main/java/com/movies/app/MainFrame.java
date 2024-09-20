package com.movies.app;

import com.movies.dao.MovieDAO;
import com.movies.dao.impl.MovieDAOImpl;
import com.movies.model.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JTextField txtTitle, txtGenre, txtDirector, txtYear, txtId;
    private JButton btnAdd, btnFetch, btnUpdate, btnDelete;
    private JTextArea outputArea;

    // MovieDAO for handling CRUD operations
    private MovieDAO movieDAO;

    public MainFrame() {
        movieDAO = new MovieDAOImpl();

        // Initialize JFrame
        setTitle("Movie Management System");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // Using absolute layout

        // Title Label
        JLabel lblHeading = new JLabel("Movie Management System");
        lblHeading.setFont(new Font("Arial", Font.BOLD, 24));
        lblHeading.setBounds(140, 20, 400, 30);
        add(lblHeading);

        // Labels and TextFields
        JLabel lblId = new JLabel("Movie ID:");
        lblId.setBounds(50, 80, 100, 30);
        add(lblId);
        txtId = new JTextField();
        txtId.setBounds(150, 80, 200, 30);
        add(txtId);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(50, 120, 100, 30);
        add(lblTitle);
        txtTitle = new JTextField();
        txtTitle.setBounds(150, 120, 200, 30);
        add(txtTitle);

        JLabel lblGenre = new JLabel("Genre:");
        lblGenre.setBounds(50, 160, 100, 30);
        add(lblGenre);
        txtGenre = new JTextField();
        txtGenre.setBounds(150, 160, 200, 30);
        add(txtGenre);

        JLabel lblDirector = new JLabel("Director:");
        lblDirector.setBounds(50, 200, 100, 30);
        add(lblDirector);
        txtDirector = new JTextField();
        txtDirector.setBounds(150, 200, 200, 30);
        add(txtDirector);

        JLabel lblYear = new JLabel("Year:");
        lblYear.setBounds(50, 240, 100, 30);
        add(lblYear);
        txtYear = new JTextField();
        txtYear.setBounds(150, 240, 200, 30);
        add(txtYear);

        // Buttons for CRUD operations
        btnAdd = new JButton("Add Movie");
        btnAdd.setBounds(400, 80, 150, 30);
        add(btnAdd);

        btnFetch = new JButton("Fetch Movie");
        btnFetch.setBounds(400, 120, 150, 30);
        add(btnFetch);

        btnUpdate = new JButton("Update Movie");
        btnUpdate.setBounds(400, 160, 150, 30);
        add(btnUpdate);

        btnDelete = new JButton("Delete Movie");
        btnDelete.setBounds(400, 200, 150, 30);
        add(btnDelete);

        // Output area for displaying results or messages
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(50, 300, 500, 150);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        add(scrollPane);

        // Add Action Listeners for buttons
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMovie();
            }
        });

        btnFetch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchMovie();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMovie();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMovie();
            }
        });
    }

    // Method to Add Movie
    private void addMovie() {
        try {
            String title = txtTitle.getText();
            String genre = txtGenre.getText();
            String director = txtDirector.getText();
            int year = Integer.parseInt(txtYear.getText());

            Movie movie = new Movie(title, genre, director, year);
            movieDAO.addMovie(movie);
            outputArea.setText("Movie added successfully!\n" + movie.toString());
            clearFields();
        } catch (Exception ex) {
            outputArea.setText("Error adding movie: " + ex.getMessage());
        }
    }

    // Method to Fetch Movie by ID
    private void fetchMovie() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Movie movie = movieDAO.getMovieById(id);
            if (movie != null) {
                txtTitle.setText(movie.getTitle());
                txtGenre.setText(movie.getGenre());
                txtDirector.setText(movie.getDirector());
                txtYear.setText(String.valueOf(movie.getYear()));
                outputArea.setText("Fetched Movie: \n" + movie.toString());
            } else {
                outputArea.setText("Movie not found!");
            }
        } catch (Exception ex) {
            outputArea.setText("Error fetching movie: " + ex.getMessage());
        }
    }

    // Method to Update Movie
    private void updateMovie() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String title = txtTitle.getText();
            String genre = txtGenre.getText();
            String director = txtDirector.getText();
            int year = Integer.parseInt(txtYear.getText());

            Movie movie = new Movie(title, genre, director, year);
            movie.setId(id); // Set ID to update specific movie
            movieDAO.updateMovie(movie);
            outputArea.setText("Movie updated successfully!\n" + movie.toString());
            clearFields();
        } catch (Exception ex) {
            outputArea.setText("Error updating movie: " + ex.getMessage());
        }
    }

    // Method to Delete Movie by ID
    private void deleteMovie() {
        try {
            int id = Integer.parseInt(txtId.getText());
            movieDAO.deleteMovie(id);
            outputArea.setText("Movie deleted successfully!");
            clearFields();
        } catch (Exception ex) {
            outputArea.setText("Error deleting movie: " + ex.getMessage());
        }
    }

    // Clear all input fields after an operation
    private void clearFields() {
        txtId.setText("");
        txtTitle.setText("");
        txtGenre.setText("");
        txtDirector.setText("");
        txtYear.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
