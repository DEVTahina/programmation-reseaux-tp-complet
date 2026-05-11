import java.net.*;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress local = InetAddress.getLocalHost();
        System.out.println("Adresse locale : " + local.getHostAddress());
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Nom d'hôte ('stop' pour finir) : ");
            String host = sc.nextLine();
            if ("stop".equalsIgnoreCase(host)) break;
            try {
                InetAddress[] addrs = InetAddress.getAllByName(host);
                for (InetAddress a : addrs) System.out.println(" -> " + a.getHostAddress());
            } catch (UnknownHostException e) {
                System.out.println("Inconnu");
            }
        }
        sc.close();
    }
}