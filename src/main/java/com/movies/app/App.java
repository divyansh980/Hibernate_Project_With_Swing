package com.movies.app;

import com.movies.dao.MovieDAO;
import com.movies.dao.impl.MovieDAOImpl;
import com.movies.model.Movie;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        MovieDAO movieDAO = new MovieDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Movie Management System ---");
            System.out.println("1. Add Movie");
            System.out.println("2. Fetch Movie by ID");
            System.out.println("3. Update Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Movie
                    System.out.print("Enter Movie Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Movie Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter Movie Director: ");
                    String director = scanner.nextLine();
                    System.out.print("Enter Movie Year: ");
                    int year = scanner.nextInt();

                    Movie movie = new Movie(title, genre, director, year);
                    movieDAO.addMovie(movie);
                    System.out.println("Movie added successfully.");
                    break;

                case 2: // Fetch Movie by ID
                    System.out.print("Enter Movie ID: ");
                    int fetchId = scanner.nextInt();
                    Movie fetchedMovie = movieDAO.getMovieById(fetchId);
                    if (fetchedMovie != null) {
                        System.out.println("Fetched Movie: " + fetchedMovie);
                    } else {
                        System.out.println("Movie not found!");
                    }
                    break;

                case 3: // Update Movie
                    System.out.print("Enter Movie ID to Update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Movie movieToUpdate = movieDAO.getMovieById(updateId);
                    if (movieToUpdate != null) {
                        System.out.print("Enter New Movie Title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter New Movie Genre: ");
                        String newGenre = scanner.nextLine();
                        System.out.print("Enter New Movie Director: ");
                        String newDirector = scanner.nextLine();
                        System.out.print("Enter New Movie Year: ");
                        int newYear = scanner.nextInt();

                        movieToUpdate.setTitle(newTitle);
                        movieToUpdate.setGenre(newGenre);
                        movieToUpdate.setDirector(newDirector);
                        movieToUpdate.setYear(newYear);

                        movieDAO.updateMovie(movieToUpdate);
                        System.out.println("Movie updated successfully.");
                    } else {
                        System.out.println("Movie not found!");
                    }
                    break;

                case 4: // Delete Movie
                    System.out.print("Enter Movie ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    movieDAO.deleteMovie(deleteId);
                    break;

                case 5: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
