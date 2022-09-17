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

    public void setQueuePosition(Integer queuePosition) { this.queuePosition = queuePosition; }

    public Goal getVisitGoal() { return visitGoal; }

    public void setVisitGoal(Goal visitGoal) { this.visitGoal = visitGoal; }

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

    public void getTheSum(Double commission) {
        Double sumToGet = 500d;
        if (this.getSumOnTheAccount() != null && this.getSumOnTheAccount() > 0
                && sumToGet != null && sumToGet > 0 && this.getSumOnTheAccount() >= sumToGet) {
            this.setSumOnTheAccount(this.getSumOnTheAccount() - (sumToGet + commission));
            this.setSumInThePocket(this.getSumInThePocket() + sumToGet);
        }
    }

    public void fillTheAccount(Double commission) {
        if (this.getSumOnTheAccount() != null && this.getSumInThePocket() != null && this.getSumInThePocket() > 0) {
            this.setSumOnTheAccount(this.getSumOnTheAccount() + this.getSumInThePocket() - commission);
        }
    }

    public void transferTheSum(Double commission) {
        Double sumToTransfer = 1000d;
        if (this.getSumOnTheAccount() != null && this.getSumOnTheAccount() > 0
                && sumToTransfer != null && sumToTransfer > 0 && this.getSumOnTheAccount() >= sumToTransfer) {
            this.setSumOnTheAccount(this.getSumOnTheAccount() - (sumToTransfer + commission));
        }
    }

    public void exchangeTheSum(Double commission) {
        Double rate = 2.5;
        if (this.getSumInThePocket() != null && this.getSumInThePocket() > 0) {
            this.setSumInThePocket(this.getSumInThePocket() / rate - commission);
        }
    }

}
