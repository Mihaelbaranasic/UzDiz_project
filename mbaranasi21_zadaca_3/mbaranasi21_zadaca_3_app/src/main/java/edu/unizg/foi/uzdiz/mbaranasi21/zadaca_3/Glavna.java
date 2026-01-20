package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3;

import java.util.List;
import java.util.Scanner;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.builder.AranzmanBuilder;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac.ApstraktniArgumentHandler;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac.JdrArgumentHandler;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac.KonfiguracijaAplikacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac.RtaArgumentHandler;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac.TaArgumentHandler;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac.VdrArgumentHandler;
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

public class Glavna {

    public static void main(String[] args) {
        KonfiguracijaAplikacije config = parsirajArgumente(args);

        if (!config.jeValjan()) {
            System.err.println("GREŠKA: Nedostaju obavezni argumenti!");
            System.err.println("Korištenje: --ta <datoteka> --rta <datoteka> [--jdr|--vdr]");
            System.exit(1);
        }

        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        agencija.postaviStrategiju(config.getStrategija());

        ucitajPodatke(config.getDatotekaAranzmani(), config.getDatotekaRezervacije());
        pokreniInteraktivniMod();
    }

    private static KonfiguracijaAplikacije parsirajArgumente(String[] args) {
        ApstraktniArgumentHandler taHandler = new TaArgumentHandler();
        ApstraktniArgumentHandler rtaHandler = new RtaArgumentHandler();
        ApstraktniArgumentHandler jdrHandler = new JdrArgumentHandler();
        ApstraktniArgumentHandler vdrHandler = new VdrArgumentHandler();

        taHandler.postaviSljedbenika(rtaHandler);
        rtaHandler.postaviSljedbenika(jdrHandler);
        jdrHandler.postaviSljedbenika(vdrHandler);

        KonfiguracijaAplikacije config = new KonfiguracijaAplikacije();

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                taHandler.obradiArgument(args, i, config);
            }
        }

        return config;
    }

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

    private static void obradiNaredbu(String naredba) {
        String[] dijelovi = naredba.split("\\s+");
        String nazivKomande = dijelovi[0].toUpperCase();

        KomandaCreator creator = KomandaFactory.dohvatiCreator(nazivKomande);

        if (creator == null) {
            System.err.println("GREŠKA: Nepoznata naredba '" + nazivKomande + "'!");
            return;
        }

        try {
            creator.izvrsiKomandu(dijelovi);
        } catch (IllegalArgumentException e) {
            System.err.println("GREŠKA: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("GREŠKA prilikom izvršavanja naredbe: " + e.getMessage());
        }
    }
}