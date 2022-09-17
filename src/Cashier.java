import java.util.Queue;
import java.util.concurrent.Callable;

public class Cashier implements Callable<Queue<Client>> {
    private String name;
    private Double sum;
    private volatile Queue<Client> clients;
    private Double commission = 500d;

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

    public Double getCommission() { return commission; }

    public void setCommission(Double comission) { this.commission = comission; }

    @Override
    public Queue<Client> call() throws Exception {
        Client tempClient = null;
        synchronized (this.getClients()) {
            tempClient = this.getClients().peek();
            this.getClients().poll();
        }
        if (tempClient != null) {
            if (tempClient.getVisitGoal().equals(Goal.GET_MONEY_FROM_ACCOUNT)) {
                System.out.println(tempClient.getName() + " has started his business.");
                tempClient.getTheSum(this.getCommission());
                this.setSum(this.getSum() + this.getCommission());
                System.out.println(tempClient.getName() + " has done his business.");
                System.out.println(tempClient);
            } else if (tempClient.getVisitGoal().equals(Goal.REPLENISH_ACCOUNT)) {
                System.out.println(tempClient.getName() + " has started his business.");
                tempClient.fillTheAccount(this.getCommission());
                this.setSum(this.getSum() + this.getCommission());
                System.out.println(tempClient.getName() + " has done his business.");
                System.out.println(tempClient);
            } else if (tempClient.getVisitGoal().equals(Goal.TRANSFER_MONEY)) {
                System.out.println(tempClient.getName() + " has started his business.");
                tempClient.transferTheSum(this.getCommission());
                this.setSum(this.getSum() + this.getCommission());
                System.out.println(tempClient.getName() + " has done his business.");
                System.out.println(tempClient);
            } else if (tempClient.getVisitGoal().equals(Goal.EXCHANGE_MONEY)) {
                System.out.println(tempClient.getName() + " has started his business.");
                tempClient.exchangeTheSum(this.getCommission());
                this.setSum(this.getSum() + this.getCommission());
                System.out.println(tempClient.getName() + " has done his business.");
                System.out.println(tempClient);
            }
        }
        return this.getClients();
    }
}
