// Même code que TCP_A mais l'adresse IP est passée en argument

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCP_A_Different {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: java TCP_A_Different <server_ip>");
            return;
        }
        Socket socket = new Socket(args[0], 1027);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String line;
        System.out.println("A (client) connecté. Saisissez du texte ('stop' pour finir) :");

        while ((line = userInput.readLine()) != null) {
            out.println(line);
            if ("stop".equalsIgnoreCase(line)) break;
        }
        socket.close();

    }
}