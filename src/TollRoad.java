import java.util.ArrayList;
import java.util.List;
public class TollRoad
{
    List<CustomerAccount> accounts;
    int moneyMade;

    TollRoad(List<CustomerAccount> accounts, int moneyMade){
        this.accounts = accounts;
        this.moneyMade = moneyMade;
    }

    public String getList() //prints the array as 1 string
    {
        String str = "";

        for(int i = 0; i<this.accounts.stream().count(); i++)
        {
            str += this.accounts.get(i).toString() + "\n";
        }
        return str;
    }

    public int getMoneyMade(){
        return moneyMade;
    }

    public void addCustomer(CustomerAccount acc){
        this.accounts.add(acc);
    }

    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException{
        CustomerAccount target = null;
        for(int i = 0; i<this.accounts.stream().count(); i++){
            if(this.accounts.get(i).getVehicle().getRegistration().equalsIgnoreCase(regNum)){
                target = this.accounts.get(i);
            }
        }
        if(target==null){
            throw new CustomerNotFoundException("Customer not on toll road");
        }
        return target;
    }

    public void chargeCustomer(String registrationNumber) throws CustomerNotFoundException,
            InsufficientAccountBalanceException{
        CustomerAccount customer = findCustomer(registrationNumber);
        moneyMade += customer.makeTrip();
    }

    public static void main(String[] args) throws CustomerNotFoundException, InsufficientAccountBalanceException{
        List<CustomerAccount> List1 = new ArrayList<>();
        List1.add(new CustomerAccount("toby", "towler", 750, new Van("Ford", "AB12CDE", 540)));
        List1.add(new CustomerAccount("Mollie", "towler", 1000, new Car("Peugeot", "VW21UDK", 5)));
        List1.add(new CustomerAccount("Grace", "darnell", 1890, new Truck("Volvo", "KV13VFW", 2)));
        TollRoad road1 = new TollRoad(List1, 0);
        road1.accounts.get(1).activateStaffDiscount();
        System.out.println(road1.getList());
        System.out.println(road1.getMoneyMade());
        road1.addCustomer(new CustomerAccount("brian", "towler", 13500, new Car("Nissan", "IF14WFE", 8)));
        System.out.println(road1.getList());
        System.out.println(road1.findCustomer("AB12CDE"));
        road1.chargeCustomer("AB12CDE");
        System.out.println(road1.getMoneyMade());
        System.out.println(List1.get(0).getAccountBalance());
        road1.chargeCustomer("vW21Udk");
        System.out.println("money made " + road1.getMoneyMade());
        System.out.println("balance " + List1.get(1).getAccountBalance());
        road1.chargeCustomer("kv13vfw");
        System.out.println(road1.getMoneyMade());
        System.out.println(List1.get(2).getAccountBalance());
    }


}
