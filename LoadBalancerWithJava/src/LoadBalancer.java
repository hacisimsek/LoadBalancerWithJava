import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadBalancer {
    private static final int LISTEN_PORT = 8080;
    private List<String> serverAddresses;
    private int currentServerIndex;
    private ExecutorService executorService;

    public LoadBalancer(List<String> serverAddresses) {
        this.serverAddresses = serverAddresses;
        this.currentServerIndex = 0;
        this.executorService = Executors.newFixedThreadPool(serverAddresses.size());
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(LISTEN_PORT)) {
            System.out.println("Load balancer is running on port " + LISTEN_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                String targetServerAddress = getNextServerAddress();
                executorService.submit(new RequestHandler(clientSocket, targetServerAddress));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized String getNextServerAddress() {
        String nextServer = serverAddresses.get(currentServerIndex);
        currentServerIndex = (currentServerIndex + 1) % serverAddresses.size();
        return nextServer;
    }


}

