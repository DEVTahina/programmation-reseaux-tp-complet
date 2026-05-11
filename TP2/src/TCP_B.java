import java.io.*;
import java.net.*;

public class TCP_B {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1027);
        System.out.println("B (serveur) en attente...");
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println("Reçu: " + line);
            if ("stop".equalsIgnoreCase(line)) break;
        }
        client.close();
        server.close();
    }
}