import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.Scanner;

public class TollRoadMain
{
public static TollRoad initialiseTollRoadFromFile(){
    TollRoad thisRoad = new TollRoad(new ArrayList<CustomerAccount>(), 0);
    String str = "";
    try{
        File data = new File("customerData.txt");
        Scanner reader = new Scanner(data);
        while(reader.hasNextLine()){
            str += reader.nextLine();
        }
    reader.close();
    }
    catch(FileNotFoundException e){
        System.out.println("There as been an error" + e);
    }

    String[] parts = str.split("#");
    for(int i = 0; i<parts.length; i++){
        int info = 0;
        int balance = 0;
        CustomerAccount temp = null;
        String[] pieces = parts[i].split(",");
        //convert the vehicle info to integer
        try{
            info = Integer.parseInt(pieces[5]);
            balance = Integer.parseInt(pieces[6]);
        }
        catch(NumberFormatException e){
            System.err.println("invalid vehicle info");
        }

        //create account with correct vehicle type
        if(pieces[0].equalsIgnoreCase("van")) {
            temp = new CustomerAccount(pieces[2], pieces[3], balance, new Van(pieces[4], pieces[1], info));
        }
        else if(pieces[0].equalsIgnoreCase("car")){
            temp = new CustomerAccount(pieces[2], pieces[3], balance, new Car(pieces[4], pieces[1], info));
        }
        else if(pieces[0].equalsIgnoreCase("truck")){
            temp = new CustomerAccount(pieces[2], pieces[3], balance, new Truck(pieces[4], pieces[1], info));
        }
        else{
            System.err.println("Invalid vehicle type");
        }

        //activate discounts
        if(pieces[7].equalsIgnoreCase("staff")){
            temp.activateStaffDiscount();
        }
        if(pieces[7].equalsIgnoreCase("FRIENDS_AND_FAMILY")){
            temp.activateFriendsAndFamilyDiscount();
        }

        thisRoad.accounts.add(temp);

    }


    return thisRoad;
}

public static void SimulateFromFile(TollRoad road){
    String str = "";
    try{
        File data = new File("transactions.txt");
        Scanner reader = new Scanner(data);
        while(reader.hasNextLine()){
            str += reader.nextLine();
        }
        reader.close();
    }
    catch(FileNotFoundException e){
        System.out.println("There as been an error" + e);
    }
    String[] parts = str.split("\\$"); //use \\ so $ is treated as character and not regex
    for(int i = 0; i<parts.length; i++){
        //System.out.println(parts[0] + "\n");
    }
    for(int i = 0; i<parts.length; i++){

        CustomerAccount customer = null;
        int amount = 0;
        String[] pieces = parts[i].split(",");
        String reg = pieces[1];

        //add funds
        if(pieces[0].equalsIgnoreCase("addfunds")){
        try{
            amount = Integer.parseInt(pieces[2]);
        }
        catch(NumberFormatException e){
            System.err.println("invalid number format");
        }
        if(pieces[0].equalsIgnoreCase("addFunds")) {
            try { //find the customer the query is about
                customer = road.findCustomer(reg);
                customer.addFunds(amount);
                System.out.println(reg + ": " + amount + " added successfully");
            }
                catch(CustomerNotFoundException e){
                System.err.println(reg + ": addFunds failed. CustomerAccount does not exist");
            }
        }

        }
        //makeTrip
        else if(pieces[0].equalsIgnoreCase("maketrip")){
            //System.out.println(road.getMoneyMade());
            try { //find the customer the query is about
                customer = road.findCustomer(reg);
                int temp = customer.makeTrip();
                road.moneyMade +=temp;
                System.out.println(reg + ": Trip completed successfully");
            }
            catch(CustomerNotFoundException e){
                System.err.println(reg + ": makeTrip failed. CustomerAccount does not exist");
            }
            catch (InsufficientAccountBalanceException e){
                System.err.println(reg + ": makeTrip failed. Insufficient funds");
            }

        }
        else{
            System.err.println("Error with action");
        }
    }


}

    public static void main(String[] args) {
        TollRoad road1 = initialiseTollRoadFromFile();
        //System.out.println(road1.getList());
        SimulateFromFile(road1);
        System.out.println("The toll road made " + road1.getMoneyMade() + "p during the simulation");
    }


}
