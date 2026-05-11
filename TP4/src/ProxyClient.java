import java.io.*;
import java.net.*;

public class ProxyClient {
    public static void main(String[] args) throws IOException {
        Socket proxy = new Socket("localhost", 1026);
        PrintWriter out = new PrintWriter(proxy.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(proxy.getInputStream()));
        BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Adresse de la machine distante : ");
        String target = user.readLine();
        out.println(target);
        System.out.println("Réponse : " + in.readLine());
        proxy.close();
    }
}