package eventi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        
        Evento concerto1 = new Evento(scan);

        concerto1.prenota(scan);

        concerto1.postiDisponibili();
    }
}
