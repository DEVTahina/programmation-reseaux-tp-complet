import java.io.*;
import java.net.*;

public class PeriodicClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1027);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
        new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) System.out.println(line);
            } catch (IOException e) {}
        }).start();
        String input;
        while ((input = user.readLine()) != null) {
            out.println(input);
            if ("stop".equalsIgnoreCase(input)) break;
        }
        socket.close();
    }
}