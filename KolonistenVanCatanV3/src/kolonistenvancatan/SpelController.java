/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolonistenvancatan;

import domein.*;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

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
    public static Spel spel;

    /**
     *
     */
    public SpelController() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                //Todo
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
