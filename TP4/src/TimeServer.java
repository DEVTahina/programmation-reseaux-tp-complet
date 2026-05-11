import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1027);
        System.out.println("TimeServer prêt");
        while (true) {
            Socket client = server.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println(new Date().toString());
            client.close();
        }
    }
}