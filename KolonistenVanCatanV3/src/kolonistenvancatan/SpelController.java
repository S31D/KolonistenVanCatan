/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolonistenvancatan;

import domein.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Miquel
 */
public class SpelController {

    @FXML
    ImageView imgGraan;
    @FXML
    ImageView imgHout;
    @FXML
    ImageView imgSteen;
    @FXML
    ImageView imgWol;
    @FXML
    ImageView imgErts;
    @FXML
    ImageView imgHandelsroute;
    @FXML
    ImageView imgRiddermacht;
    @FXML
    ImageView imgRidder;
    @FXML
    ImageView imgMonopolie;
    @FXML
    ImageView imgUitvinding;
    @FXML
    ImageView imgOverwinningspunt;
    @FXML
    ImageView imgStratenbouw;
    @FXML
    Button btnHandelen;
    @FXML
    Button btnBeurt;
    @FXML
    Button btnHandelenbank;
    @FXML
    Label lbHout;
    @FXML
    Label lbGraan;
    @FXML
    Label lbSteen;
    @FXML
    Label lbWol;
    @FXML
    Label lbErts;
    @FXML
    TextArea taChatbox;
    @FXML
    TextField tfChatbox;
    public static Spel spel;
    public Speler speler1;
    public Speler speler2;
    public Speler speler3;
    public Speler speler4;
    public Speler actievespeler;
    

    /**
     *
     */
    public SpelController() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {  

                lbErts.setText("0");
                lbHout.setText("0");
                lbGraan.setText("0");
                lbWol.setText("0");
                lbSteen.setText("0");
                spel = new Spel(spel.getNaam());
                actievespeler = speler1;    
                btnBeurt.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        
                        if(actievespeler == speler1)
                        {
                            actievespeler = speler2;
                        }
                        if(actievespeler == speler2)
                        {
                            actievespeler = speler3;
                        }
                        if(actievespeler == speler3)
                        {
                            actievespeler = speler4;
                        }
                        if(actievespeler == speler4)
                        {
                            actievespeler = speler1;
                        }
                    }
        });
    }
        });
    }
    
    public void testConnectie() {
        try {
            spel.lobby.plaatsBericht("TEST", "TEST");
        } catch (RemoteException ex) {
            Logger.getLogger(KVCSpelGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
