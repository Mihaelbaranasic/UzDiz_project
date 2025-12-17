package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.composite.AranzmanKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.composite.RezervacijaKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.StanjeRezervacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.aranzman.AranzmanStanje;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.aranzman.UPripremiAranzman;

/**
 * Singleton klasa koja upravlja cijelim sustavom turističke agencije.
 */
public class TuristickaAgencija {
    
    private static volatile TuristickaAgencija instanca;
    
    private Map<String, Aranzman> aranzmani;
    private Map<String, List<Rezervacija>> rezervacijePoAranzmanu;
    private Map<String, AranzmanStanje> stanjaAranzmana;
    private String kriterijSortiranja = "DATUM";
    
    /**
     * Privatni konstruktor.
     */
    private TuristickaAgencija() {
        this.aranzmani = new HashMap<>();
        this.rezervacijePoAranzmanu = new HashMap<>();
        this.stanjaAranzmana = new HashMap<>();
    }
    
    /**
     * Dohvaća jedinstvenu instancu (thread-safe).
     */
    public static TuristickaAgencija getInstance() {
        if (instanca == null) {
            synchronized (TuristickaAgencija.class) {
                if (instanca == null) {
                    instanca = new TuristickaAgencija();
                }
            }
        }
        return instanca;
    }
    
    
    /**
     * Dodaje aranžman u sustav.
     */
    public boolean dodajAranzman(Aranzman aranzman) {
        if (aranzmani.containsKey(aranzman.getOznaka())) {
            System.err.println("GREŠKA: Aranžman s oznakom '" 
                + aranzman.getOznaka() + "' već postoji!");
            return false;
        }
        
        aranzmani.put(aranzman.getOznaka(), aranzman);
        rezervacijePoAranzmanu.put(aranzman.getOznaka(), new ArrayList<>());
        
        AranzmanStanje pocetnoStanje = new UPripremiAranzman();
        stanjaAranzmana.put(aranzman.getOznaka(), pocetnoStanje);
        
        return true;
    }
    
    /**
     * Dohvaća aranžman prema oznaci.
     */
    public Aranzman dohvatiAranzman(String oznaka) {
        return aranzmani.get(oznaka);
    }
    
    /**
     * Dohvaća sve aranžmane sortirane po kriteriju.
     */
    public List<Aranzman> dohvatiSveAranzmane() {
        List<Aranzman> lista = new ArrayList<>(aranzmani.values());
        sortirajAranzmane(lista);
        return lista;
    }
    
    /**
     * Dohvaća sve oznake aranžmana.
     */
    public List<String> dohvatiSveOznakeAranzmana() {
        return new ArrayList<>(aranzmani.keySet());
    }
    
    /**
     * Dohvaća stanje aranžmana.
     */
    public AranzmanStanje dohvatiStanjeAranzmana(String oznaka) {
        return stanjaAranzmana.get(oznaka);
    }
    
    /**
     * Postavlja stanje aranžmana.
     */
    public void postaviStanjeAranzmana(String oznaka, AranzmanStanje novoStanje) {
        stanjaAranzmana.put(oznaka, novoStanje);
    }
    
    
    /**
     * Dodaje rezervaciju u sustav.
     */
    public void dodajRezervaciju(Rezervacija rezervacija) {
        String oznaka = rezervacija.getOznakaAranzmana();
        
        if (!aranzmani.containsKey(oznaka)) {
            ispisiGreskuRezervacije(rezervacija, 
                "aranžman " + oznaka + " ne postoji");
            return;
        }
        
        AranzmanStanje stanje = dohvatiStanjeAranzmana(oznaka);
        if (stanje != null && stanje.getNazivStanja().equals("OTKAZAN")) {
            ispisiGreskuRezervacije(rezervacija, 
                "aranžman " + oznaka + " je otkazan");
            return;
        }
        
        if (!rezervacijePoAranzmanu.containsKey(oznaka)) {
            rezervacijePoAranzmanu.put(oznaka, new ArrayList<>());
        }
        
        rezervacijePoAranzmanu.get(oznaka).add(rezervacija);
        azurirajStanjaRezervacija(oznaka);
        azurirajStanjeAranzmana(oznaka);
    }
    
