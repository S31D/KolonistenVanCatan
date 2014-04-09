package kolonistenvancatan;

import domein.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * In this main class you can find all properties and operations for KVCSpelGUI.
 *
 * @organization: Fontys S31D
 * @author Anne Toonen
 * @date 24-03-2014
 */
public class KVCSpelGUI extends Application {
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Events">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Oprarations">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Scene Build">
    public Group tegenspeler(Speler speler){
        Group returner = new Group();
        return returner;
    }
    
    public Group Canvas(){
        Group returner = new Group();
        return returner;
    }
    
    public Group ActieveGuiSpeler(Speler speler){
        Group returner = new Group();
        return returner;
    }
    
    public Group Chat(){
        Group returner = new Group();
        return returner;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Start">
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //</editor-fold>
    
}
