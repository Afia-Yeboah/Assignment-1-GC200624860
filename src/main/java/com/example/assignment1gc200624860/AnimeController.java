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

import java.io.IOException;
import java.sql.*;

public class AnimeController {
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
    }

    private void loadAnime() {
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
                animeList.add(a);
            }
            System.out.println("Generated Anime: " + animeList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


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

