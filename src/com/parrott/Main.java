package com.parrott;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    private Stage window;
    private Scene scene;
    private VBox layout;
    private HBox pathBox;
    private HBox ipBox;

    private TextField pathText;
    private TextField ipText;
    private TextField portText;


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        pathText = new TextField();
        pathText.setMinWidth(274);
        pathText.setPromptText("Path");
        Button browseButton = new Button("...");
        browseButton.setOnAction(e -> browse());
        pathBox = new HBox(pathText, browseButton);

        ipText = new TextField();
        ipText.setPromptText("Ip Address");
        portText = new TextField();
        portText.setPromptText("Port");
        ipBox = new HBox(ipText, portText);

        Button doneButton = new Button("Done");
        doneButton.setOnAction(e ->done());
        doneButton.setMinSize(298, 10);

        layout = new VBox(pathBox, ipBox, doneButton);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setSpacing(10);

        scene = new Scene(layout);
        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }

    private ObservableList<Direction> directionObservableList;

    private void browse(){
        directionObservableList = Saver.open(window);
        for(Object object : directionObservableList){
            Direction direction = (Direction) object;
           System.out.println(direction.getFormatedString());
        }

        pathText.setText(Saver.path);

    }

    private void done() {
        Networker.start(ipText.getText(), Integer.parseInt(portText.getText()));

        for (Direction direction : directionObservableList){
            Networker.sendRightSpeed(direction.getRightSpeed());
            Networker.sendLeftSpeed(direction.getLeftSpeed());

            try {
                Thread.sleep((long) direction.getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(direction.getDelay());

        }

        Networker.doneDirections = true;
        window.close();

    }
}
