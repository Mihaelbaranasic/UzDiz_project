package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model;

import java.time.LocalTime;

/**
 * Apstraktno sučelje za kreiranje dijelova aranžmana (Builder pattern).
 * Određuje operacije za postavljanje atributa i kreiranje finalnog produkta.
 */
public interface Builder {
    
    /**
     * Postavlja program aranžmana.
     *
     * @param program Program aranžmana
     * @return Builder instance za fluent interface
     */
    Builder program(String program);
    
    /**
     * Postavlja vrijeme kretanja.
     *
     * @param vrijeme Vrijeme kretanja
     * @return Builder instance za fluent interface
     */
    Builder vrijemeKretanja(LocalTime vrijeme);
    
    /**
     * Postavlja vrijeme povratka.
     *
     * @param vrijeme Vrijeme povratka
     * @return Builder instance za fluent interface
     */
    Builder vrijemePovratka(LocalTime vrijeme);
    
    /**
     * Postavlja broj noćenja.
     *
     * @param broj Broj noćenja
     * @return Builder instance za fluent interface
     */
    Builder brojNocenja(int broj);
    
    /**
     * Postavlja doplatu za jednokrevetnu sobu.
     *
     * @param doplata Doplata u eurima
     * @return Builder instance za fluent interface
     */
    Builder doplataZaJednokrevetnuSobu(Double doplata);
    
    /**
     * Postavlja vrstu prijevoza.
     *
     * @param prijevoz Prijevoz
     * @return Builder instance za fluent interface
     */
    Builder prijevoz(String prijevoz);
    
    /**
     * Postavlja broj doručaka.
     *
     * @param broj Broj doručaka
     * @return Builder instance za fluent interface
     */
    Builder brojDorucka(int broj);
    
    /**
     * Postavlja broj ručkova.
     *
     * @param broj Broj ručkova
     * @return Builder instance za fluent interface
     */
    Builder brojRuckova(int broj);
    
    /**
     * Postavlja broj večera.
     *
     * @param broj Broj večera
     * @return Builder instance za fluent interface
     */
    Builder brojVecera(int broj);
    
    /**
     * Gradi i vraća finalni Aranzman objekt.
     *
     * @return Kreirani Aranzman
     */
    Aranzman build();
}