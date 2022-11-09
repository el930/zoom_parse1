public class Time {

    double seconds;
    String[] stringOfTime;
    Double[] numsOfTime = new Double[3];


    public Time(String time){
        stringOfTime = time.split(":");
        for (int i = 0; i < 3; i++) {
            numsOfTime[i] = Double.parseDouble(stringOfTime[i]);
        }
        seconds = numsOfTime[0] * 3600 + numsOfTime[1] * 60 + numsOfTime[2];


    }



    public double getSeconds(){
        return seconds;
    }


}
