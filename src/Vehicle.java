public class Vehicle
    //Holds the registration and make of every vehicle.
    //Superclass to every type of vehicle.
{
    private String registration;
    private String make;

    public Vehicle(String make, String registration)
    {
        this.registration = registration;
        this.make = make;
    }

    public String getRegistration()
    {
        return this.registration;
    }

    public String getMake()
    {
        return this.make;
    }

    public int calculateBasicTripCost()
    {
        return 0;
    }

    public String toString()
    {
        return(this.make + ", " + this.registration);
    }

    public static void main(String[] args) {
        Vehicle Car1 = new Vehicle("Ford", "WN06PNL");
        System.out.println(Car1.toString());
        System.out.println(Car1.getRegistration());
        System.out.println(Car1.getMake());
        System.out.println(Car1.calculateBasicTripCost());

    }
}
