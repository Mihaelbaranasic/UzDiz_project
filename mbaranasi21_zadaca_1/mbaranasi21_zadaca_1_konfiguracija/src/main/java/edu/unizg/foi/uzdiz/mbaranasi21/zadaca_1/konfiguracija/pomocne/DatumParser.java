package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.pomocne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Pomoćna klasa za parsiranje datuma i vremena.
 */
public class DatumParser {
    
    private static final DateTimeFormatter FORMATTER_DATUM_SA_TOCKOM = 
        DateTimeFormatter.ofPattern("d.M.yyyy.");
    
    private static final DateTimeFormatter FORMATTER_DATUM_BEZ_TOCKE = 
        DateTimeFormatter.ofPattern("d.M.yyyy");
    
    private static final DateTimeFormatter FORMATTER_VRIJEME_SA_SEKUNDAMA = 
        DateTimeFormatter.ofPattern("H:mm:ss");
    
    private static final DateTimeFormatter FORMATTER_VRIJEME_BEZ_SEKUNDI = 
        DateTimeFormatter.ofPattern("H:mm");
    
    private static final DateTimeFormatter FORMATTER_DATUM_VRIJEME_SA_TOCKOM = 
        DateTimeFormatter.ofPattern("d.M.yyyy. H:mm:ss");
    
    private static final DateTimeFormatter FORMATTER_DATUM_VRIJEME_BEZ_TOCKE = 
        DateTimeFormatter.ofPattern("d.M.yyyy H:mm:ss");
    
    /**
     * Parsira datum (pokušava oba formata: sa i bez točke).
     *
     * @param tekst Tekstualni prikaz datuma
     * @return LocalDate ili null ako parsiranje ne uspije
     */
    public static LocalDate parsirajDatum(String tekst) {
        if (tekst == null || tekst.trim().isEmpty()) {
            return null;
        }
        
        String trimmed = tekst.trim();
        
        try {
            return LocalDate.parse(trimmed, FORMATTER_DATUM_SA_TOCKOM);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(trimmed, FORMATTER_DATUM_BEZ_TOCKE);
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }
    
    /**
     * Parsira vrijeme (pokušava oba formata: sa i bez sekundi).
     *
     * @param tekst Tekstualni prikaz vremena
     * @return LocalTime ili null ako parsiranje ne uspije
     */
    public static LocalTime parsirajVrijeme(String tekst) {
        if (tekst == null || tekst.trim().isEmpty()) {
            return null;
        }
        
        String trimmed = tekst.trim();
        
        try {
            return LocalTime.parse(trimmed, FORMATTER_VRIJEME_SA_SEKUNDAMA);
        } catch (DateTimeParseException e1) {
            try {
                return LocalTime.parse(trimmed, FORMATTER_VRIJEME_BEZ_SEKUNDI);
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }
    
    /**
     * Parsira datum i vrijeme (pokušava oba formata).
     *
     * @param tekst Tekstualni prikaz datuma i vremena
     * @return LocalDateTime ili null ako parsiranje ne uspije
     */
    public static LocalDateTime parsirajDatumVrijeme(String tekst) {
        if (tekst == null || tekst.trim().isEmpty()) {
            return null;
        }
        
        String trimmed = tekst.trim();
        
        try {
            return LocalDateTime.parse(trimmed, FORMATTER_DATUM_VRIJEME_SA_TOCKOM);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(trimmed, FORMATTER_DATUM_VRIJEME_BEZ_TOCKE);
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }
}