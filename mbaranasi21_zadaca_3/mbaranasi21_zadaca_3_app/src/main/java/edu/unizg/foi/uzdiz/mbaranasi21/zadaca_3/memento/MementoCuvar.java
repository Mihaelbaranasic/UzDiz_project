package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.memento;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Caretaker koji upravlja spremljenim Memento objektima.
 * Singleton implementacija.
 */
public class MementoCuvar {
    
    private static volatile MementoCuvar instanca;
    
    private Map<String, Stack<AranzmanMemento>> spremljenaStanja;
    
    private MementoCuvar() {
        this.spremljenaStanja = new HashMap<>();
    }
    
    public static MementoCuvar getInstance() {
        if (instanca == null) {
            synchronized (MementoCuvar.class) {
                if (instanca == null) {
                    instanca = new MementoCuvar();
                }
            }
        }
        return instanca;
    }
    
    public void spremiStanje(String oznakaAranzmana, AranzmanMemento memento) {
        if (!spremljenaStanja.containsKey(oznakaAranzmana)) {
            spremljenaStanja.put(oznakaAranzmana, new Stack<>());
        }
        spremljenaStanja.get(oznakaAranzmana).push(memento);
    }
    
    public AranzmanMemento vratiZadnjeStanje(String oznakaAranzmana) {
        if (!spremljenaStanja.containsKey(oznakaAranzmana)) {
            return null;
        }
        
        Stack<AranzmanMemento> stog = spremljenaStanja.get(oznakaAranzmana);
        
        if (stog.isEmpty()) {
            return null;
        }
        
        return stog.pop();
    }
    
    public int dohvatiBrojSpremljenihStanja(String oznakaAranzmana) {
        if (!spremljenaStanja.containsKey(oznakaAranzmana)) {
            return 0;
        }
        return spremljenaStanja.get(oznakaAranzmana).size();
    }
    
    public void obrisiSvaStanja() {
        spremljenaStanja.clear();
    }
    
    public void obrisiStanjaZaAranzman(String oznakaAranzmana) {
        spremljenaStanja.remove(oznakaAranzmana);
    }
}