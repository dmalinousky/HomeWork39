import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HW39MainClass {
    public static void main(String[] args) throws Exception {
        // Clients creation
        Client client1 = new Client("Client 1", 3000d, 0d, 4, Goal.REPLENISH_ACCOUNT);
        Client client2 = new Client("Client 2", 3500d, 1000d, 2, Goal.GET_MONEY_FROM_ACCOUNT);
        Client client3 = new Client("Client 3", 4000d, 2000d, 1, Goal.TRANSFER_MONEY);
        Client client4 = new Client("Client 4", 4500d, 4000d, 5, Goal.EXCHANGE_MONEY);
        Client client5 = new Client("Client 5", 5000d, 6000d, 3, Goal.GET_MONEY_FROM_ACCOUNT);

        Comparator<Client> clientComparator = (o1, o2) -> o1.getQueuePosition() - o2.getQueuePosition();

        Queue<Client> clients = new PriorityQueue<>(clientComparator);
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);


        // Cashiers creation
        ArrayList<Cashier> cashiers = new ArrayList<>();
        Cashier cashier1 = new Cashier("Cashier 1", 500d, clients);
        Cashier cashier2 = new Cashier("Cashier 2", 2000d, clients);
        Cashier cashier3 = new Cashier("Cashier 3", 5000d, clients);
        cashiers.add(cashier1);
        cashiers.add(cashier2);
        cashiers.add(cashier3);
        ExecutorService cashierExecutor = Executors.newFixedThreadPool(3);

        Supervisor supervisor = new Supervisor(0d, cashiers);
        ExecutorService supervisorExecutor = Executors.newSingleThreadExecutor();
        supervisor.setTotalSum(10000d);

        while (!clients.isEmpty()) {
            cashierExecutor.submit(cashier1);
            cashierExecutor.submit(cashier2);
            cashierExecutor.submit(cashier3);
            supervisorExecutor.submit(supervisor);
        }

        cashierExecutor.shutdown();
        supervisorExecutor.shutdown();
    }
}
