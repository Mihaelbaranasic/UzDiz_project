package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.pomocne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Pomoćna klasa za parsiranje i formatiranje datuma i vremena.
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
    
    
    /**
     * Formatira datum u hrvatski format sa točkom.
     *
     * @param datum Datum za formatiranje
     * @return Formatirani datum (npr. "10.11.2025.")
     */
    public static String formatirajDatum(LocalDate datum) {
        if (datum == null) {
            return "-";
        }
        return datum.format(FORMATTER_DATUM_SA_TOCKOM);
    }
    
    /**
     * Formatira vrijeme u hrvatski format.
     *
     * @param vrijeme Vrijeme za formatiranje
     * @return Formatirano vrijeme (npr. "15:30:00")
     */
    public static String formatirajVrijeme(LocalTime vrijeme) {
        if (vrijeme == null) {
            return "-";
        }
        return vrijeme.format(FORMATTER_VRIJEME_SA_SEKUNDAMA);
    }
    
    /**
     * Formatira datum i vrijeme u hrvatski format.
     *
     * @param datumVrijeme Datum i vrijeme za formatiranje
     * @return Formatirani datum i vrijeme (npr. "10.11.2025. 15:30:00")
     */
    public static String formatirajDatumVrijeme(LocalDateTime datumVrijeme) {
        if (datumVrijeme == null) {
            return "-";
        }
        return datumVrijeme.format(FORMATTER_DATUM_VRIJEME_SA_TOCKOM);
    }
}