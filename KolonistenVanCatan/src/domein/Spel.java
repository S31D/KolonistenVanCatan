/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.tegels.Tegel;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import kolonistenvancatan.KVCSpelGUI;

/**
 *
 * @author anne
 */
public class Spel {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //nodig bij spel constructor
    private final String naam;
    private final ArrayList<Speler> spelers;
    //Speelstukken
    private Speler langsteHandelsroute;
    private Speler grootsteRiddermacht;
    private final Struikrover struikrover;
    private final Bord bord;
    //GUI
    private Speler activeSpeler;
    private KVCSpelGUI gui;
    //</editor-fold>

    //<editor-fold desc="Operations">
    //<editor-fold defaultstate="collapsed" desc="Constructor(naam)">
    /**
     *
     * @param naam
     * @param spelers
     */
    public Spel(String naam, ArrayList<Speler> spelers) {
        this.naam = naam;
        this.spelers = spelers;

        //TODO
        //this.Ontwikkelingskaarten = addOntwikkelingskaarten();
        this.struikrover = new Struikrover();
        this.bord = new Bord();
        gui = new KVCSpelGUI();
        //


    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Operations">
    public String getNaam() {
        return this.naam;
    }

    public Iterator getSpelers() {
        return this.spelers.iterator();
    }

    public boolean afstandsRegel(Point2D plaats) {
        throw new UnsupportedOperationException();
    }

    public void dorpBouwen(Speler speler) {
        ArrayList<Point2D> mogelijkePlaatsen = new ArrayList<Point2D>();
        if (speler.voorraadToereikend(Grondstof.HOUT, 1) && speler.voorraadToereikend(Grondstof.GRAAN, 1) && speler.voorraadToereikend(Grondstof.WOL, 1) && speler.voorraadToereikend(Grondstof.BAKSTEEN, 1)) {
            for (Point2D p : controleerBeschikbaarheidVestiging(true)) {
                mogelijkePlaatsen.add(p);

                Point2D dorpPlek = gui.keuzePlaatsDorp(mogelijkePlaatsen);
                Vesting v = new Vesting(dorpPlek, speler.getKleur(), false);
                speler.setDorp(v);
                gui.setDorp(v);

                speler.setOverwinningspunten(speler.getOverwinningspunten() + 1);
                speler.setGrondstof(Grondstof.HOUT, speler.aantalGrondstoffen(Grondstof.HOUT) - 1);
                speler.setGrondstof(Grondstof.GRAAN, speler.aantalGrondstoffen(Grondstof.GRAAN) - 1);
                speler.setGrondstof(Grondstof.BAKSTEEN, speler.aantalGrondstoffen(Grondstof.BAKSTEEN) - 1);
                speler.setGrondstof(Grondstof.WOL, speler.aantalGrondstoffen(Grondstof.WOL) - 1);

            }
        } else {
        }
    }

    public void creëerStad(Speler speler) {
        ArrayList<Point2D> mogelijkeDorpen = new ArrayList<Point2D>();
        if (speler.voorraadToereikend(Grondstof.GRAAN, 2) && speler.voorraadToereikend(Grondstof.ERTS, 3)) {
            for (Point2D p : controleerBeschikbaarheidVestiging(false)) {
                mogelijkeDorpen.add(p);

                Point2D stadPlek = gui.keuzePlaatsDorp(mogelijkeDorpen);
                Vesting v = new Vesting(stadPlek, speler.getKleur(), false);
                speler.setStad(v);
                gui.setStad(v);

                speler.setOverwinningspunten(speler.getOverwinningspunten() + 1);
                speler.setGrondstof(Grondstof.HOUT, speler.aantalGrondstoffen(Grondstof.GRAAN) - 2);
                speler.setGrondstof(Grondstof.GRAAN, speler.aantalGrondstoffen(Grondstof.ERTS) - 3);
            }
        } else {
        }
    }

    public void straatBouwen(Speler speler) {
        if (speler.voorraadToereikend(Grondstof.HOUT, 1) && speler.voorraadToereikend(Grondstof.BAKSTEEN, 1)) {
            ArrayList<Straat> straten = this.controleerBeschikbaarheidStraat();
            Straat s = gui.getStraat(straten, speler.getKleur());
            speler.setStraat(s);
            gui.setStraat(s);
            speler.setGrondstof(Grondstof.HOUT, speler.aantalGrondstoffen(Grondstof.HOUT) - 1);
            speler.setGrondstof(Grondstof.BAKSTEEN, speler.aantalGrondstoffen(Grondstof.BAKSTEEN) - 1);
        }
    }

    public void straatBouwenKaart(Speler speler) {
        ArrayList<Straat> straten = this.controleerBeschikbaarheidStraat();
        Straat s = gui.getStraat(straten, speler.getKleur());
        speler.setStraat(s);
        gui.setStraat(s);
    }

    public boolean Handelen(Speler speler, ArrayList<Object[]> grondstoffenWillen, ArrayList<Object[]> grondstoffenGeven) {
        ArrayList<Speler> spelersRuilen = new ArrayList<Speler>();

        for (Speler s : spelers) {
            if (gui.wilHandellen(grondstoffenWillen, grondstoffenGeven)) {
                spelersRuilen.add(s);
            }
        }
        if (spelersRuilen.isEmpty()) {
            return false;
        } else {
            Speler sp = gui.willenHandelen(spelersRuilen);

            speler.setGrondstof(grondstofWillen[0], grondstofWillen[1]);
            speler.setGrondstof(grondstofGeven[0], (grondstofGeven[1] * -1);

            sp.setGrondstof(grondstofWillen[0], (grondstofWillen[1] * -1);
            sp.setGrondstof(grondstoffenGeven[0], grondstoffenGeven[1]);
            return true;
        }
    }

    public void HandelenBank(Speler speler, ArrayList<Object[]> grondstoffenWillen, ArrayList<Object[]> grondstoffenGeven) {
        speler.setGrondstof(grondstofWillen[0], grondstofWillen[1]);
        speler.setGrondstof(grondstofGeven[0], (grondstofGeven[1] * -1);
    }

    public void dobbelen() {
        Random r = new Random();
        int getal1 = (r.nextInt(6) + 1);
        int getal2 = (r.nextInt(6) + 1);
        gui.letOpErIsGedobbelt(getal1, getal2);
        if ((getal1 + getal2) == 7) {
            kaartenAfgeven();
            struikroverVerzetten();
        }
        for (Speler s : spelers) {
            s.grondstofInnen((getal1 + getal2));
        }
    }

    public ArrayList<Color> struikroverVerzetten() {
        Tegel t = gui.setStruikrover();
        struikrover.setPlaats(t.getPlaats().getCenterPositie());
        Hexagon h = t.getPlaats();
        Point p = struikrover.getPlaats();
        Point2D[] hoeken = h.getVertices();
        ArrayList<Vesting> vestingen = new ArrayList<>();
        Iterator<Speler> spelers = this.getSpelers();
        ArrayList<Color> aanliggendeSpelers = new ArrayList<>();
        while (spelers.hasNext()) {
            Speler s = spelers.next();
            vestingen.addAll(s.getVestingen());
        }
        for (Point2D hoek : hoeken) {
            for (Vesting v : vestingen) {
                if (hoek.getX() > v.getPlaats().getX() - 2 && hoek.getX() < v.getPlaats().getX() + 2 && hoek.getY() > v.getPlaats().getY() - 2 && hoek.getY() < v.getPlaats().getX() + 2) {
                    if (!aanliggendeSpelers.contains(v.getKleur())) {
                        aanliggendeSpelers.add(v.getKleur());
                    }
                }
            }
        }

        return aanliggendeSpelers;
    }

    public void kaartenAfgeven() {
        for (Speler s : spelers) {
            if (s.moetGrondstoffenInleveren()) {
                gui.grondstoffenInleveren(s);
            }
        }
    }
    //</editor-fold>
}
