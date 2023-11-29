import java.io.IOException;
import java.net.Socket;

class RequestHandler implements Runnable {
    private Socket clientSocket;
    private String targetServerAddress;

    public RequestHandler(Socket clientSocket, String targetServerAddress) {
        this.clientSocket = clientSocket;
        this.targetServerAddress = targetServerAddress;
    }

    @Override
    public void run() {
        try {
            // Forward the request to the target server
            String[] parts = targetServerAddress.split(":");
            String targetServerHost = parts[0];
            int targetServerPort = Integer.parseInt(parts[1]);

            Socket serverSocket = new Socket(targetServerHost, targetServerPort);
            RequestForwarder.forward(clientSocket.getInputStream(), serverSocket.getOutputStream());
            ResponseForwarder.forward(serverSocket.getInputStream(), clientSocket.getOutputStream());

            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
