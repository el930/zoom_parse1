public class CondensedPerson {

    private String name;
    private double time = 0.0;



    public CondensedPerson(String name){
        this.name = name;
    }


    public double getTime(){
        return time;
    }

    public void resetTime(){
        this.time = 0.0;
    }



}
