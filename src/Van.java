public class Van extends Vehicle
//Subclass of Vehicle
//has additional payload variable
{
    private int payload;

    public Van(String make, String registration, int payload){
        super(make, registration);
        this.payload = payload;
    }

    @Override
    public int calculateBasicTripCost() {
        if(payload<601){
            return 500;
        }
        else if(payload<801){
            return 750;
        }
        else{
            return 1000;
        }
    }

    public int getPayload(){
        return this.payload;
    }

    public String toString()
    {
        return super.toString() + ", " + this.payload;
    }

    public static void main(String[] args) {
        Van Peugeot = new Van("Peugeot", "AO61CWG", 400);
        Van Nissan = new Van("Nissan", "AW15BHD", 700);
        Van Vauxhall = new Van("Vauxhall", "GR72IOF", 1253);
        Van van1 = new Van("", "AA32GFE", 123);
        Van van2 = new Van("", "AA32GFE", 125);

        System.out.println(Peugeot.getPayload());
        System.out.println(Peugeot.toString());
        System.out.println(Peugeot.calculateBasicTripCost());
        System.out.println(Nissan.calculateBasicTripCost());
        System.out.println(Vauxhall.calculateBasicTripCost());

        String a = "a";
        String b = "a";


        System.out.println(a.compareTo(b));
        }


}
