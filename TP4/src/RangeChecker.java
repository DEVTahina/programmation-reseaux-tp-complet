public class RangeChecker {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) return;
        String base = args[0];
        int lastDot = base.lastIndexOf('.');
        String prefix = base.substring(0, lastDot+1);
        int start = Integer.parseInt(base.substring(lastDot+1));
        for (int i = start; i < start+25; i++) {
            String ip = prefix + i;
            try (java.net.Socket s = new java.net.Socket(ip, 1027);
                 java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(s.getInputStream()))) {
                System.out.println(ip + " active : " + in.readLine());
            } catch (Exception e) {
                System.out.println(ip + " inactive");
            }
        }
    }
}