package com.example.assignment1gc200624860;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.stream.Collectors;

import java.io.IOException;
import java.sql.*;

/**
 * This controls the table view scene by
 * loading the Anime data from the database
 * populate the table and handles the back to chart button
 */
public class AnimeController {
    // the entire data that holds all the anime informatiom that is
    // filtered into the animeList
    private final ObservableList<Anime> masterList = FXCollections.observableArrayList();
    private final ObservableList<Anime> animeList = FXCollections.observableArrayList();

    @FXML
    private TableView<Anime> animeTable;

    @FXML
    private TableColumn<Anime, String> titleCol;

    @FXML
    private TableColumn<Anime, String> genreCol;

    @FXML
    private TableColumn<Anime, Integer> yearCol;

    @FXML
    private TableColumn<Anime, Integer> episodesCol;

    @FXML
    private TableColumn<Anime, Double> ratingCol;

    @FXML
    private TableColumn<Anime, Double> popularityCol;

    @FXML
    private Button backToChart;

    @FXML
    private Button showAllButton;

    @FXML
    private void initialize() {
        // Fetch the Anime from the DB into the animeList
        loadAnime();

        // Set up each column to the Anime property
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        episodesCol.setCellValueFactory(new PropertyValueFactory<>("episodes"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        popularityCol.setCellValueFactory(new PropertyValueFactory<>("popularity"));

        // binding the data to the table
        animeTable.setItems(animeList);

        // Display the top 10 popular anime
        showTop10();
    }

    /**
     * Fetch all the Anime rows from DB into the animeList
     */
    private void loadAnime() {
        //masterList.clear();
        String sql = "SELECT title, genre, year, episodes, rating, popularity FROM AnimeData";

        try (Connection conn = DBConnection.getConnection();
             // prepare and execute the query
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // loop through each row
            while (rs.next()) {
                Anime a = new Anime(
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getInt("episodes"),
                        rs.getDouble("rating"),
                        rs.getDouble("popularity")
                );
                masterList.add(a);
            }
            System.out.println("Generated Anime: " + animeList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display only the top 10 by popularity in the table
     */
    private void showTop10() {
        animeList.setAll(
                masterList.stream()
                        .sorted((a, b) -> Double.compare(b.getPopularity(), a.getPopularity()))
                        .limit(10)
                        .toList()
        );
    }

    /**
     * Display every anime in the table when button is pressed
     * */
    @FXML
    private void onShowAll(ActionEvent event) {
        animeList.setAll(masterList);
    }

    /**
     * Function to switch to the chart scene when button in the view
     * is clicked
     */
    @FXML
    private void goToChart(ActionEvent event) throws IOException {
        Stage stage = (Stage) backToChart.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("anime-chart-view.fxml")
        );
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

}

