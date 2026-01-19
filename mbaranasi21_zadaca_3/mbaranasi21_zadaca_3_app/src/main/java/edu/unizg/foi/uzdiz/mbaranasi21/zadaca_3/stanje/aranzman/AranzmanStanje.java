package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.aranzman;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

public interface AranzmanStanje {
    boolean uPripremi(TuristickaAgencija agencija, String oznaka);
    boolean aktiviraj(TuristickaAgencija agencija, String oznaka);
    boolean popuni(TuristickaAgencija agencija, String oznaka);
    boolean otkazi(TuristickaAgencija agencija, String oznaka);
    String getNazivStanja();
}