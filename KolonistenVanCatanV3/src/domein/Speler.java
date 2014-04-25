/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.ontwikkelingskaarten.IOntwikkelingskaart;
import domein.ontwikkelingskaarten.Monopoliekaart;
import domein.ontwikkelingskaarten.Overwinningspuntkaart;
import domein.ontwikkelingskaarten.Ridderkaart;
import domein.ontwikkelingskaarten.Stratenbouwkaart;
import domein.ontwikkelingskaarten.Uitvindingkaart;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * In this class you can find all properties and operations for Speler. //CHECK
 *
 * @organization
 * @author anne
 * @date 9-apr-2014
 */
public class Speler implements Serializable {
    //<editor-fold defaultstate="collapsed" desc="Declarations">

    private final String gebruikersnaam;
    private ArrayList<Object[]> aantallenVanGrondstoffen;
    private ArrayList<Object[]> aantallenVanOntwikkelingskaarten;
    private int overwinningspunten;
    private final Kleur kleur;
    private ArrayList<Vesting> Vestigingen;
    private ArrayList<Straat> Straten;
    private ArrayList<IOntwikkelingskaart> Ontwikkelingskaarten;
    private int gespeeldeRidderkaarten;
    
        Object[] monopoliekaarten = null;
    Object[] overwinningspuntenkaarten = null;
    Object[] ridderkaarten = null;
    Object[] stratenbouwkaarten = null;
    Object[] uitvindingkaarten = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Speler(String naam, Kleur kleur) {
        this.gebruikersnaam = naam;
        this.kleur = kleur;
        this.Vestigingen = new ArrayList<>();
        this.Ontwikkelingskaarten = new ArrayList<>();
        this.aantallenVanOntwikkelingskaarten = new ArrayList<Object[]>();
        
        monopoliekaarten = new Object[2];
        Monopoliekaart monopoliekaart = new Monopoliekaart();
        monopoliekaarten[0] = monopoliekaart;
        monopoliekaarten[1] = 0;
        overwinningspuntenkaarten = new Object[2];
        Overwinningspuntkaart overwinningspuntkaart = new Overwinningspuntkaart();
        overwinningspuntenkaarten[0] = overwinningspuntkaart;
        overwinningspuntenkaarten[1] = 0;
        ridderkaarten = new Object[2];
        Ridderkaart ridderkaart = new Ridderkaart();
        ridderkaarten[0] = ridderkaart;
        ridderkaarten[1] = 0;
        stratenbouwkaarten = new Object[2];
        Stratenbouwkaart stratenbouwkaart = new Stratenbouwkaart();
        stratenbouwkaarten[0] = stratenbouwkaart;
        stratenbouwkaarten[1] = 0;
        uitvindingkaarten = new Object[2];
        Uitvindingkaart uitvindingkaart = new Uitvindingkaart();
        uitvindingkaarten[0] = uitvindingkaart;
        uitvindingkaarten[1] = 0;
        
        
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Oprarations">
    public String getNaam() {
        return gebruikersnaam;
    }

    public Kleur getKleur() {
        return kleur;
    }

    public int getOverwinningspunten() {
        return this.overwinningspunten;
    }

    public void setOverwinningspunten(int overwinningspunten) {
        this.overwinningspunten = overwinningspunten;
    }

    public int getGespeeldeRidderkaarten() {
        return this.gespeeldeRidderkaarten;
    }

    public void setGespeeldeRidderkaarten() {
        this.gespeeldeRidderkaarten = gespeeldeRidderkaarten + 1;
    }

    public boolean voorraadToereikend(Grondstof grondstof, int aantal) {
        boolean toereikend = false;
        for (Object[] o : aantallenVanGrondstoffen) {
            Grondstof gs = (Grondstof) o[0];
            int i = (int) o[1];
            if (gs == grondstof && i >= aantal) {

                return true;
            }
        }
        return toereikend;
    }

    public int aantalGrondstoffen(Grondstof grondstof) {
        for (Object[] o : aantallenVanGrondstoffen) {
            if (o[0] == grondstof) {
                return (int) o[1];
            }
        }
        return 0;
    }

    public boolean moetGrondstoffenInleveren() {
        int aantal = 0;
        for (Object[] o : aantallenVanGrondstoffen) {
            for (Object o2 : o) {
                aantal += (int) o[1];
            }
        }
        return (aantal > 7);
    }

    public void setGrondstof(Grondstof grondstof, int aantal) {
        for (Object[] o : aantallenVanGrondstoffen) {
            if ((Grondstof) o[0] == grondstof) {
                if (aantal + (int) o[1] >= 0) {
                    o[1] = aantal + (int) o[1];
                }
            }
        }
    }

    public void setDorp(Vesting v) {
        int count = 0;
        for (Vesting vesting : Vestigingen) {
            if (vesting.getIsStad() == false) {
                count = count + 1;
            }
        }
        if (count <= 4) {
            Vestigingen.add(v);
        }
    }

    public void setStad(Vesting v) {
        int count = 0;
        for (Vesting vesting : Vestigingen) {
            if (vesting.getIsStad() == true) {
                count = count + 1;
            }
        }
        if (count <= 3) {
            Vestigingen.add(v);
        }
    }

    public void setStraat(Straat s) {
        Straten.add(s);
    }

    public ArrayList<Straat> getStraten() {
        return Straten;
    }

    public ArrayList<Vesting> getVestigingen() {
        return this.Vestigingen;
    }

    public void grondstofInnen(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<IOntwikkelingskaart> getOntwikkelingskaarten() {
        return this.Ontwikkelingskaarten;
    }

    public void addOntwikkelingskaarten(IOntwikkelingskaart ontwikkelingskaart) {
        Ontwikkelingskaarten.add(ontwikkelingskaart);

        switch (ontwikkelingskaart.getNaam()) {
            case "Monopoliekaart":
                Object[] Monopoliekaart = new Object[2];
                Monopoliekaart[0] = Monopoliekaart;
                Monopoliekaart[1] = (int) Monopoliekaart[0] + 1;
                aantallenVanOntwikkelingskaarten.set(0, Monopoliekaart);
                break;
            case "Overwinningspuntkaart":
                Object[] Overwinningspuntkaart = new Object[2];
                Overwinningspuntkaart[0] = Overwinningspuntkaart;
                Overwinningspuntkaart[1] = (int) Overwinningspuntkaart[1] + 1;
                aantallenVanOntwikkelingskaarten.set(1, Overwinningspuntkaart);
                break;
            case "Ridderkaart":
                Object[] Ridderkaart = new Object[2];
                Ridderkaart[0] = Ridderkaart;
                Ridderkaart[1] = (int) Ridderkaart[1] + 1;
                aantallenVanOntwikkelingskaarten.set(2, Ridderkaart);
                break;
            case "Stratenbouwkaart":
                Object[] Stratenbouwkaart = new Object[2];
                Stratenbouwkaart[0] = Stratenbouwkaart;
                Stratenbouwkaart[1] = (int) Stratenbouwkaart[1] + 1;
                aantallenVanOntwikkelingskaarten.set(3, Stratenbouwkaart);
                break;
            case "Uitvindingkaart":
                Object[] Uitvindingkaart = new Object[2];
                Uitvindingkaart[0] = Uitvindingkaart;
                Uitvindingkaart[1] = (int) Uitvindingkaart[1] + 1;
                aantallenVanOntwikkelingskaarten.set(4, Uitvindingkaart);
                break;
        }
    }

    public void verwijderOntwikkelingskaart(IOntwikkelingskaart ontwikkelingskaart) {
        Ontwikkelingskaarten.remove(ontwikkelingskaart);

        switch (ontwikkelingskaart.getNaam()) {
            case "Monopoliekaart":
                Object[] Monopoliekaart = new Object[2];
                Monopoliekaart[0] = Monopoliekaart;
                Monopoliekaart[1] = (int) Monopoliekaart[1] - 1;
                aantallenVanOntwikkelingskaarten.set(0, Monopoliekaart);
                break;
            case "Overwinningspuntkaart":
                Object[] Overwinningspuntkaart = new Object[2];
                Overwinningspuntkaart[0] = Overwinningspuntkaart;
                Overwinningspuntkaart[1] = (int) Overwinningspuntkaart[1] - 1;
                aantallenVanOntwikkelingskaarten.set(1, Overwinningspuntkaart);
            case "Ridderkaart":
                Object[] Ridderkaart = new Object[2];
                Ridderkaart[0] = Ridderkaart;
                Ridderkaart[1] = (int) Ridderkaart[1] - 1;
                aantallenVanOntwikkelingskaarten.set(2, Ridderkaart);
            case "Stratenbouwkaart":
                Object[] Stratenbouwkaart = new Object[2];
                Stratenbouwkaart[0] = Stratenbouwkaart;
                Stratenbouwkaart[1] = (int) Stratenbouwkaart[1] - 1;
                aantallenVanOntwikkelingskaarten.set(3, Stratenbouwkaart);
            case "Uitvindingkaart":
                Object[] Uitvindingkaart = new Object[2];
                Uitvindingkaart[0] = Uitvindingkaart;
                Uitvindingkaart[1] = (int) Uitvindingkaart[1] - 1;
                aantallenVanOntwikkelingskaarten.set(4, Uitvindingkaart);
        }
    }
    //</editor-fold>
}
