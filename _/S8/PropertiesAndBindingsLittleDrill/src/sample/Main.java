package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BorderPane root = new BorderPane();
        TextArea view1 = new TextArea();
        TextArea view2 = new TextArea();
        MyModel model = new MyModel();

        view1.textProperty().addListener( e->{
            model.setSimpleString(view1.getText());
        });

        view2.textProperty().bind(model.SimpleString());

        root.setTop(view1);
        root.setBottom(view2);


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
