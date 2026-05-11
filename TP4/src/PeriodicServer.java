import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.*;

public class PeriodicServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1027);
        System.out.println("PeriodicServer actif");
        while (true) {
            Socket s = server.accept();
            new Thread(new PeriodicHandler(s)).start();
        }
    }
    
}

class PeriodicHandler implements Runnable {
    private Socket socket;
    PeriodicHandler(Socket s) { socket = s; }
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> out.println("Ping: " + new Date()), 0, 5, TimeUnit.SECONDS);
            String line;
            while ((line = in.readLine()) != null) {
                if ("stop".equalsIgnoreCase(line)) break;
            }
            scheduler.shutdown();
            socket.close();

        } catch (IOException e) {}
    }
}