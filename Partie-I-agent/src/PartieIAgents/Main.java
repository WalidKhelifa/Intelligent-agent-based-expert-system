package PartieIAgents;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage stage;
    static Connection connection;

    public Main() {
    }

    public void start(Stage primaryStage) throws IOException {
        Parent parent = (Parent)FXMLLoader.load(this.getClass().getResource("Home.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        stage = primaryStage;
        stage.setTitle("Accueil");
    }

    public static void main(String[] args) throws SQLException {
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8889/agent", "root", "root");
        connection = myConn;
        launch(args);
    }
}
