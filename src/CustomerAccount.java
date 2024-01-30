public class CustomerAccount implements Comparable<CustomerAccount>
//holds info about customers
{
    private String fName;
    private String lName;
    private int accountBalance;
    private Vehicle vehicle;
    private enum Discount{Staff, FriendsAndFamily};
    private Discount discount;



    CustomerAccount(String fname, String lName, int accountBalance, Vehicle vehicle){
        this.fName = fname;
        this.lName = lName;
        this.accountBalance = accountBalance;
        this.vehicle = vehicle;
        this.discount = null;
    }

    public void activateStaffDiscount(){
        this.discount = Discount.Staff;
    }

    public void activateFriendsAndFamilyDiscount(){
        this.discount = Discount.FriendsAndFamily;
    }

    public void deactiveDiscount(){
        this.discount = null;
    }

    public void addFunds(int amount){
        accountBalance += amount;
    }

    public String getFName(){
        return this.fName;
    }

    public int makeTrip() throws InsufficientAccountBalanceException{
        double cost = this.vehicle.calculateBasicTripCost();
        if(discount==Discount.Staff){
            cost /=2;
        }
        if(discount==Discount.FriendsAndFamily){
            cost *=.9;
        }
        int costInt = (int)cost;

        if(this.accountBalance>=costInt){
            this.accountBalance -= costInt;
        }
        else{
            throw new InsufficientAccountBalanceException(this.fName + " " + this.lName + " " +
                    "does not have enough balance");
        }

        return costInt;
    }

    public String getLName(){
        return this.lName;
    }

    public int getAccountBalance(){
        return this.accountBalance;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public Discount getDiscount(){
        return this.discount;
    }

    public String toString(){
        return this.fName + " " + this.lName + ", " + this.accountBalance + ", " + this.vehicle.toString() + ", " +
                this.discount;
    }

    public int compareTo(CustomerAccount customer){
        if(this.vehicle.getRegistration().toLowerCase().charAt(0)>customer.vehicle.getRegistration()
                .toLowerCase().charAt(0)){
            return 1;
        }
        else if(this.vehicle.getRegistration().toLowerCase().charAt(0)<customer.vehicle.getRegistration()
                .toLowerCase().charAt(0)){
            return -1;
        }
        else{
            return 0;
        }
    }


    public static void main(String[] args) throws InsufficientAccountBalanceException{
        CustomerAccount Toby = new CustomerAccount("Toby", "Towler", 750,
                new Van("Van1", "BW42GEF", 750));
        Toby.activateFriendsAndFamilyDiscount();
        System.out.println(Toby);
        Toby.activateStaffDiscount();
        System.out.println(Toby.getDiscount());
        Toby.activateFriendsAndFamilyDiscount();
        System.out.println(Toby.getDiscount());
        Toby.deactiveDiscount();
        System.out.println(Toby.getDiscount());
        System.out.println(Toby.getFName());
        System.out.println(Toby.getLName());
        Toby.addFunds(150);
        System.out.println(Toby.getAccountBalance());
        System.out.println(Toby.getVehicle());
        try{
            System.out.println(Toby.makeTrip());
        }
        catch (InsufficientAccountBalanceException e){
            System.out.println(e.getMessage());
        }

        Van v2 = new Van("ford", "dW23wfe", 670);

        CustomerAccount Drew = new CustomerAccount("Drew", "Foug", 100000, v2);
        System.out.println(Drew.vehicle.getRegistration());
        System.out.println(Toby.compareTo(Drew));

    }


}
