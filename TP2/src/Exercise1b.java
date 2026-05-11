import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Exercise1b {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Fichier source : ");
        String in = sc.nextLine();
        System.out.print("Fichier destination : ");
        String out = sc.nextLine();
        sc.close();

        PrintStream ps = new PrintStream(out);
        Scanner reader = new Scanner(new java.io.File(in));
        while (reader.hasNextLine()) {
            ps.println(reader.nextLine());
        }
        reader.close();
        ps.close();
        System.out.println("Copie terminée.");
    }
}