import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Supervisor implements Callable<ArrayList<Cashier>> {
    private Double totalSum;
    private ArrayList<Cashier> cashiers;

    public Supervisor(Double totalSum, ArrayList<Cashier> cashiers) {
        this.totalSum = totalSum;
        this.cashiers = cashiers;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public ArrayList<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(ArrayList<Cashier> cashiers) {
        this.cashiers = cashiers;
    }

    @Override
    public ArrayList<Cashier> call() throws Exception {
        supervise();
        return this.getCashiers();
    }

    public void supervise() {
        for (int i = 0; i < this.getCashiers().size(); i++) {
            synchronized (this.getCashiers().get(i)) {
                if (this.getCashiers().get(i).getSum() < 1000) {
                    double difference = 1000 - this.getCashiers().get(i).getSum();
                    this.getCashiers().get(i).setSum(this.getCashiers().get(i).getSum() + difference);
                    System.out.println(this.getCashiers().get(i).getName() + " was fulfilled with $"
                            + difference + " by supervisor.");
                    synchronized (this.getTotalSum()) {
                        this.setTotalSum(this.getTotalSum() - difference);
                        System.out.println("New total sum is: $" + this.getTotalSum() + ".");
                    }

                } else if (this.getCashiers().get(i).getSum() > 1000) {
                    double difference = this.getCashiers().get(i).getSum() - 1000;
                    this.getCashiers().get(i).setSum(this.getCashiers().get(i).getSum() - difference);
                    System.out.println(this.getCashiers().get(i).getName() + " has handed over $"
                            + difference + " to supervisor.");
                    synchronized (this.getTotalSum()) {
                        this.setTotalSum(this.getTotalSum() + difference);
                        System.out.println("New total sum is: $" + this.getTotalSum() + ".");
                    }
                }
            }
        }
    }
}
