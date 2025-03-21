package eventi;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/*

Creare una classe Evento che abbia le seguenti proprietà:
    titolo -------OK
    data ---------OK
    numero di posti in totale -------OK
    numero di posti prenotati -------OK

    Quando si istanzia un nuovo evento questi attributi devono essere tutti valorizzati nel costruttore, tranne posti prenotati che va inizializzato a 0. OK

    Inserire il controllo che la data non sia già passata e che il numero di posti totali sia positivo. In caso contrario il programma deve lanciare una eccezione. OKU
    Aggiungere metodi getter e setter in modo che:
    titolo sia in lettura e in scrittura OK
    data sia in lettura e scrittura OK
    numero di posti totale sia solo in lettura OK
    numero di posti prenotati sia solo in lettura OK

Vanno inoltre implementati dei metodi public che svolgono le seguenti funzioni:
    prenota: aggiunge uno ai posti prenotati. Se l’evento è già passato o non ha posti disponibili deve restituire un’eccezione. OK
    disdici: riduce di uno i posti prenotati. Se l’evento è già passato o non ci sono prenotazioni restituisce un’eccezione.
    l’override del metodo toString() in modo che venga restituita una stringa contenente: data formattata - titolo OK

Aggiungete eventuali metodi (public e private) che vi aiutino a svolgere le funzioni richieste.

 */

public class Evento {

    private String titolo;
    protected LocalDate data;
    private int numeroPosti;
    private int[] postiTotale;
    private int postiPrenotati = 0;

    Scanner scan = new Scanner(System.in);

    private void setPosti(Scanner scan) {
        while (true) {
            try {
                System.out.println("Quanti posti sono disponibili?");
                int postiEvento = scan.nextInt();
                scan.nextLine();

                if (postiEvento > 0) {
                    this.numeroPosti = postiEvento;
                    break;
                } else if (postiEvento <= 0) {
                    throw new IllegalArgumentException("E' possibile assegnare solo numeri positivi.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Errore: devi inserire un numero intero.");
                scan.nextLine();
            }
        }
    }

    public void setData(Scanner scan) {
        while (true) {
            try {
                System.out.println("Inserisci anno evento:");
                int anno = scan.nextInt();
                int annoAttuale = LocalDate.now().getYear();

                if (anno < annoAttuale || anno > 2099) {
                    throw new DateTimeException("Anno non valido. Inserisci anno attuale o successivo fino al 2099");
                }

                System.out.println("Inserisci mese evento (1-12):");
                int mese = scan.nextInt();
                if (mese < 1 || mese > 12 ||
                        (anno == LocalDate.now().getYear() && mese < LocalDate.now().getMonthValue())) {
                    throw new DateTimeException("Mese non valido. Inserisci mese attuale o successivo (1-12).");
                }

                int maxGiorni = Month.of(mese).length(Year.isLeap(anno));

                System.out.println("Inserisci giorno evento (1-" + maxGiorni + "):");
                int giorno = scan.nextInt();
                if (giorno < 1 || giorno > maxGiorni) {
                    throw new DateTimeException("Giorno non valido. Inserisci giorno corretto per il mese scelto.");
                }

                LocalDate dataInserita = LocalDate.of(anno, mese, giorno);
                if (dataInserita.isBefore(LocalDate.now())) {
                    throw new DateTimeException("Errore: la data deve essere futura.");
                }
                
                scan.nextLine();
                this.data = dataInserita;
                System.out.println("Data evento impostata: " + getData());
                break;

            } catch (DateTimeException e) {
                System.out.println("Errore: " + e.getMessage() + " Riprova.");
            } catch (Exception e) {
                System.out.println("Errore di input: inserisci un valore numerico valido.");
                scan.nextLine();
            }
        }
    }

    public Evento(Scanner scan) {
        System.out.println("Inserisci il nome dell'evento");
        this.titolo = scan.nextLine().trim();
        if (titolo.length() <= 0) {
            throw new IllegalArgumentException("Il titolo deve contenere del testo");
        }
        setData(scan);
        setPosti(scan);
        this.postiTotale = new int[numeroPosti];
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(Scanner scan) {
        System.out.println("Inserisci il nuovo titolo");
        this.titolo = scan.nextLine().trim();
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    public String getData() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        return this.data.format(formato);
    }

    @Override
    public String toString() {
        return getData() + " - " + titolo;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public void prenota(Scanner scan) {
        System.out.print("Vuoi prenotare dei posti? (y/n): ");
        String scelta = scan.nextLine().trim().toLowerCase();

        while (!scelta.equals("y") && !scelta.equals("n")) {
            System.out.print("Input non valido. Inserisci 'y' per sì o 'n' per no: ");
            scelta = scan.nextLine().trim().toLowerCase();
        }

        if (scelta.equals("n")) {
            System.out.println("Prenotazione annullata.");
            return;
        }

        int numPostiDaAggiungere = 0;

            while (true) {
                try {
                    System.out.println("Quanti posti vuoi aggiungere?");
                    numPostiDaAggiungere = scan.nextInt();
                    scan.nextLine();

                    if (numPostiDaAggiungere >= 0) {
                        break;
                    } else {
                        System.out.println("Errore: inserisci un numero positivo.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Errore: devi inserire un numero intero valido.");
                    scan.nextLine();
                }
            }

            if (postiPrenotati + numPostiDaAggiungere > numeroPosti) {
                System.out.println("Non ci sono abbastanza posti. Posti disponibili: " + postiDisponibili() + ". Riprova.");
                return;
            }

            for (int i = 0; i < numPostiDaAggiungere; i++) {
                int nuovoPosto = postiPrenotati + 1;
                postiTotale[postiPrenotati] = nuovoPosto;
                postiPrenotati++;
            }

            System.out.println("Posti aggiunti: " + numPostiDaAggiungere + ". Posti rimanenti: " + postiDisponibili()
                    + ". Posti prenotati: " + postiPrenotati);
    }

    public void disdici(Scanner scan) {
        System.out.print("Vuoi disdire dei posti? (y/n): ");
        String scelta = scan.nextLine().trim().toLowerCase();

        while (!scelta.equals("y") && !scelta.equals("n")) {
            System.out.print("Input non valido. Inserisci 'y' per sì o 'n' per no: ");
            scelta = scan.nextLine().trim().toLowerCase();
        }

        if (scelta.equals("n")) {
            System.out.println("Nessun posto eliminato.");
            return;
        }

        int numPostiDaDisdire = 0;
        while (true) {
            try {
                System.out.println("Quanti posti vuoi disdire?");
                numPostiDaDisdire = scan.nextInt();
                scan.nextLine();

                if (numPostiDaDisdire >= 0) {
                    break;
                } else {
                    System.out.println("Inserisci un numero positivo");
                }
            } catch (InputMismatchException e) {
                System.out.println("Errore: devi inserire un numero intero valido.");
                scan.nextLine();
            }
        }

        if (numPostiDaDisdire > postiPrenotati) {
            System.out.println(
                    "Non è possibile disdire i posti selezionati, i posti prenotati sono inferiori: " + postiPrenotati);
            return;
        }

        for (int i = 0; i < numPostiDaDisdire; i++) {
            postiPrenotati--;
            postiTotale[postiPrenotati] = 0;
        }
        System.out.println("Posti eliminati: " + numPostiDaDisdire + ". Posti rimanenti: " + postiDisponibili()
                + ". Posti prenotati: " + postiPrenotati);
    }

    public int postiDisponibili() {
        int postiDisponibili = numeroPosti - postiPrenotati;
        return postiDisponibili;
    }
}
