import java.io.*;
import java.net.*;

public class TCP_A {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1027);
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