import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress addr = InetAddress.getByName("localhost");
        BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = user.readLine()) != null) {
            byte[] data = line.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, addr, 9876);
            socket.send(packet);
        }
        socket.close();
    }
}