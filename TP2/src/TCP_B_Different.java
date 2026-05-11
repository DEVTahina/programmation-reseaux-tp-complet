import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_B_Different {
    public static void main(String[] args) {
        try {
            // Création du serveur sur le port 1027, accessible depuis n'importe quelle interface
            ServerSocket serverSocket = new ServerSocket(1027);
            System.out.println("Serveur B en attente de connexion sur le port 1027...");

            // Accepte la connexion du client A (peut venir d'une machine différente)
            Socket socket = serverSocket.accept();
            System.out.println("Client A connecté depuis : " + socket.getInetAddress());

            // Lecture des données envoyées par A
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;

            // Reçoit et affiche les lignes jusqu'à recevoir "stop"
            while ((line = in.readLine()) != null) {
                System.out.println("Reçu de A : " + line);
                if ("stop".equalsIgnoreCase(line)) {
                    System.out.println("Fin demandée par A. Fermeture du serveur.");
                    break;
                }
            }

            // Fermeture des ressources
            in.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.err.println("Erreur dans le serveur B : " + e.getMessage());
        }
    }
}