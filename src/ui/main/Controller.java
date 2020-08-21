package ui.main;

import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controller {

    public JFXProgressBar progressBar;
    @FXML
    private AnchorPane header;
    @FXML
    private JFXTextField searchBox;
    @FXML
    private ImageView weatherIcon;
    @FXML
    private Label locationLab;
    @FXML
    private Label lastUpdateLab;
    @FXML
    private VBox tempTile;
    @FXML
    private Label tempTileLab;
    @FXML
    private VBox visiblityTile;
    @FXML
    private Label visiblityTileLab;
    @FXML
    private VBox humidityTile;
    @FXML
    private Label humidityTileLab;
    @FXML
    private VBox windTile;
    @FXML
    private Label windTileLab;
    @FXML
    private VBox uvTile;
    @FXML
    private Label uvTileLab;


    @FXML
    public void initialize(){
    }

    public void searchLocation() {

        progressBar.setVisible(true);

        Runnable run = () -> {
            ArrayList<String> data = new ArrayList<>();
            try {
                Process process = Runtime.getRuntime().exec("python3 src/api/weatherInfoFetcher.py " + searchBox.getText());
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    data.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!data.isEmpty()) {
                Platform.runLater(() -> {
                    locationLab.setText(data.get(0));
                    tempTileLab.setText(data.get(1) + " C");
                    visiblityTileLab.setText(data.get(2) + " Km");
                    humidityTileLab.setText(data.get(3) + "%");
                    windTileLab.setText(data.get(4) + " " + data.get(5));
                    uvTileLab.setText(data.get(6) + " unit");
                    progressBar.setVisible(false);
                    searchBox.setText("");
                });
            } else {
                Platform.runLater(() -> {
                    locationLab.setText("Not found '" + searchBox.getText() + "'");
                    tempTileLab.setText("N/A");
                    visiblityTileLab.setText("N/A");
                    humidityTileLab.setText("N/A");
                    windTileLab.setText("N/A");
                    uvTileLab.setText("N/A");
                    progressBar.setVisible(false);
                    searchBox.setText("");
                });
            }
        };

        new Thread(run).start();

    }

}
