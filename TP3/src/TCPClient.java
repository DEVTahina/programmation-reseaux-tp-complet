import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1027);
        BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) System.out.println("Serveur dit: " + msg);
            } catch (IOException e) {}
        }).start();

        String line;
        while ((line = user.readLine()) != null) {
            out.println(line);
        }
        socket.close();
    }
}