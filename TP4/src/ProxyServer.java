import java.io.*;
import java.net.*;

public class ProxyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket proxy = new ServerSocket(1026);
        System.out.println("Proxy (port 1026) - relais pour autres réseaux");
        while (true) {
            Socket client = proxy.accept();
            new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
                    String target = in.readLine(); // syntaxe: TARGET_IP
                    Socket remote = new Socket(target, 1027);
                    BufferedReader remoteIn = new BufferedReader(new InputStreamReader(remote.getInputStream()));
                    String response = remoteIn.readLine();
                    out.println(response);
                    remote.close();
                } catch (Exception e) {
                    System.out.println("ERR: cible injoignable");
                }
            }).start();
        }
    }
}