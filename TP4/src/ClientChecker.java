import java.io.*;
import java.net.*;

public class ClientChecker {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;
        String host = args[0];
        try (Socket s = new Socket(host, 1027);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            System.out.println("Réponse: " + in.readLine());
        } catch (ConnectException e) {
            System.out.println(host + " inactif");
        }
    }
}