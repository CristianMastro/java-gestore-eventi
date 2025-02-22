package eventi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        
        Evento concerto1 = new Evento(scan);

        System.out.println(concerto1.getTitolo());
        scan.nextLine();

        System.out.println(concerto1.getTitolo());
        System.out.println(concerto1.getPostiTotale() + " " + concerto1.getPostiPrenotati());
        System.out.println(concerto1.getData());

        concerto1.setData(scan);

        concerto1.getData();

    }
}
