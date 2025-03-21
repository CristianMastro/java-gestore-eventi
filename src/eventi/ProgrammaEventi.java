package eventi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgrammaEventi {

    private String titolo;
    private List <Evento> nomeEvento;

    public ProgrammaEventi(Scanner scan) {
        System.out.println("Inserisci il nome del programma");
        this.titolo = scan.nextLine().trim();
        if (titolo.length() <= 0) {
            throw new IllegalArgumentException("Il titolo deve contenere del testo");
        }
        this.nomeEvento = new ArrayList<>();
    }

    public void aggiungiEvento(Evento evento) {
        nomeEvento.add(evento);
        System.out.println("Evento aggiunto: " + evento);
    }


    public void svuotaLista(){
        nomeEvento.clear();
        System.out.println("Eventi eliminati");
    }


    public int eventiPresenti() {
        return nomeEvento.size();
    }
    
}
