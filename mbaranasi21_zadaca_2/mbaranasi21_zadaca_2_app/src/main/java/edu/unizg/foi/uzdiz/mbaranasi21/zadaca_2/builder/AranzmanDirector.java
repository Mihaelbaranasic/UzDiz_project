package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.builder;

import java.time.LocalTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Aranzman;

/**
 * Director klasa za Builder pattern.
 * Konstruira objekt koristeći sučelje Builder-a.
 * Definira različite načine konstrukcije aranžmana.
 */
public class AranzmanDirector {
    
    private Builder builder;
    
    /**
     * Konstruktor koji prima Builder.
     * 
     * @param builder Builder koji će se koristiti za konstrukciju
     */
    public AranzmanDirector(Builder builder) {
        this.builder = builder;
    }
    
    /**
     * Kreira kompletan aranžman sa svim detaljima.
     * Koristi se za višednevne putovanja s punim programom.
     * 
     * @return Kompletan aranžman
     */
    public Aranzman kreirajKompletiranAranzman() {
        return builder
            .program("Kompletan program s razgledavanjem")
            .brojNocenja(3)
            .doplataZaJednokrevetnuSobu(50.0)
            .prijevoz("Autobus")
            .brojDorucka(3)
            .brojRuckova(3)
            .brojVecera(3)
            .build();
    }
    
    /**
     * Kreira jednodnevni izlet bez noćenja.
     * Koristi se za kratke jednodnevne izlete.
     * 
     * @return Jednodnevni izlet
     */
    public Aranzman kreirajJednodnevniIzlet() {
        return builder
            .program("Jednodnevni izlet")
            .vrijemeKretanja(LocalTime.of(8, 0))
            .vrijemePovratka(LocalTime.of(20, 0))
            .brojNocenja(0)
            .prijevoz("Autobus")
            .brojRuckova(1)
            .build();
    }
    
    /**
     * Kreira vikend paket (2 noćenja).
     * Koristi se za vikend izlete s noćenjem.
     * 
     * @return Vikend paket
     */
    public Aranzman kreirajVikendPaket() {
        return builder
            .program("Vikend opuštanje")
            .brojNocenja(2)
            .doplataZaJednokrevetnuSobu(30.0)
            .brojDorucka(2)
            .brojVecera(2)
            .build();
    }
    
    /**
     * Kreira vlastiti aranžman (bez organiziranog prijevoza).
     * Koristi se kada putnici sami organiziraju prijevoz.
     * 
     * @return Aranžman bez prijevoza
     */
    public Aranzman kreirajVlastitiAranzman() {
        return builder
            .program("Vlastiti program")
            .brojNocenja(0)
            .build();
    }
    
    /**
     * Kreira luksuzni paket s dodatnim sadržajima.
     * Koristi se za premium aranžmane.
     * 
     * @return Luksuzni paket
     */
    public Aranzman kreirajLuksuzniPaket() {
        return builder
            .program("Luksuzni program s vodicima")
            .brojNocenja(5)
            .doplataZaJednokrevetnuSobu(100.0)
            .prijevoz("Avion;Autobus")
            .brojDorucka(5)
            .brojRuckova(5)
            .brojVecera(5)
            .build();
    }
}