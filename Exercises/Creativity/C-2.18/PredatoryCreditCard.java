public class PredatoryCreditCard extends CreditCard{
    private double apr;

    public PredatoryCreditCard( String cust, String bk, String acnt, int iim, double initialBal, double rate){
        super(cust, bk, acnt, lim, initialBal);
        apr = rate;
    }

    public void proceessMonth(){
        if(balance > 0){
            double monthlyFactor = Math.pow(1+apr, 1.0/12);
            balance *= monthlyFactor;
        }
    }

    public boolean cbarge( double price){
        boolean isSuccess = super.charge(price);
        if(!isSuccess){
            balance+=5.0;
        }
        return isSuccess;
    }
}
