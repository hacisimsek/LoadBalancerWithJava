// Authors: Haci Simsek
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> serverAddresses = new ArrayList<>();
        serverAddresses.add("localhost:8081");
        serverAddresses.add("localhost:8082");
        serverAddresses.add("localhost:8083");

        LoadBalancer loadBalancer = new LoadBalancer(serverAddresses);
        loadBalancer.start();
    }
}

