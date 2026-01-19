package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.dekorator;

/**
 * Decorator abstract klasa za Decorator pattern.
 * Delegira pozive osnovnoj komponenti.
 */
public abstract class IzvjestajDekorator implements IzvjestajKomponenta {
    
    protected IzvjestajKomponenta komponenta;
    
    /**
     * Konstruktor.
     * 
     * @param komponenta Komponenta koju dekoriramo
     */
    public IzvjestajDekorator(IzvjestajKomponenta komponenta) {
        this.komponenta = komponenta;
    }
    
    @Override
    public void generiraj() {
        komponenta.generiraj();
    }
    
    @Override
    public String dohvatiSadrzaj() {
        return komponenta.dohvatiSadrzaj();
    }
}