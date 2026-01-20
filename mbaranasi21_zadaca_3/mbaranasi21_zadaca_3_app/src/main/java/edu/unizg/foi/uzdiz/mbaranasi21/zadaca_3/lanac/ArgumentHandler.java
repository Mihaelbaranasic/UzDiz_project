package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lanac;

/**
 * Handler interface za Chain of Responsibility.
 * Definira sučelje za rukovanje argumentima komandne linije.
 */
public interface ArgumentHandler {
    
    void obradiArgument(String[] args, int index, KonfiguracijaAplikacije config);
}