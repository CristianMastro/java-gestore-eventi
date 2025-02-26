package eventi;

import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Scanner;

public class Concerto extends Evento {

    private double prezzo;
    private LocalTime oraEvento;
    
    public Concerto(Scanner scan) {
        super(scan);
        setOrario(scan);
        setPrezzo(scan);
    }

    public String getPrezzo() {
        DecimalFormat formato = new DecimalFormat("##,##0.00€");
        return formato.format(this.prezzo);
    }

    public void setPrezzo(Scanner scan) {
        while (true) {
            try {
                System.out.println("Inserisci il prezzo del biglietto :");
                double prezzo = scan.nextDouble();
                if (prezzo < 0) {
                    throw new IllegalArgumentException("Il prezzo non può essere negativo.");
                }
                scan.nextLine();
                this.prezzo = prezzo;
                System.out.println("Prezzo: " + getPrezzo());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage() + " Riprova.");
            } catch (Exception e) {
                System.out.println("Errore di input: inserisci un numero valido.");
                scan.nextLine();
            }
        }
    }

    public void setOrario(Scanner scan) {
        while (true) {
            try {
                System.out.println("Inserisci ora del concerto (0-23):");
                int ora = scan.nextInt();
                if (ora < 0 || ora > 23) {
                    throw new DateTimeException("Ora non valida. Inserisci un'ora compresa tra 0 e 23.");
                }

                System.out.println("Inserisci minuti del concerto (0-59):");
                int minuti = scan.nextInt();
                if (minuti < 0 || minuti > 59) {
                    throw new DateTimeException("Minuti non validi. Inserisci un valore tra 0 e 59.");
                }

                this.oraEvento = LocalTime.of(ora, minuti);
                System.out.println("Orario impostato: " + getData() + " " + this.oraEvento);
                break;

            } catch (DateTimeException e) {
                System.out.println("Errore: " + e.getMessage() + " Riprova.");
            } catch (Exception e) {
                System.out.println("Inserisci un valore numerico valido.");
                scan.nextLine();
            }
        }
    }

    public LocalTime getOrario() {
        return oraEvento;
    }

    @Override
    public String toString() {
        return getData() + " - Ore: " + getOrario() + " - " + getTitolo() + " - Prezzo biglietto: " + getPrezzo();
    }

}
