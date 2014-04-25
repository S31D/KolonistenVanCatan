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
import domein.tegels.Tegel;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.Color;
import kolonistenvancatan.ILobby;
import kolonistenvancatan.KVCSpelGUI;

/**
 *
 * @author anne
 */
public class Spel implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //nodig bij spel constructor
    private final String naam;
    private final ArrayList<Speler> spelers;
    private ArrayList<Object[]> aantallenVanOntwikkelingskaarten;
    Random r;
    //Speelstukken
    private Speler langsteHandelsroute;
    private Speler grootsteRiddermacht;
    private final Struikrover struikrover;
    private final Bord bord;
    //GUI
    private Speler activeSpeler;
    private KVCSpelGUI gui;
    private Bord b;
    public ILobby lobby;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor(naam)">
    /**
     *
     * @param naam
     */
    public Spel(String naam) {
        this.naam = naam;
        this.spelers = new ArrayList<>();
        this.aantallenVanOntwikkelingskaarten = new ArrayList<Object[]>();
        Object[] monopoliekaarten = new Object[2];
        Monopoliekaart monopoliekaart = new Monopoliekaart();
        monopoliekaarten[0] = monopoliekaart;
        monopoliekaarten[1] = 2;
        Object[] overwinningsPuntenkaarten = new Object[2];
        Overwinningspuntkaart overwinningspuntkaart = new Overwinningspuntkaart();
        overwinningsPuntenkaarten[0] = overwinningspuntkaart;
        overwinningsPuntenkaarten[1] = 5;
        Object[] ridderkaarten = new Object[2];
        Ridderkaart ridderkaart = new Ridderkaart();
        ridderkaarten[0] = ridderkaart;
        ridderkaarten[1] = 14;
        Object[] stratenbouwkaarten = new Object[2];
        Stratenbouwkaart stratenbouwkaart = new Stratenbouwkaart();
        stratenbouwkaarten[0] = stratenbouwkaart;
        stratenbouwkaarten[1] = 2;
        Object[] uitvindingkaarten = new Object[2];
        Uitvindingkaart uitvindingkaart = new Uitvindingkaart();
        uitvindingkaarten[0] = uitvindingkaart;
        uitvindingkaarten[1] = 2;
        aantallenVanOntwikkelingskaarten.add(monopoliekaarten);
        aantallenVanOntwikkelingskaarten.add(overwinningsPuntenkaarten);
        aantallenVanOntwikkelingskaarten.add(ridderkaarten);
        aantallenVanOntwikkelingskaarten.add(stratenbouwkaarten);
        aantallenVanOntwikkelingskaarten.add(uitvindingkaarten);

        r = new Random();
        //TODO
        //this.Ontwikkelingskaarten = addOntwikkelingskaarten();
        this.struikrover = new Struikrover();
        this.bord = new Bord();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Operations">
    public String getNaam() {
        return this.naam;
    }

    public Iterator getSpelers() {
        return this.spelers.iterator();
    }

    public void setLobby(ILobby l) {
        this.lobby = l;
    }

    public boolean afstandsRegel(Coordinate plaats) {
        throw new UnsupportedOperationException();
    }

    public void dorpBouwen(Speler speler) {
        ArrayList<Coordinate> mogelijkePlaatsen = new ArrayList<Coordinate>();
        if (speler instanceof Bot) {
            if (speler.voorraadToereikend(Grondstof.HOUT, 1) && speler.voorraadToereikend(Grondstof.GRAAN, 1) && speler.voorraadToereikend(Grondstof.WOL, 1) && speler.voorraadToereikend(Grondstof.BAKSTEEN, 1)) {
                for (Coordinate p : controleerBeschikbaarheidVestiging(true)) {
                    mogelijkePlaatsen.add(p);
                }
                Coordinate dorpPlek = mogelijkePlaatsen.get(r.nextInt(mogelijkePlaatsen.size()));
                Vesting v = new Vesting(dorpPlek, speler.getKleur(), false);
                speler.setDorp(v);
                gui.setDorp(v);

                speler.setOverwinningspunten(speler.getOverwinningspunten() + 1);

                speler.setGrondstof(Grondstof.HOUT, -1);
                speler.setGrondstof(Grondstof.GRAAN, -1);
                speler.setGrondstof(Grondstof.BAKSTEEN, -1);
                speler.setGrondstof(Grondstof.WOL, -1);
            } else {
            }
        } else {
            if (speler.voorraadToereikend(Grondstof.HOUT, 1) && speler.voorraadToereikend(Grondstof.GRAAN, 1) && speler.voorraadToereikend(Grondstof.WOL, 1) && speler.voorraadToereikend(Grondstof.BAKSTEEN, 1)) {
                for (Coordinate p : controleerBeschikbaarheidVestiging(true)) {
                    mogelijkePlaatsen.add(p);
                }

                Coordinate dorpPlek = gui.keuzePlaatsDorp(mogelijkePlaatsen);
                Vesting v = new Vesting(dorpPlek, speler.getKleur(), false);
                speler.setDorp(v);
                gui.setDorp(v);

                speler.setOverwinningspunten(speler.getOverwinningspunten() + 1);
                speler.setGrondstof(Grondstof.HOUT, -1);
                speler.setGrondstof(Grondstof.GRAAN, -1);
                speler.setGrondstof(Grondstof.BAKSTEEN, -1);
                speler.setGrondstof(Grondstof.WOL, -1);
            } else {
            }

        }
    }

    public void creëerStad(Speler speler) {
        ArrayList<Coordinate> mogelijkeDorpen = new ArrayList<Coordinate>();
        if (speler instanceof Bot) {
            if (speler.voorraadToereikend(Grondstof.GRAAN, 2) && speler.voorraadToereikend(Grondstof.ERTS, 3)) {
                for (Coordinate p : controleerBeschikbaarheidVestiging(false)) {
                    mogelijkeDorpen.add(p);
                }
                Coordinate stadPlek = mogelijkeDorpen.get(r.nextInt(mogelijkeDorpen.size()));
                Vesting v = new Vesting(stadPlek, speler.getKleur(), false);
                speler.setStad(v);
                gui.setStad(v);

                speler.setOverwinningspunten(speler.getOverwinningspunten() + 1);
                speler.setGrondstof(Grondstof.HOUT, -2);
                speler.setGrondstof(Grondstof.GRAAN, -3);
            } else {
            }
        } else {
            if (speler.voorraadToereikend(Grondstof.GRAAN, 2) && speler.voorraadToereikend(Grondstof.ERTS, 3)) {
                for (Coordinate p : controleerBeschikbaarheidVestiging(false)) {
                    mogelijkeDorpen.add(p);
                }
                Coordinate stadPlek = gui.keuzePlaatsDorp(mogelijkeDorpen);
                Vesting v = new Vesting(stadPlek, speler.getKleur(), false);
                speler.setStad(v);
                gui.setStad(v);

                speler.setOverwinningspunten(speler.getOverwinningspunten() + 1);
                speler.setGrondstof(Grondstof.HOUT, -2);
                speler.setGrondstof(Grondstof.GRAAN, -3);
            } else {
            }
        }
    }

    public void straatBouwen(Speler speler) {
        if (speler instanceof Bot) {
            if (speler.voorraadToereikend(Grondstof.HOUT, 1) && speler.voorraadToereikend(Grondstof.BAKSTEEN, 1)) {
                ArrayList<Straat> straten = this.controleerBeschikbaarheidStraat();
                Straat s = straten.get(r.nextInt(straten.size()));
                speler.setStraat(s);
                gui.setStraat(s);
                speler.setGrondstof(Grondstof.HOUT, -1);
                speler.setGrondstof(Grondstof.BAKSTEEN, -1);
            }
        } else {
            if (speler.voorraadToereikend(Grondstof.HOUT, 1) && speler.voorraadToereikend(Grondstof.BAKSTEEN, 1)) {
                ArrayList<Straat> straten = this.controleerBeschikbaarheidStraat();
                Straat s = gui.getStraat(straten, speler.getKleur());
                speler.setStraat(s);
                gui.setStraat(s);
                speler.setGrondstof(Grondstof.HOUT, -1);
                speler.setGrondstof(Grondstof.BAKSTEEN, -1);
            }
        }
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

            speler.setGrondstof((Grondstof) grondstoffenWillen.get(0)[0], (int) grondstoffenWillen.get(1)[1]);
            speler.setGrondstof((Grondstof) grondstoffenGeven.get(0)[0], ((int) grondstoffenGeven.get(0)[0] * -1));

            sp.setGrondstof((Grondstof) grondstoffenWillen.get(0)[0], ((int) grondstoffenWillen.get(0)[1] * -1));
            sp.setGrondstof((Grondstof) grondstoffenGeven.get(0)[0], (int) grondstoffenGeven.get(0)[1]);


            return true;
        }
    }

    public void HandelenBank(Speler speler, ArrayList<Object[]> grondstoffenWillen, ArrayList<Object[]> grondstoffenGeven) {
        /*
         speler.setGrondstof(grondstofWillen[0], grondstofWillen[1]);
         speler.setGrondstof(grondstofGeven[0], (grondstofGeven[1] * -1);
         */
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

    public ArrayList<Kleur> struikroverVerzetten() {
        Tegel t = null;
        if (this.activeSpeler instanceof Bot) {
            ArrayList<Tegel> alleTegels = null;
            alleTegels.addAll(b.getTegels("Landtegel"));
            t = alleTegels.get(r.nextInt(alleTegels.size()));
        } else {
            t = gui.setStruikrover();
        }
        struikrover.setPlaats(t.getPlaats().getCenterPositie());
        Hexagon h = t.getPlaats();
        Coordinate p = struikrover.getPlaats();
        Coordinate[] hoeken = h.getVertices();
        ArrayList<Vesting> vestingen = new ArrayList<>();
        Iterator<Speler> spelers = this.getSpelers();
        ArrayList<Kleur> aanliggendeSpelers = new ArrayList<>();
        while (spelers.hasNext()) {
            Speler s = spelers.next();
            vestingen.addAll(s.getVestigingen());
        }
        for (Coordinate hoek : hoeken) {
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

    private Iterable<Coordinate> controleerBeschikbaarheidVestiging(boolean b) {
        ArrayList<Vesting> vestingen = activeSpeler.getVestigingen();
        ArrayList<Straat> straten = activeSpeler.getStraten();
        ArrayList<Coordinate> driesprongen = bord.getDriesprongen();

        ArrayList<Coordinate> vrijePlekkenDorp = new ArrayList<Coordinate>();
        ArrayList<Coordinate> vrijePlekkenStad = new ArrayList<Coordinate>();
        ArrayList<Coordinate> vrijePlekken = new ArrayList<Coordinate>();
        if (b) {
            //KRIJG ALLE VRIJE DRIESPRONGEN
            for (Vesting v : vestingen) {
                for (Coordinate p : driesprongen) {
                    if (v.getPlaats().equals(p.getX()) || v.getPlaats().equals(p.getY())) {
                    } else {
                        vrijePlekkenDorp.add(p);
                    }
                }
            }
            //KIJK OF JE OP EEN VAN DE VRIJE DRIESPRONGEN ZOU MOGEN BOUWEN
            for (Coordinate p2D : vrijePlekkenDorp) {
                if (this.afstandsRegel(p2D)) {
                } else {
                    vrijePlekkenDorp.remove(p2D);
                }
            }
            //KIJK OF JE AAN EEN EIGEN STRAAT KAN BOUWEN
            for (Coordinate point : vrijePlekkenDorp) {
                for (Straat s : this.activeSpeler.getStraten()) {
                    if (s.getPlaats()[0].equals(point) || s.getPlaats()[1].equals(point)) {
                    } else {
                        vrijePlekkenDorp.remove(point);
                    }
                }
            }
            vrijePlekken = vrijePlekkenDorp;
        } else {
            for (Vesting v : this.activeSpeler.getVestigingen()) {
                if (!v.getIsStad()) {
                    vrijePlekkenStad.add(v.getPlaats());
                }
            }
            vrijePlekken = vrijePlekkenStad;
        }
        return vrijePlekken;
    }

    public ArrayList<Straat> controleerBeschikbaarheidStraat() {
        ArrayList<Coordinate[]> paden = bord.getPaden();
        ArrayList<Straat> vrijePaden = new ArrayList<Straat>();
        for (Speler speler : this.spelers) {
            for (Coordinate[] p : paden) {
                for (Straat s : speler.getStraten()) {
                    if (p.equals(s.getPlaats()[0]) && p.equals(s.getPlaats()[1])) {
                    } else {
                        vrijePaden.add(s);
                    }
                }
                for (Straat str : vrijePaden) {
                    for (Straat straat : this.activeSpeler.getStraten()) {
                        if (str.equals(straat.getPlaats()[0]) || str.equals(straat.getPlaats()[1])) {
                        } else {
                            vrijePaden.remove(str);
                        }
                    }
                }
            }
        }
        return vrijePaden;
    }

    public void ontwikkelingskaartInzetten() {
        ArrayList<IOntwikkelingskaart> alleKaarten = null;
        if (activeSpeler instanceof Bot) {
            alleKaarten = activeSpeler.getOntwikkelingskaarten();
            IOntwikkelingskaart randomKaart = alleKaarten.get(r.nextInt(alleKaarten.size()));
            switch (randomKaart.getNaam()) {
                case "Monopoliekaart":
                    Monopoliekaart mo = new Monopoliekaart();
                    mo.actie();
                    activeSpeler.removeOntwikkelingskaart(mo);
                    break;
                case "Overwinningspuntkaart":
                    Overwinningspuntkaart ov = new Overwinningspuntkaart();
                    ov.actie();
                    activeSpeler.removeOntwikkelingskaart(ov);
                    break;
                case "Ridderkaart":
                    Ridderkaart ri = new Ridderkaart();
                    ri.actie();
                    activeSpeler.removeOntwikkelingskaart(ri);
                    break;
                case "Stratenbouwkaart":
                    Stratenbouwkaart st = new Stratenbouwkaart();
                    st.actie();
                    activeSpeler.removeOntwikkelingskaart(st);
                    break;
                case "Uitvindingkaart":
                    Uitvindingkaart ui = new Uitvindingkaart();
                    ui.actie();
                    activeSpeler.removeOntwikkelingskaart(ui);
                    break;
            }
        } else {
            IOntwikkelingskaart gekozenkaart = gui.getGekozenOntwikkelingskaartInzetten();
            switch (gekozenkaart.getNaam()) {
                case "Monopoliekaart":
                    Monopoliekaart mo = new Monopoliekaart();
                    mo.actie();
                    activeSpeler.removeOntwikkelingskaart(mo);
                    break;
                case "Overwinningspuntkaart":
                    Overwinningspuntkaart ov = new Overwinningspuntkaart();
                    ov.actie();
                    activeSpeler.removeOntwikkelingskaart(ov);
                    break;
                case "Ridderkaart":
                    Ridderkaart ri = new Ridderkaart();
                    ri.actie();
                    activeSpeler.removeOntwikkelingskaart(ri);
                    break;
                case "Stratenbouwkaart":
                    Stratenbouwkaart st = new Stratenbouwkaart();
                    st.actie();
                    activeSpeler.removeOntwikkelingskaart(st);
                    break;
                case "Uitvindingkaart":
                    Uitvindingkaart ui = new Uitvindingkaart();
                    ui.actie();
                    activeSpeler.removeOntwikkelingskaart(ui);
                    break;
            }
        }
    }

    public IOntwikkelingskaart ontwikkelingskaartKopen() {
        IOntwikkelingskaart gekozenkaart = null;
        if (activeSpeler instanceof Bot) {
            ArrayList<IOntwikkelingskaart> alleSoortenOntwikkelingskaarten = new ArrayList<>();
            Monopoliekaart mon = new Monopoliekaart();
            Overwinningspuntkaart ove = new Overwinningspuntkaart();
            Ridderkaart rid = new Ridderkaart();
            Stratenbouwkaart str = new Stratenbouwkaart();
            Uitvindingkaart uit = new Uitvindingkaart();

            alleSoortenOntwikkelingskaarten.add(mon);
            alleSoortenOntwikkelingskaarten.add(ove);
            alleSoortenOntwikkelingskaarten.add(rid);
            alleSoortenOntwikkelingskaarten.add(str);
            alleSoortenOntwikkelingskaarten.add(uit);

            gekozenkaart = alleSoortenOntwikkelingskaarten.get(r.nextInt(alleSoortenOntwikkelingskaarten.size()));

        } else {
            gekozenkaart = gui.getGekozenOntwikkelingskaartKopen();


        }
        return gekozenkaart;
    }

    private ArrayList<IOntwikkelingskaart> addOntwikkelingskaarten() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

    public Speler getActiveSpeler() {
        return this.activeSpeler;
    }

    public Bord getBord() {
        return this.bord;
    }
}
