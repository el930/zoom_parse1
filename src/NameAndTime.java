public class NameAndTime {
    private String name;
    private double totalTimeSpoken = 0.0;

    public NameAndTime(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    // time stuff
    public void increaseTime(double increase){
        if (increase < 0){
            increase = 0;
        }

        totalTimeSpoken += increase;
    }

    public double getTotalTimeSpoken(){
        return totalTimeSpoken;
    }


}
