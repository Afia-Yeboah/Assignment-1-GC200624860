package com.example.assignment1gc200624860;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;

/**
 * This controls the chart scene
 * It loads the Anime data and populate the bar char
 * it also handles the View Table button
 */
public class AnimeChartController {

    @FXML
    private BarChart<String, Number> animeChart;

    @FXML
    private CategoryAxis catAxis;

    @FXML
    private Button goToTable;

    @FXML
    private NumberAxis numAxis;

    // fetch the list of the anime rows
    private final ObservableList<Anime> animeList = FXCollections.observableArrayList();

    /**
     * After the FXML loads: pull data and draw up the top 10 bars
     */
    @FXML
    private void initialize() {
        // fetch the list from the DB
        loadAnime();

        // populate the top 10 popular anime
        populateChart();
    }

    // load all the Anime row into the animeList from DB
    private void loadAnime() {
        String sql = "SELECT title, genre, year, episodes, rating, popularity FROM AnimeData";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                animeList.add(new Anime(
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getInt("episodes"),
                        rs.getDouble("rating"),
                        rs.getDouble("popularity")
                ));
            }
            System.out.println("Loaded Anime: " + animeList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display the top 10 popularity into the bar chart
     */
    private void populateChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Top 10 Animes By Popularity");

        animeList.stream()
                .sorted((a,b) -> Double.compare(b.getPopularity(), a.getPopularity()))
                .limit(10)
                .forEach(a ->
                        series.getData().add(
                                new XYChart.Data<>(a.getTitle(), a.getPopularity())
                        )
                );
        animeChart.getData().add(series);
    }

    /**
     * Switching between the table scene when the button is clicked
     */
    @FXML
    private void goToTable(ActionEvent event) throws IOException {
        Stage stage = (Stage) goToTable.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("anime-table-view.fxml")
        );
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}
