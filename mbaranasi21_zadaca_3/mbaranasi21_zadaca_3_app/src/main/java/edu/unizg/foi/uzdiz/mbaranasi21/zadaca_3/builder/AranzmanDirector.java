package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.builder;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;

/**
 * Director klasa za Builder pattern.
 * Enkapsulira proces konstrukcije aranžmana.
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
     * Konstruira aranžman koristeći builder.
     * Director enkapsulira proces konstrukcije.
     * 
     * @return Izgrađeni aranžman
     */
    public Aranzman construct() {
        return builder.build();
    }
}