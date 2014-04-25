/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.ontwikkelingskaarten.IOntwikkelingskaart;
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public Speler(String naam, Kleur kleur) {
        this.gebruikersnaam = naam;
        this.kleur = kleur;
        this.Vestigingen = new ArrayList<>();
        this.Ontwikkelingskaarten = new ArrayList<>();
        this.aantallenVanOntwikkelingskaarten = new ArrayList<Object[]>();
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
                Object[] Monopoliekaart = new Object[1];
                Monopoliekaart[0] = Monopoliekaart;
                Monopoliekaart[1] = (int) Monopoliekaart[0] + 1;
                aantallenVanOntwikkelingskaarten.add(Monopoliekaart);
                break;
            case "Overwinningspuntkaart":
                Object[] Overwinningspuntkaart = new Object[1];
                Overwinningspuntkaart[0] = Overwinningspuntkaart;
                Overwinningspuntkaart[1] = (int) Overwinningspuntkaart[1] + 1;
                aantallenVanOntwikkelingskaarten.add(Overwinningspuntkaart);
            case "Ridderkaart":
                Object[] Ridderkaart = new Object[1];
                Ridderkaart[0] = Ridderkaart;
                Ridderkaart[1] = (int) Ridderkaart[1] + 1;
                aantallenVanOntwikkelingskaarten.add(Ridderkaart);
            case "Stratenbouwkaart":
                Object[] Stratenbouwkaart = new Object[1];
                Stratenbouwkaart[0] = Stratenbouwkaart;
                Stratenbouwkaart[1] = (int) Stratenbouwkaart[1] + 1;
                aantallenVanOntwikkelingskaarten.add(Stratenbouwkaart);
            case "Uitvindingkaart":
                Object[] Uitvindingkaart = new Object[1];
                Uitvindingkaart[0] = Uitvindingkaart;
                Uitvindingkaart[1] = (int) Uitvindingkaart[1] + 1;
                aantallenVanOntwikkelingskaarten.add(Uitvindingkaart);
        }
    }

    public void removeOntwikkelingskaart(IOntwikkelingskaart ontwikkelingskaart) {
        Ontwikkelingskaarten.remove(ontwikkelingskaart);

        switch (ontwikkelingskaart.getNaam()) {
            case "Monopoliekaart":
                Object[] Monopoliekaart = new Object[1];
                Monopoliekaart[0] = Monopoliekaart;
                Monopoliekaart[1] = (int) Monopoliekaart[1] - 1;
                aantallenVanOntwikkelingskaarten.add(Monopoliekaart);
                break;
            case "Overwinningspuntkaart":
                Object[] Overwinningspuntkaart = new Object[1];
                Overwinningspuntkaart[0] = Overwinningspuntkaart;
                Overwinningspuntkaart[1] = (int) Overwinningspuntkaart[1] - 1;
                aantallenVanOntwikkelingskaarten.add(Overwinningspuntkaart);
            case "Ridderkaart":
                Object[] Ridderkaart = new Object[1];
                Ridderkaart[0] = Ridderkaart;
                Ridderkaart[1] = (int) Ridderkaart[1] - 1;
                aantallenVanOntwikkelingskaarten.add(Ridderkaart);
            case "Stratenbouwkaart":
                Object[] Stratenbouwkaart = new Object[1];
                Stratenbouwkaart[0] = Stratenbouwkaart;
                Stratenbouwkaart[1] = (int) Stratenbouwkaart[1] - 1;
                aantallenVanOntwikkelingskaarten.add(Stratenbouwkaart);
            case "Uitvindingkaart":
                Object[] Uitvindingkaart = new Object[1];
                Uitvindingkaart[0] = Uitvindingkaart;
                Uitvindingkaart[1] = (int) Uitvindingkaart[1] - 1;
                aantallenVanOntwikkelingskaarten.add(Uitvindingkaart);
        }
    }
    //</editor-fold>
}
