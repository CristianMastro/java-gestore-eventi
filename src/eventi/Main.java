package eventi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        
        Evento concerto1 = new Evento(scan);

        System.out.println(concerto1.getTitolo());
        scan.nextLine();

        concerto1.setTitolo(scan);

        System.out.println(concerto1.getTitolo());

    }
}
