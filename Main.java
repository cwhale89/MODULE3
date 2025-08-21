package app;  // <-- if you didn't make a package, delete this line

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class Main extends Application {
    private Text textBox;  
    private BorderPane root;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        textBox = new Text("Welcome! Use the menu above.");
        root.setCenter(textBox);

        // Menu bar
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Options");

        // Menu items
        MenuItem dateTimeItem = new MenuItem("Show Date & Time");
        MenuItem saveLogItem = new MenuItem("Save to log.txt");
        MenuItem randomColorItem = new MenuItem("Random Orange Hue");
        MenuItem exitItem = new MenuItem("Exit");

        menu.getItems().addAll(dateTimeItem, saveLogItem, randomColorItem, exitItem);
        menuBar.getMenus().add(menu);

        root.setTop(menuBar);

        // Actions
        dateTimeItem.setOnAction(e -> showDateTime());
        saveLogItem.setOnAction(e -> saveToFile());
        randomColorItem.setOnAction(e -> changeBackground());
        exitItem.setOnAction(e -> primaryStage.close());

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setTitle("User Interface with Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDateTime() {
        String dateTime = LocalDateTime.now().toString();
        textBox.setText("Current Date & Time: " + dateTime);
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(textBox.getText() + System.lineSeparator());
            textBox.setText("Saved to log.txt!");
        } catch (IOException ex) {
            textBox.setText("Error writing to file.");
        }
    }

    private void changeBackground() {
        Random rand = new Random();
        double hue = 30 + rand.nextInt(20) - 10; 
        double saturation = 0.7 + (0.3 * rand.nextDouble());
        double brightness = 0.7 + (0.3 * rand.nextDouble());
        Color randomOrange = Color.hsb(hue, saturation, brightness);

        root.setStyle("-fx-background-color: " + toHex(randomOrange) + ";");
        textBox.setText("Background changed to: " + toHex(randomOrange));
    }

    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