    /**
     * Kreira i dodaje novu rezervaciju (za DRTA komandu).
     */
    public boolean dodajNovuRezervaciju(Osoba osoba, String oznakaAranzmana, 
            LocalDateTime datumVrijeme) {
        if (!aranzmani.containsKey(oznakaAranzmana)) {
            System.err.println("GREŠKA: Aranžman " + oznakaAranzmana 
                + " ne postoji.");
            return false;
        }
        
        AranzmanStanje stanje = dohvatiStanjeAranzmana(oznakaAranzmana);
        if (stanje != null && stanje.getNazivStanja().equals("OTKAZAN")) {
            System.err.println("GREŠKA: Aranžman " + oznakaAranzmana 
                + " je otkazan. Ne mogu se dodavati rezervacije.");
            return false;
        }
        
        Rezervacija rezervacija = new Rezervacija(
            osoba, oznakaAranzmana, datumVrijeme, StanjeRezervacije.NOVA);
        
        dodajRezervaciju(rezervacija);
        return true;
    }
    
    /**
     * Dohvaća sve rezervacije za aranžman.
     */
    public List<Rezervacija> dohvatiRezervacije(String oznakaAranzmana) {
        return rezervacijePoAranzmanu.getOrDefault(
            oznakaAranzmana, new ArrayList<>());
    }
    
    /**
     * Dohvaća sve rezervacije osobe.
     */
    public List<Rezervacija> dohvatiRezervacijeOsobe(String ime, String prezime) {
        List<Rezervacija> rezultat = new ArrayList<>();
        
        for (List<Rezervacija> listaRezervacija : rezervacijePoAranzmanu.values()) {
            for (Rezervacija r : listaRezervacija) {
                if (r.getOsoba().getIme().equals(ime) 
                    && r.getOsoba().getPrezime().equals(prezime)) {
                    rezultat.add(r);
                }
            }
        }
        
        return rezultat;
    }
    
    /**
     * Otkazuje rezervaciju korisnika.
     */
    public boolean otkaziRezervaciju(String ime, String prezime, 
            String oznakaAranzmana, LocalDateTime datumVrijeme) {
        List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);
        Rezervacija najstarija = pronadjiNajstarijuRezervaciju(
            rezervacije, ime, prezime);
        
        if (najstarija == null) {
            ispisiGreskuOtkazivanja(ime, prezime, oznakaAranzmana);
            return false;
        }
        
        najstarija.otkazi(datumVrijeme);
        
        azurirajStanjaRezervacija(oznakaAranzmana);
        azurirajStanjeAranzmana(oznakaAranzmana);
        
