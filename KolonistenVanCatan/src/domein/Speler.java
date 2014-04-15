/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domein;

/**
 * In this class you can find all properties and operations for Speler. 
 * //CHECK
 *
 * @organization    
 * @author          anne
 * @date            9-apr-2014 
 */
public class Speler {
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private String naam;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Speler(String naam){
        this.naam = naam;
    }

    
    //</editor-fold>

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Events">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Oprarations">
    public String getNaam() {
        return naam;
    }

}
