import java.util.Queue;
import java.util.concurrent.Callable;

public class Cashier implements Callable<String> {
    private String name;
    private Double sum;
    private volatile Queue<Client> clients;

    public Cashier(String name, Double sum, Queue<Client> clients) {
        this.name = name;
        this.sum = sum;
        this.clients = clients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Queue<Client> getClients() {
        return clients;
    }

    public void setClients(Queue<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String call() throws Exception {
        synchronized (this.getClients()) {
            if ((!this.getClients().isEmpty()) && this.getClients() != null) {
                if (this.getClients().peek().getVisitGoal().equals(Goal.GET_MONEY_FROM_ACCOUNT)) {
                    this.getClients().peek().getTheSum();
                    System.out.println(this.getClients().peek().getName() + " has done his business. There "
                            + (this.getClients().size() - 1) + " in the line.");
                    this.getClients().poll();
                } else if (this.getClients().peek().getVisitGoal().equals(Goal.REPLENISH_ACCOUNT)) {
                    this.getClients().peek().fillTheAccount();
                    System.out.println(this.getClients().peek().getName() + " has done his business. There "
                            + (this.getClients().size() - 1) + " in the line.");
                    this.getClients().poll();
                }
            }
        }
        return "Done.";
    }
}
