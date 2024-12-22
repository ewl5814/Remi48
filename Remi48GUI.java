package gui;

import java.util.Arrays;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Observer;
import model.Remi48Model;

public class Remi48GUI extends Application implements Observer<Remi48Model, String> {
    private Label message;
    private final BorderPane inside;
    private Remi48Model model;
    private Stage stage;
    private HashMap<int[], Button> buttons = null;
    public Remi48GUI() {
        this.buttons = new HashMap<>();
        this.inside= new BorderPane();
    }
    @Override
    public void init() {
        model = new Remi48Model();
        model.addObserver(this);
        //model.newGame();
        //update(model, "Update");
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.message = new Label("Welcome to Remi48, Bookworm :)");
        this.stage = stage;
        BorderPane outside = new BorderPane();
        outside.setCenter(inside);

        outside.setTop(sn(message));
        GridPane grid = makeGridPane();
        inside.setCenter(grid);

        model.newGame();
        update(model, "Update");
        Button reset = new Button("New Game");
        reset.setOnAction(event -> {model.newGame();});

        outside.setBottom(sn(reset));
        Button north = new Button("^");
        north.setOnAction(event -> this.model.shiftBoard("N"));
        //inside.setTop(sn(north));

        Button south = new Button("v");
        south.setOnAction(event -> this.model.shiftBoard("S"));
        //inside.setBottom(sn(south));

        Button west = new Button("<");
        west.setOnAction(event -> this.model.shiftBoard("W"));
        //inside.setLeft(ew(west));

        Button east = new Button(">");
        east.setOnAction(event -> this.model.shiftBoard("E"));
        //inside.setRight(ew(east));

        HBox wse = new HBox();
        wse.getChildren().addAll(west,south,east);
        HBox wsea = sn(wse);
        HBox n = sn(north);
        VBox b = new VBox();
        b.getChildren().addAll(n,wsea);
        inside.setBottom(b);

        stage.setTitle("Remi48");
        Scene scene = new Scene(outside);
        stage.setScene(scene);
        stage.show();
    }

    private GridPane makeGridPane(){
        GridPane gridPane = new GridPane();
        //this.buttons = new HashMap<>();
        for (int row=0; row<4; ++row) {
            for (int col=0; col<4; ++col) {
                Button button = new Button();
                int buttonsize = 60;
                button.setMinSize(buttonsize, buttonsize);
                this.buttons.put(new int[]{row, col}, button);
                gridPane.add(button, col, row);
            }
        }
        return gridPane;
    }
    private HBox sn(Node s) {
        HBox t = new HBox();
        Region t1 = new Region();
        HBox.setHgrow(t1, Priority.ALWAYS);
        Region t2 = new Region();
        HBox.setHgrow(t2, Priority.ALWAYS);
        t.getChildren().addAll(t1,s,t2);
        return t;
    }

    /*private VBox ew(Node s) {
        VBox t = new VBox();
        Region t1 = new Region();
        VBox.setVgrow(t1, Priority.ALWAYS);
        Region t2 = new Region();
        VBox.setVgrow(t2, Priority.ALWAYS);
        t.getChildren().addAll(t1,s,t2);
        return t;
    }*/
    @Override
    public void update(Remi48Model remi48Model, String s) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int[] coords = new int[] {i, j};
                for (int[] key : buttons.keySet()) {
                    if (Arrays.equals(coords, key)) {
                        //if (model.getBoard().getGrid()[i][j] == 0) {
                        //    //this.buttons.get(key).setGraphic(new ImageView(greenDisk));
                        //    this.buttons.get(key).setText("0");
                        //}
                        //else {
                            switch (model.getBoard().getGrid()[i][j]) {
                                case 0 -> {this.buttons.get(key).setStyle("-fx-background-color: New.css");}
                                case 2 -> {this.buttons.get(key).setStyle("-fx-background-color: #FFECD6");}
                                case 4 -> {this.buttons.get(key).setStyle("-fx-background-color: #FFD4A8");}
                                case 8 -> {this.buttons.get(key).setStyle("-fx-background-color:#FFAE52");}
                                case 16 -> {this.buttons.get(key).setStyle("-fx-background-color:#FF8C00");}
                                case 32 -> {this.buttons.get(key).setStyle("-fx-background-color:#ED6F50");}
                                case 64 -> {this.buttons.get(key).setStyle("-fx-background-color:#E95D3A");}
                                case 128 -> {this.buttons.get(key).setStyle("-fx-background-color:#E4CA49");}
                                case 256 -> {this.buttons.get(key).setStyle("-fx-background-color:#F5CD05");}
                                case 512 -> {this.buttons.get(key).setStyle("-fx-background-color:#E1BC05");}
                                case 1024 -> {this.buttons.get(key).setStyle("-fx-background-color:#D08343");}
                                case 2048 -> {this.buttons.get(key).setStyle("-fx-background-color:#975926");}
                            }
                            this.buttons.get(key).setText(String.valueOf(model.getBoard().getGrid()[i][j]));
                        //}
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        if (args.length != 0) {
            System.out.println("Usage:");
            System.exit(0);
        } else {
            Application.launch(args);
        }
    }
}