public class Speaker {

    private String name;
    private int numTimesSpoken = 0;
    private double timeSpoken = 0.0;
    private double timeSpokenInMinutes = 0.0;
    private  double avgSpeakingTimeInMinutes = 0.0;

    public Speaker (String name){
        this.name = name;


    }


    // name stuff
    public String getName(){
        return name;
    }


    // Time related
    public double getTimeSpoken(){
        double timeSpokenRounded = cutDecimal(timeSpoken);
        return timeSpokenRounded;
    }

    public void addSpeechTime(double time){
        timeSpoken += time;
        numTimesSpoken++;
    }

    public double getTimeSpokenInMinutes(){
        timeSpokenInMinutes = timeSpoken / 60;
        double timeSpokenInMinutesRounded = cutDecimal(timeSpokenInMinutes);
        return timeSpokenInMinutesRounded;
    }

    public double getAvgSpeakingTimeInMinutes(){
        if (numTimesSpoken == 0){
            return -1;
        }
        avgSpeakingTimeInMinutes = timeSpokenInMinutes / numTimesSpoken;
        double avgSpeakingTimeInMinutesRounded = cutDecimal(avgSpeakingTimeInMinutes);
        return avgSpeakingTimeInMinutesRounded;
    }

    private double cutDecimal(double time){
        int up = (int)(time * 100);
        double rounded = up/100.0;
        return rounded;
    }




}
