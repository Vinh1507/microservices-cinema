package model;
public class Customer extends User {
    private String customerRanking;
    private int rewardPoint;

    // Constructor
    public Customer(int id, Address address, Fullname fullname, String customerRanking, int rewardPoint) {
        super(id, address, fullname);
        this.customerRanking = customerRanking;
        this.rewardPoint = rewardPoint;
    }

    public String getCustomerRanking() {
        return customerRanking;
    }

    public void setCustomerRanking(String customerRanking) {
        this.customerRanking = customerRanking;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }
}
