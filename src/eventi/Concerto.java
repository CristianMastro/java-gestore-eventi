package eventi;

import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
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
            System.out.println("Inserisci il prezzo del biglietto:");

            if (!scan.hasNextDouble()) {
                System.out.println("Errore: inserisci un numero valido.");
                scan.nextLine();
                continue;
            }

            double prezzo = scan.nextDouble();
            scan.nextLine();

            if (prezzo <= 0) {
                System.out.println("Errore: Il prezzo non può essere negativo. Riprova.");
            } else {
                this.prezzo = prezzo;
                System.out.println("Prezzo impostato: " + getPrezzo());
                break;
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

                scan.nextLine();
                LocalTime orarioInserito = LocalTime.of(ora, minuti);
                if (this.data.equals(LocalDate.now()) && orarioInserito.isBefore(LocalTime.now())) {
                    System.out.println("Errore: l'orario deve essere successivo a quello attuale. Riprova.");
                    continue; // Richiede un nuovo inserimento
                }

                this.oraEvento = orarioInserito;
                System.out.println("Orario impostato: " + getData() + " " + this.oraEvento);
                break; // Esce dal ciclo

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
