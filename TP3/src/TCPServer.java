import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer {
    private static List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1027);
        System.out.println("Serveur TCP démarré sur 1027");
        while (true) {
            Socket s = server.accept();
            System.out.println("Client connecté: " + s.getRemoteSocketAddress());
            new Thread(new ClientHandler(s)).start();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        ClientHandler(Socket s) { socket = s; }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clients) { clients.add(out); }
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Message reçu: " + line);
                    synchronized (clients) {
                        for (PrintWriter writer : clients) {
                            writer.println(line);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Client déconnecté");
            } finally {
                synchronized (clients) { clients.remove(out); }
                try { socket.close(); } catch (IOException e) {}
            }
        }
    }
}