import java.util.Scanner;

public class Exercise1a {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Saisissez des lignes ('stop' pour arrêter): ");
       while (true) {
              String line = scanner.nextLine();
              if ("stop".equalsIgnoreCase(line))break;
              System.out.println(line);
       }
       scanner.close();
    }
}
