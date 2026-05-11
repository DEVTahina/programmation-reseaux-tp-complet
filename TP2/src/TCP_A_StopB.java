import java.io.*;
import java.net.*;

public class TCP_A_StopB {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1027);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        System.out.println("A : saisissez ('stop' de la part de B pour terminer)");
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    if ("stop".equalsIgnoreCase(msg)) {
                        System.out.println("B a demandé l'arrêt.");
                        System.exit(0);
                    }
                }
            } catch (IOException e) {}
        }).start();
        while ((line = userInput.readLine()) != null) {
            out.println(line);
        }
    }
}