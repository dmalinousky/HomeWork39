import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HW39MainClass {
    public static void main(String[] args) throws Exception {
        Client client1 = new Client("Client 1", 3000d, 0d, 4, Goal.REPLENISH_ACCOUNT);
        Client client2 = new Client("Client 2", 3500d, 1000d, 6, Goal.GET_MONEY_FROM_ACCOUNT);
        Client client3 = new Client("Client 3", 4000d, 2000d, 3, Goal.GET_MONEY_FROM_ACCOUNT);
        Client client4 = new Client("Client 4", 4500d, 4000d, 12, Goal.REPLENISH_ACCOUNT);
        Client client5 = new Client("Client 5", 5000d, 6000d, 5, Goal.GET_MONEY_FROM_ACCOUNT);

        Comparator<Client> clientComparator = (o1, o2) -> o1.getQueuePosition() - o2.getQueuePosition();

        Queue<Client> clients = new PriorityQueue<>(clientComparator);
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);

        Cashier cashier1 = new Cashier("Cashier 1", 0d, clients);
        Cashier cashier2 = new Cashier("Cashier 2", 0d, clients);
        Cashier cashier3 = new Cashier("Cashier 3", 0d, clients);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        while (!clients.isEmpty()) {
            executor.submit(cashier1);
            executor.submit(cashier2);
            executor.submit(cashier3);
        }
    }
}
