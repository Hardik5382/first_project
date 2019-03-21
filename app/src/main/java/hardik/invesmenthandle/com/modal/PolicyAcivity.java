package hardik.invesmenthandle.com.modal;

public class PolicyAcivity {
    private String pName;
    private String planNumber;
    private String planName;
    private String primumAmount;
    private String years;


    public PolicyAcivity(String pName, String planNumber, String planName, String primumAmount, String years) {
        this.pName = pName;
        this.planNumber = planNumber;
        this.planName = planName;
        this.primumAmount = primumAmount;
        this.years = years;
    }

    public String getpName() {
        return pName;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public String getPlanName() {
        return planName;
    }

    public String getPrimumAmount() {
        return primumAmount;
    }

    public String getYears() {
        return years;
    }

//    setters

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setPrimumAmount(String primumAmount) {
        this.primumAmount = primumAmount;
    }

    public void setYears(String years) {
        this.years = years;
    }
}
