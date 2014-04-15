package kolonistenvancatan;

import domein.Spel;
import domein.Speler;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    //Spel
    private Spel spel;
    private Speler GUISpeler;
    
    
    //Canvas
    private Canvas catan;
    private GraphicsContext gc;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Events">

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Teken op Canvas">
    private void drawBord(){
        /*
        // Graphics
        gc = catan.getGraphicsContext2D();
        Image imageTegel = null;
        Image imageNummer = null;
        spel.getBord().getAlleTegels();
        
        //voor elke tegel
        for (Tegel t : spel.getBord().getAlleTegels()) {
            double tegelX = t.getPlaats().getImagePositie().getX();
            double tegelY = t.getPlaats().getImagePositie().getY();

            if (t instanceof Woestijntegel) {
                imageTegel = new Image("file:src/Images/Tegels/Woestijn.png");
                gc.drawImage(imageTegel, tegelX, tegelY);

            } else if (t instanceof Landtegel) {
                Landtegel lt = (Landtegel) t;
                imageNummer = new Image("file:src/Image/GetalFishes/Getal_" + lt.getLandnummer());
                double nummerX = t.getPlaats().getNumberPositie().getX();
                double nummerY = t.getPlaats().getNumberPositie().getY();

                switch (lt.getGrondstof()) {
                    case HOUT:
                        imageTegel = new Image("file:src/Images/Tegels/Bos.png");
                        break;
                    case BAKSTEEN:
                        imageTegel = new Image("file:src/Images/Tegels/Weide.png");
                        break;
                    case GRAAN:
                        imageTegel = new Image("file:src/Images/Tegels/Akker.png");
                        break;
                    case WOL:
                        imageTegel = new Image("file:src/Images/Tegels/Heuvel.png");
                        break;
                    case ERTS:
                        imageTegel = new Image("file:src/Images/Tegels/Berg.png");
                        break;
                }
                gc.drawImage(imageTegel, nummerX, nummerY);
                gc.drawImage(imageNummer, tegelY, tegelY);

            } else if (t instanceof Haventegel && !spel.getBord().isOptieRand()) {
                Haventegel ht = (Haventegel) t;
                if (ht.getGrondstof() == null) {
                    imageTegel = new Image("file:src/Images/Tegels/Haven_Gewoon.png");
                } else {
                    switch (ht.getGrondstof()) {
                        case HOUT:
                            imageTegel = new Image("file:src/Images/Tegels/Haven_Hout_" + ht.getHavezijde() + ".png");
                            break;
                        case BAKSTEEN:
                            imageTegel = new Image("file:src/Images/Tegels/Haven_Wol_" + ht.getHavezijde() + ".png");
                            break;
                        case GRAAN:
                            imageTegel = new Image("file:src/Images/Tegels/Haven_Graan_" + ht.getHavezijde() + ".png");
                            break;
                        case WOL:
                            imageTegel = new Image("file:src/Images/Tegels/Haven_Backsteen_" + ht.getHavezijde() + ".png");
                            break;
                        case ERTS:
                            imageTegel = new Image("file:src/Images/Tegels/Haven_Erts_" + ht.getHavezijde() + ".png");
                            break;
                    }
                    gc.drawImage(imageTegel, tegelX, tegelY);
                }
            } else if (t instanceof Zeetegel && !spel.getBord().isOptieRand()) {
                imageTegel = new Image("file:src/Images/Tegels/Zee.png");
                gc.drawImage(imageTegel, tegelX, tegelY);

            } else if (spel.getBord().isOptieRand()) {
                imageTegel = new Image("file:src/Images/Tegels/Rand.png");
                gc.drawImage(imageTegel, 0, 0);
            }
        }
        */
    }
    
    
    //</editor-fold>
    
    //Dorp bouwen
    public Point2D keuzePlaatsDorp(ArrayList<Point2D> mogelijkheden){
        Point2D returner;
        returner = mogelijkheden.get(0);
        
        return returner;
    }
    
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Scene Build">
    private Group tegenspeler(Speler speler){
        Group returner = new Group();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(3);
        gridPane.setVgap(3);
        
        Circle circle = new Circle(60);
        Rectangle rectangle = new Rectangle();
        Text text = new Text(speler.getNaam());
             
        return returner;
    }
    
    private VBox tegenspelers(ArrayList<Speler> spelers){
        VBox returner = new VBox();
        for(int i= 0; i< spelers.size(); i++){
            returner.getChildren().add(tegenspeler(spelers.get(i)));
        }      
        return returner;
    }
    
    public Group Canvas(){
        Group returner = new Group();
        GridPane gridPane = new GridPane();
        catan = new Canvas(500, 500);
        drawBord();
        gridPane.add(catan, 0, 0, 25, 25);
        returner.getChildren().add(gridPane);
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
    
    //<editor-fold defaultstate="collapsed" desc="Popup Build">
    public void popupGUISpeler(){
        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);
              
        final TextField tfGUISpeler = new TextField();
        Button okButton = new Button("Ok");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                GUISpeler = new Speler(tfGUISpeler.getText());
                myDialog.close();
            }
        });
        
        Scene myDialogScene = new Scene(VBoxBuilder.create()
                .children(tfGUISpeler, okButton)
                .spacing(30)
                .alignment(Pos.CENTER)
                .padding(new Insets(10))
                .build());

        myDialog.setScene(myDialogScene);
        myDialog.show();
    }
    
    public void popupErrorGrondstoffen() {
        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.APPLICATION_MODAL);
        Button okButton = new Button("Ok");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                myDialog.close();
            }
        });
        
        Scene myDialogScene = new Scene(VBoxBuilder.create()
                .children(new Text("Je hebt te weinig grondstoffen"), okButton)
                .spacing(30)
                .alignment(Pos.CENTER)
                .padding(new Insets(10))
                .build());

        myDialog.setScene(myDialogScene);
        myDialog.show();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Start">
    @Override
    public void init(){
        //Spel
    }
    
    public void start(Stage stage, Spel spel, Speler speler){
        this.spel = spel;
        this.GUISpeler = speler;
        start(stage);
    }
    
    @Override
    public void start(Stage primaryStage) {
        //Spelers
        ArrayList<Speler> tegenspelers = new ArrayList<>();
        
        
        for(int i =0; i< spel.getSpelers().size(); i++){
            if(GUISpeler.getNaam() == spel.getSpelers.get(i).getNaam()){
                
            }
        }
         
        
        
        
                
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(tegenspeler(new Speler("Anne")));
        borderPane.setCenter(Canvas());
        borderPane.setBottom(ActieveGuiSpeler(new Speler("Thomas")));
        borderPane.setRight(tegenspeler(new Speler("Daan")));
        
        StackPane root = new StackPane();
        root.getChildren().add(borderPane);
        root.getChildren().add(Chat());
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Kolonisten Van Catan");
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
