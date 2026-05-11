import java.io.*;
import java.net.*;

public class TCP_B_StopB {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1027);
        System.out.println("B en attente...");
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader localInput = new BufferedReader(new InputStreamReader(System.in));
        // Thread pour lire le clavier de B
        Thread t = new Thread(() -> {
            try {
                String line;
                while ((line = localInput.readLine()) != null) {
                    out.println(line);
                    if ("stop".equalsIgnoreCase(line)) {
                        System.exit(0);
                    }
                }
            } catch (IOException e) {}
        });
        t.start();
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println("Reçu de A: " + line);
        }
    }
}