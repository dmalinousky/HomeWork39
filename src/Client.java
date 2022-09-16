import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {
    private String name;
    private Double sumOnTheAccount;
    private Double sumInThePocket;
    private Integer queuePosition;
    private Goal visitGoal;

    public Client(String name, Double sumOnTheAccount, Double sumInThePocket, Integer queuePosition, Goal visitGoal) {
        this.name = name;
        this.sumOnTheAccount = sumOnTheAccount;
        this.sumInThePocket = sumInThePocket;
        this.queuePosition = queuePosition;
        this.visitGoal = visitGoal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSumOnTheAccount() {
        return sumOnTheAccount;
    }

    public void setSumOnTheAccount(Double sumOnTheAccount) {
        this.sumOnTheAccount = sumOnTheAccount;
    }

    public Double getSumInThePocket() {
        return sumInThePocket;
    }

    public void setSumInThePocket(Double sumInThePocket) {
        this.sumInThePocket = sumInThePocket;
    }

    public Integer getQueuePosition() {
        return queuePosition;
    }

    public void setQueuePosition(Integer queuePosition) {
        this.queuePosition = queuePosition;
    }

    public Goal getVisitGoal() {
        return visitGoal;
    }

    public void setVisitGoal(Goal visitGoal) {
        this.visitGoal = visitGoal;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", sumOnTheAccount=" + sumOnTheAccount +
                ", sumInThePocket=" + sumInThePocket +
                ", queuePosition=" + queuePosition +
                ", visitGoal=" + visitGoal +
                '}';
    }

    public void getTheSum() throws Exception {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter the sum:");
        Double sumToGet = 500d;
        if (this.getSumOnTheAccount() >= sumToGet && this.getSumOnTheAccount() != null
                && this.getSumOnTheAccount() != 0 && sumToGet != null && sumToGet != 0) {
            this.setSumOnTheAccount(this.getSumOnTheAccount() - sumToGet);
            this.setSumInThePocket(this.getSumInThePocket() + sumToGet);
        }
    }

    public void fillTheAccount() {
        if (this.getSumOnTheAccount() != null && this.getSumInThePocket() != null && this.getSumInThePocket() != 0) {
            this.setSumOnTheAccount(this.getSumOnTheAccount() + this.getSumInThePocket());
        }
    }


}
