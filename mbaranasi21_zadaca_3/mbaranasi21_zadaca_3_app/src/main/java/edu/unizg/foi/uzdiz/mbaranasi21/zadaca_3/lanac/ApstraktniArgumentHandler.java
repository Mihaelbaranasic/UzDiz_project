package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac;

/**
 * Apstraktni ConcreteHandler koji implementira vezu na sljedbenika.
 * Prema profesorovim materijalima - Chain of Responsibility pattern.
 */
public abstract class ApstraktniArgumentHandler implements ArgumentHandler {
    
    protected ArgumentHandler sljedbenik;
    
    public void postaviSljedbenika(ArgumentHandler sljedbenik) {
        this.sljedbenik = sljedbenik;
    }
    
    @Override
    public void obradiArgument(String[] args, int index, KonfiguracijaAplikacije config) {
        if (sljedbenik != null) {
            sljedbenik.obradiArgument(args, index, config);
        }
    }
}