        return true;
    }
    
    
    /**
     * Ažurira stanja rezervacija za aranžman.
     */
    private void azurirajStanjaRezervacija(String oznakaAranzmana) {
        Aranzman aranzman = dohvatiAranzman(oznakaAranzmana);
        if (aranzman == null) {
            return;
        }
        
        List<Rezervacija> sve = dohvatiRezervacije(oznakaAranzmana);
        List<Rezervacija> aktivne = filtrirajNeotkazane(sve);
        
        sortirajRezervacijePoVremenu(aktivne);
        postaviSveNaPrimljene(aktivne);
        
        if (aktivne.size() < aranzman.getMinBrojPutnika()) {
            return;
        }
        
        odrediKonacneStatuse(aktivne, aranzman);
    }
    
    /**
     * Ažurira stanje aranžmana prema broju aktivnih rezervacija.
     */
    private void azurirajStanjeAranzmana(String oznakaAranzmana) {
        Aranzman aranzman = dohvatiAranzman(oznakaAranzmana);
        if (aranzman == null) {
            return;
        }
        
        AranzmanStanje trenutnoStanje = stanjaAranzmana.get(oznakaAranzmana);
        if (trenutnoStanje == null) {
            trenutnoStanje = new UPripremiAranzman();
            stanjaAranzmana.put(oznakaAranzmana, trenutnoStanje);
        }
        
        long brojAktivnih = prebrojAktivneRezervacije(oznakaAranzmana);
        
        if (brojAktivnih < aranzman.getMinBrojPutnika()) {
            trenutnoStanje.uPripremi(this, oznakaAranzmana);
        } else if (brojAktivnih <= aranzman.getMaksBrojPutnika()) {
            trenutnoStanje.aktiviraj(this, oznakaAranzmana);
        } else {
            trenutnoStanje.popuni(this, oznakaAranzmana);
        }
    }
    
    
    /**
     * Filtrira neotkazane rezervacije.
     */
    private List<Rezervacija> filtrirajNeotkazane(List<Rezervacija> rezervacije) {
        List<Rezervacija> rezultat = new ArrayList<>();
        for (Rezervacija r : rezervacije) {
            if (!r.jeOtkazana()) {
                rezultat.add(r);
            }
        }
        return rezultat;
    }
    
    /**
     * Sortira rezervacije po vremenu prijema.
     */
    private void sortirajRezervacijePoVremenu(List<Rezervacija> rezervacije) {
        for (int i = 0; i < rezervacije.size() - 1; i++) {
            for (int j = 0; j < rezervacije.size() - i - 1; j++) {
                if (rezervacije.get(j).getDatumVrijemePrijema()
                        .isAfter(rezervacije.get(j + 1).getDatumVrijemePrijema())) {
                    zamijeniRezervacije(rezervacije, j, j + 1);
                }
            }
        }
    }
    
    /**
     * Zamjenjuje dvije rezervacije u listi.
     */
    private void zamijeniRezervacije(List<Rezervacija> lista, int i, int j) {
        Rezervacija temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }
    
    /**
     * Postavlja sve NOVE rezervacije na PRIMLJENE.
     */
    private void postaviSveNaPrimljene(List<Rezervacija> rezervacije) {
        for (Rezervacija r : rezervacije) {
            if (r.jeNova()) {
                r.primljena();
            }
        }
    }
    
    /**
     * Sortira aranžmane prema kriteriju.
     */
    private void sortirajAranzmane(List<Aranzman> lista) {
        if (kriterijSortiranja.equals("ABECEDA")) {
            sortirajPoAbecedi(lista);
        } else if (kriterijSortiranja.equals("DATUM")) {
            sortirajPoDatumu(lista);
        } else if (kriterijSortiranja.equals("CIJENA")) {
            sortirajPoCijeni(lista);
        }
    }
    
    /**
     * Sortira aranžmane po nazivu.
     */
    private void sortirajPoAbecedi(List<Aranzman> lista) {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getNaziv()
                        .compareTo(lista.get(j + 1).getNaziv()) > 0) {
                    zamijeniAranzmane(lista, j, j + 1);
                }
            }
        }
    }
    
    /**
     * Sortira aranžmane po datumu.
     */
    private void sortirajPoDatumu(List<Aranzman> lista) {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getPocetniDatum()
                        .isAfter(lista.get(j + 1).getPocetniDatum())) {
                    zamijeniAranzmane(lista, j, j + 1);
                }
            }
        }
    }
    
    /**
     * Sortira aranžmane po cijeni.
     */
    private void sortirajPoCijeni(List<Aranzman> lista) {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getCijena() > lista.get(j + 1).getCijena()) {
                    zamijeniAranzmane(lista, j, j + 1);
                }
            }
        }
    }
    
    /**
     * Zamjenjuje dva aranžmana u listi.
     */
    private void zamijeniAranzmane(List<Aranzman> lista, int i, int j) {
        Aranzman temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }
    
    
    /**
     * Određuje konačne statuse rezervacija.
     */
    /**
     * Određuje konačne statuse rezervacija.
     */
    private void odrediKonacneStatuse(List<Rezervacija> rezervacije, 
            Aranzman aranzman) {
        int maxPutnika = aranzman.getMaksBrojPutnika();
        int brojAktivnih = 0;
        Map<String, Boolean> osobaImaAktivu = new HashMap<>();
        
        for (Rezervacija rez : rezervacije) {
            String kljucOsobe = kreirajKljucOsobe(rez.getOsoba());
            
            if (osobaVecImaAktivu(osobaImaAktivu, kljucOsobe)) {
                rez.odgodi();
                ispisiGreskuRezervacije(rez, 
                    "korisnik već ima aktivnu rezervaciju");
                continue;
            }
            
            if (imaPreklop(rez.getOsoba(), aranzman)) {
                rez.odgodi();
                ispisiGreskuRezervacije(rez, 
                    "korisnik ima aktivnu rezervaciju na aranžmanu koji se preklapa");
                continue;
            }
            
            if (brojAktivnih < maxPutnika) {
                rez.aktiviraj();
                osobaImaAktivu.put(kljucOsobe, true);
                brojAktivnih++;
            } else {
                rez.staviNaCekanje();
            }
        }
    }
    
    /**
     * Kreira jedinstveni ključ za osobu.
     */
    private String kreirajKljucOsobe(Osoba osoba) {
        return osoba.getIme() + "|" + osoba.getPrezime();
    }
    
    /**
     * Provjerava ima li osoba već aktivnu rezervaciju.
     */
    private boolean osobaVecImaAktivu(Map<String, Boolean> mapa, String kljuc) {
        return mapa.containsKey(kljuc) && mapa.get(kljuc);
    }
    
    /**
     * Provjerava preklapanje s drugim aranžmanima.
     */
    private boolean imaPreklop(Osoba osoba, Aranzman trenutniAranzman) {
        for (String oznaka : rezervacijePoAranzmanu.keySet()) {
            if (oznaka.equals(trenutniAranzman.getOznaka())) {
                continue;
            }
            
            Aranzman drugiAranzman = dohvatiAranzman(oznaka);
            if (drugiAranzman == null) {
                continue;
            }
            
            if (!trenutniAranzman.preklapa(drugiAranzman)) {
                continue;
            }
            
            if (imaAktivnuRezervaciju(osoba, oznaka)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Provjerava ima li osoba aktivnu rezervaciju na aranžmanu.
     */
    private boolean imaAktivnuRezervaciju(Osoba osoba, String oznakaAranzmana) {
        List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);
        
        for (Rezervacija r : rezervacije) {
            if (r.getOsoba().getIme().equals(osoba.getIme()) 
                && r.getOsoba().getPrezime().equals(osoba.getPrezime())
                && r.jeAktivna()) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Pronalazi najstariju neotkazanu rezervaciju osobe.
     */
    private Rezervacija pronadjiNajstarijuRezervaciju(
            List<Rezervacija> rezervacije, String ime, String prezime) {
        Rezervacija najstarija = null;
        
        for (Rezervacija r : rezervacije) {
            if (r.getOsoba().getIme().equals(ime) 
                && r.getOsoba().getPrezime().equals(prezime) 
                && !r.jeOtkazana()) {
                
                if (najstarija == null || r.getDatumVrijemePrijema()
                        .isBefore(najstarija.getDatumVrijemePrijema())) {
                    najstarija = r;
                }
            }
        }
        
        return najstarija;
    }
    
    /**
     * Prebraja aktivne rezervacije za aranžman.
     */
    private long prebrojAktivneRezervacije(String oznakaAranzmana) {
        List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);
        long brojac = 0;
        
        for (Rezervacija r : rezervacije) {
            if (r.jeAktivna()) {
                brojac++;
            }
        }
        
        return brojac;
    }
    
    
    /**
     * Kreira Composite strukturu za aranžman.
     */
    public AranzmanKomponenta kreirajCompositeStrukturuAranzmana(
            String oznakaAranzmana) {
        Aranzman aranzman = dohvatiAranzman(oznakaAranzmana);
        if (aranzman == null) {
            return null;
        }
        
        AranzmanKomponenta aranzmanKomp = new AranzmanKomponenta(aranzman);
        
        List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);
        for (Rezervacija rez : rezervacije) {
            RezervacijaKomponenta rezKomp = new RezervacijaKomponenta(rez);
            aranzmanKomp.dodaj(rezKomp);
        }
        
        return aranzmanKomp;
    }
    
    
    /**
     * Postavlja kriterij sortiranja.
     */
    public void postaviKriterijSortiranja(String kriterij) {
        this.kriterijSortiranja = kriterij;
    }
    
    /**
     * Dohvaća trenutni kriterij sortiranja.
     */
    public String dohvatiKriterijSortiranja() {
        return kriterijSortiranja;
    }
    
    
    /**
     * Resetira sve podatke.
     */
    public void resetiraj() {
        aranzmani.clear();
        rezervacijePoAranzmanu.clear();
        stanjaAranzmana.clear();
    }
    
    
    /**
     * Ispisuje grešku za neispravnu rezervaciju.
     */
    private void ispisiGreskuRezervacije(Rezervacija rez, String razlog) {
        System.err.println("GREŠKA: Rezervacija korisnika " 
            + rez.getOsoba().getIme() + " " + rez.getOsoba().getPrezime() 
            + " za aranžman " + rez.getOznakaAranzmana() + " ("
            + DatumParser.formatirajDatumVrijeme(rez.getDatumVrijemePrijema()) 
            + ") je neispravna - " + razlog + ".");
    }
    
    /**
     * Ispisuje grešku za neuspješno otkazivanje.
     */
    private void ispisiGreskuOtkazivanja(String ime, String prezime, 
            String oznakaAranzmana) {
        System.err.println("GREŠKA: Rezervacija korisnika " + ime + " " 
            + prezime + " za aranžman " + oznakaAranzmana 
            + " ne postoji ili je već otkazana.");
    }
}