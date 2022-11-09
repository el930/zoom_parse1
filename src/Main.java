import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {


        fileToArray zoomTranscript = new fileToArray("src/Files/transcript.txt");
        String[] transcript = zoomTranscript.toStringArr();
        ArrayList<Speaker> ppl = new ArrayList<>();
        String condensedTranscriptText = "";
        String summaryStatsText = "";


        int speakerSwitchCounter = 0;
        double sessionTime = 0;

        // list of ppl's names (done)
        ArrayList<String> pplName = new ArrayList<>();
        for (int i = 0; i < transcript.length -1; i+=4) {
            // create list of ppl names
            String[] nameSpeechSplit = transcript[i+2].split(":");
            if (nameSpeechSplit.length == 1){
                continue;
            }
            String name = nameSpeechSplit[0];
            int counter = 0;
            for (int j = 0; j < pplName.size(); j++) {
                if (!pplName.get(j).contains(name)){
                    counter++;
                }

            }
            if (counter == pplName.size()){
                pplName.add(name);
            }

        }


        // set ppl array
        for (int i = 0; i < pplName.size(); i++) {
            ppl.add(new Speaker(pplName.get(i)));


        }

        String[] nameSpeechSplit = new String[2];
        String lastSpeaker;

        nameSpeechSplit = transcript[2].split(":");
        lastSpeaker = nameSpeechSplit[0];




        for (int i = 0; i < transcript.length; i += 4) {

            // parse times
            String[] timeSplit = transcript[i+1].split(" --> ");
            Time start = new Time(timeSplit[0]);
            Time end = new Time(timeSplit[1]);
            double startTime = start.getSeconds();
            double endTime = end.getSeconds();
            sessionTime = endTime;
            double differenceTime = endTime - startTime;
            int index;



            nameSpeechSplit = transcript[i+2].split(":");

            // finds index of person
            if (ifPersonExists(nameSpeechSplit[0], ppl)){
                index = findPersonIndex(nameSpeechSplit[0],ppl);
            } else{
                index = findPersonIndex(lastSpeaker, ppl);
                transcript[i+2] = lastSpeaker + ": " + nameSpeechSplit[0];
                nameSpeechSplit = parseSpeechTranscriptLine(transcript[i+2]);
            }
            double rounded = cutDecimal(differenceTime);
            condensedTranscriptText += nameSpeechSplit[0] + ": " + rounded + " sec \n";


            ppl.get(index).addSpeechTime(differenceTime);


            // counts how many times the speakers switch
            if (sameAsLastSpeaker(lastSpeaker, nameSpeechSplit[0]) == false){
                speakerSwitchCounter++;
            }



            lastSpeaker = nameSpeechSplit[0];
        }




        //create stats file string
        double sessionTimeInMinutes = cutDecimal(sessionTime / 60.0);
        summaryStatsText += "Total # people: " + pplName.size() + "\n" +
                            "Total length of session: " + sessionTimeInMinutes + " min \n" +
                            "Total # of speaker switches: " + speakerSwitchCounter + "\n" +
                            "\n" +
                            "Total talk time \n";
        for (int i = 0; i < ppl.size(); i++) {
            summaryStatsText += ppl.get(i).getName() + ": " + ppl.get(i).getTimeSpokenInMinutes() + " min \n";
        }

        summaryStatsText += "\n" +
                            "Average speaking time \n";
        for (int i = 0; i < ppl.size(); i++) {
            summaryStatsText += ppl.get(i).getName() + ": " + ppl.get(i).getAvgSpeakingTimeInMinutes() + " min \n";
        }








        StringToFile condensedTranscriptFile = new StringToFile("C:\\Users\\ethan\\IdeaProjects\\zoom parse v2\\src\\Files\\condensedTranscript.txt", condensedTranscriptText );
        StringToFile summaryStatsFile = new StringToFile("C:\\Users\\ethan\\IdeaProjects\\zoom parse v2\\src\\Files\\summaryStats.txt", summaryStatsText);


    }

    public static void printArray(String[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }



    public static boolean ifPersonExists(String possibleName, ArrayList<Speaker> speakers){
        boolean exists = false;
        for (int i = 0; i < speakers.size(); i++) {
            if (possibleName.equals(speakers.get(i).getName())){
                exists = true;
            }
        }

        return exists;
    }

    public static int findPersonIndex(String name, ArrayList<Speaker> speakers){
        int index = 0;
        for (int i = 0; i < speakers.size(); i++) {
            if (speakers.get(i).getName().equals(name)){
                index = i;
            }
        }
        return index;
    }

    public static boolean sameAsLastSpeaker(String lastSpeakerName, String currentSpeakerName){
        if (lastSpeakerName.equals(currentSpeakerName)){
            return true;
        }
        return false;
    }

    public static String[] parseSpeechTranscriptLine(String transcriptLine){
        String[] nameSpeechSplit = transcriptLine.split(":");
        return nameSpeechSplit;


    }

    public static double cutDecimal(double time){
        int up = (int)(time * 100);
        double rounded = up/100.0;
        return rounded;
    }



}

