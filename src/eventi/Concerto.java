package eventi;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Scanner;

public class Concerto extends Evento {

    private double prezzo;
    private LocalTime oraEvento;
    
    public Concerto(Scanner scan) {
        super(scan);
        setOrario(scan);
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
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
                System.out.println("Orario del concerto impostato: " + getData() + " " + this.oraEvento);
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

    


    
}
