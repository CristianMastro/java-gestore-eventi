package eventi;

import java.util.Scanner;

/*
 *  Creare una classe Main di test:
    1   in cui si chiede all’utente di inserire un nuovo evento con tutti i parametri. OK
    2   Dopo che l’evento è stato istanziato, chiedere all’utente se e quante prenotazioni vuole fare e provare ad effettuarle, implementando opportuni controlli
    3   Stampare a video il numero di posti prenotati e quelli disponibili OK
    4   Chiedere all’utente se e quanti posti vuole disdire OK
    5   Provare ad effettuare le disdette, implementando opportuni controlli OK
    6   Stampare a video il numero di posti prenotati e quelli disponibili OK

 */

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        /*Creare una classe Main di test, in cui si chiede all’utente di inserire un nuovo evento con tutti i parametri. */

        Concerto concerto1 = new Concerto(scan);
        System.out.println(concerto1.toString());
        
        /*Dopo che l’evento è stato istanziato, chiedere all’utente se e quante prenotazioni vuole fare e provare ad effettuarle, implementando opportuni controlli */
        
        concerto1.prenota(scan);

        /*Stampare a video il numero di posti prenotati e quelli disponibili */

        System.out.println("Posti prenotati: " + concerto1.getPostiPrenotati() + ". Posti disponibili: " + concerto1.postiDisponibili());

        /*Chiedere all’utente se e quanti posti vuole disdire */        

        concerto1.disdici(scan);
        System.out.println(concerto1.toString());
        System.out.println("Posti evento: " + concerto1.getNumeroPosti());
        System.out.println("Posti prenotati: " + concerto1.getPostiPrenotati());
        System.out.println("Posti disponibili: " + concerto1.postiDisponibili());

        /*Provare ad effettuare le disdette */

        concerto1.disdici(scan);

        /*Stampare a video il numero di posti prenotati e quelli disponibili */

        System.out.println(concerto1.toString());
        System.out.println("Posti evento: " + concerto1.getNumeroPosti());
        System.out.println("Posti prenotati: " + concerto1.getPostiPrenotati());
        System.out.println("Posti disponibili: " + concerto1.postiDisponibili());

        /*leggiamo data e titolo evento */

        System.out.println(concerto1.getData());
        System.out.println(concerto1.getTitolo());

        /* cambiamo titolo all'evento */

        concerto1.setTitolo(scan);
        System.out.println(concerto1.toString());

        /*cambiamo data all'evento */

        concerto1.setData(scan);
        System.out.println(concerto1.toString());

        /*cambiamo e leggiamo orario e prezzo del concerto con i metodi della classe concerto */

        concerto1.setOrario(scan);
        concerto1.setPrezzo(scan);

        System.out.println(concerto1.getOrario());
        System.out.println(concerto1.getPrezzo());
        System.out.println(concerto1.toString());

        Concerto concerto2 = new Concerto(scan);
        Concerto concerto3 = new Concerto(scan);

        /*creiamo la lista eventi */

        ProgrammaEventi concerti = new  ProgrammaEventi(scan);

        /*aggiungiamo eventi al programma */

        concerti.aggiungiEvento(concerto1);
        concerti.aggiungiEvento(concerto2);
        concerti.aggiungiEvento(concerto3);

        /*controlliamo gli eventi presenti */

        System.out.println("Eventi presenti: " + concerti.eventiPresenti());
        
    }
}
