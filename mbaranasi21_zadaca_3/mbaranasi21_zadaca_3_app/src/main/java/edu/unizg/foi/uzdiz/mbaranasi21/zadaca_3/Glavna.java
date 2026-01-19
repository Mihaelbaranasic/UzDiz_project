package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.builder.AranzmanBuilder;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.dto.AranzmanDTO;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.dto.RezervacijaDTO;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.facade.PodaciFacade;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.StanjeRezervacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaFactory;

/**
 * Glavna klasa aplikacije za upravljanje turističkom agencijom.
 * Entry point programa - parsira argumente i pokreće sustav.
 */
public class Glavna {

    public static void main(String[] args) {
        Map<String, String> argumenti = parsirajArgumente(args);

        if (!provjeriArgumente(argumenti)) {
            System.exit(1);
        }

        String datotekaAranzmana = argumenti.get("--ta");
        String datotekaRezervacija = argumenti.get("--rta");

        ucitajPodatke(datotekaAranzmana, datotekaRezervacija);
        pokreniInteraktivniMod();
    }

    private static Map<String, String> parsirajArgumente(String[] args) {
        Map<String, String> mapa = new HashMap<>();

        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].startsWith("--")) {
                mapa.put(args[i], args[i + 1]);
            }
        }

        return mapa;
    }

    private static boolean provjeriArgumente(Map<String, String> argumenti) {
        if (!argumenti.containsKey("--ta")) {
            System.err.println("GREŠKA: Nedostaje --ta (aranžmani)!");
            return false;
        }

        if (!argumenti.containsKey("--rta")) {
            System.err.println("GREŠKA: Nedostaje --rta (rezervacije)!");
            return false;
        }

        return true;
    }

    /**
     * Učitava podatke iz datoteka.
     */
    private static void ucitajPodatke(String datotekaAranzmana, String datotekaRezervacija) {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        PodaciFacade facade = new PodaciFacade();
        
        List<AranzmanDTO> aranzmaniDTO = facade.ucitajAranzmane(datotekaAranzmana);
        
        for (AranzmanDTO dto : aranzmaniDTO) {
            Aranzman aranzman = konvertirajUAranzman(dto);
            agencija.dodajAranzman(aranzman);
        }
        
        List<RezervacijaDTO> rezervacijeDTO = facade.ucitajRezervacije(datotekaRezervacija);
        
        for (RezervacijaDTO dto : rezervacijeDTO) {
            Rezervacija rezervacija = konvertirajURezervaciju(dto);
            agencija.dodajRezervaciju(rezervacija);
        }
    }

    private static Aranzman konvertirajUAranzman(AranzmanDTO dto) {
        AranzmanBuilder builder = new AranzmanBuilder(
            dto.getOznaka(),
            dto.getNaziv(),
            dto.getPocetniDatum(),
            dto.getZavrsniDatum(),
            dto.getCijena(),
            dto.getMinBrojPutnika(),
            dto.getMaksBrojPutnika()
        );

        return builder
            .program(dto.getProgram())
            .vrijemeKretanja(dto.getVrijemeKretanja())
            .vrijemePovratka(dto.getVrijemePovratka())
            .brojNocenja(dto.getBrojNocenja())
            .doplataZaJednokrevetnuSobu(dto.getDoplataZaJednokrevetnuSobu())
            .prijevoz(dto.getPrijevoz())
            .brojDorucka(dto.getBrojDorucka())
            .brojRuckova(dto.getBrojRuckova())
            .brojVecera(dto.getBrojVecera())
            .build();
    }

    /**
     * Konvertira RezervacijaDTO u Rezervacija domain objekt.
     * Početni status je NOVA.
     */
    private static Rezervacija konvertirajURezervaciju(RezervacijaDTO dto) {
        Osoba osoba = new Osoba(dto.getIme(), dto.getPrezime());
        return new Rezervacija(
            osoba,
            dto.getOznakaAranzmana(),
            dto.getDatumVrijemePrijema(),
            StanjeRezervacije.NOVA
        );
    }

    private static void pokreniInteraktivniMod() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n> ");
            String naredba = scanner.nextLine().trim();

            if (naredba.isEmpty()) {
                continue;
            }

            if (naredba.equalsIgnoreCase("Q")) {
                break;
            }

            obradiNaredbu(naredba);
        }

        scanner.close();
    }

    /**
     * Obrađuje unesenu naredbu koristeći Factory Method pattern.
     */
    private static void obradiNaredbu(String naredba) {
        String[] dijelovi = naredba.split("\\s+");
        String nazivKomande = dijelovi[0].toUpperCase();

        KomandaCreator creator = KomandaFactory.dohvatiCreator(nazivKomande);

        if (creator == null) {
            System.err.println("GREŠKA: Nepoznata naredba '" + nazivKomande + "'!");
            return;
        }

        creator.izvrsiKomandu(dijelovi);
    }
}