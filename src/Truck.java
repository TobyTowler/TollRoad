public class Truck extends Vehicle
//SubClass of Vehicle
//Has additional number of trailers variable
{
    private int numTrailers;

    public Truck(String make, String registration, int numTrailers){
        super(make, registration);
        this.numTrailers = numTrailers;
    }

    @Override
    public int calculateBasicTripCost() {
        if(numTrailers<2){
            return 1250;
        }
        else{
            return 1500;
        }
    }

    public int getNumTrailers(){
        return this.numTrailers;
    }

    @Override
    public String toString() {
        return super.toString() + ", " +  this.numTrailers;
    }

    public static void main(String[] args) {
        Truck MAN = new Truck("MAN", "GE23UDN", 1);
        Truck DAF = new Truck("DAF", "XU17JKD", 3);

        System.out.println(MAN.toString());
        System.out.println(MAN.getNumTrailers());
        System.out.println(MAN.calculateBasicTripCost());
        System.out.println(DAF.calculateBasicTripCost());
    }

}
