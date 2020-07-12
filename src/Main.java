import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("user_interface.fxml"));
        primaryStage.setTitle("Buscador");
        primaryStage.setScene(new Scene(root, 696, 400));
        primaryStage.setResizable(false);
        Image icon = new Image(getClass().getResourceAsStream("/images/search.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
