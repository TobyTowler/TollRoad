public class Car extends Vehicle
//Subclass of Vehicle
//has additional  number of seats variable
{
private int numberOfSeats;

public Car(String make, String registration, int numberOfSeats)
{
    super(make, registration);
    this.numberOfSeats=numberOfSeats;
}

    @Override
    public int calculateBasicTripCost() {
        if(numberOfSeats<6){
            return 500;
        }
        else{
            return 600;
        }
    }

    public int getNumberOfSeats(){
        return this.numberOfSeats;
    }

    public String toString(){
        return super.toString() +  ", " + this.numberOfSeats;
    }

    public static void main(String[] args) {
        Car Volvo = new Car("Volvo", "AX21WEF", 4);
        Car Seat = new Car("SEAT", "UV23FGE", 8);

        System.out.println(Volvo.getNumberOfSeats());
        System.out.println(Volvo.toString());
        System.out.println(Volvo.calculateBasicTripCost());
        System.out.println(Seat.calculateBasicTripCost());
    }
}
