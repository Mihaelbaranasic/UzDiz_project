package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.promatrac;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Osoba;

/**
 * ConcreteObserver - osoba koja je pretplaćena na obavijesti.
 */
public class OsobaPromatrac implements TuristickaPromatrac {
    
    private Osoba osoba;
    private String oznakaAranzmana;
    
    public OsobaPromatrac(Osoba osoba, String oznakaAranzmana) {
        this.osoba = osoba;
        this.oznakaAranzmana = oznakaAranzmana;
    }
    
    @Override
    public void azuriraj(String poruka) {
        System.out.println("Obavijest >> " + osoba.getIme() + " " + osoba.getPrezime() 
            + " [Aranžman: " + oznakaAranzmana + "] - " + poruka);
    }
    
    public Osoba getOsoba() {
        return osoba;
    }
    
    public String getOznakaAranzmana() {
        return oznakaAranzmana;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        OsobaPromatrac that = (OsobaPromatrac) obj;
        
        return osoba.getIme().equals(that.osoba.getIme())
            && osoba.getPrezime().equals(that.osoba.getPrezime())
            && oznakaAranzmana.equals(that.oznakaAranzmana);
    }
    
    @Override
    public int hashCode() {
        int result = osoba.getIme().hashCode();
        result = 31 * result + osoba.getPrezime().hashCode();
        result = 31 * result + oznakaAranzmana.hashCode();
        return result;
    }
}