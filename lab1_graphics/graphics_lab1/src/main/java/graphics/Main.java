package graphics;

import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.shape.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        scene.setFill(Color.rgb(121, 122, 127));


        Rectangle r = new Rectangle(240, 150, 20, 200);
        root.getChildren().add(r);
        r.setFill(Color.rgb(0, 0, 0));
        Polygon triangle2 = new Polygon(250, 30, 180, 170, 320, 170);
        root.getChildren().add(triangle2);
        triangle2.setFill(Color.rgb(255, 0, 0));
        Polygon triangle = new Polygon(250, 50, 200, 160, 300, 160);
        root.getChildren().add(triangle);
        triangle.setFill(Color.rgb(255, 255, 255));

        Circle circle1 = new Circle(250,85,13);
        root.getChildren().add(circle1);
        circle1.setFill(Color.rgb(255, 0, 0));

        Circle circle2 = new Circle(250,115,13);
        root.getChildren().add(circle2);
        circle2.setFill(Color.rgb(255, 255, 0));

        Circle circle3 = new Circle(250,145,13);
        root.getChildren().add(circle3);
        circle3.setFill(Color.rgb(0, 255, 50));


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
