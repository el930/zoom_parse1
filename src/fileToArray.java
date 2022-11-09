import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fileToArray {

    String filePath;
    String text;

    public fileToArray(String filePath){
        this.filePath = filePath;
        try {
            String file = readFile(filePath);
            text = file;
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))){

            String line = br.readLine();
            while (line != null){
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = br.readLine();
            }


        } catch (Exception errObj){
            System.err.println("There was a problem reading " + filePath);

        }

        String fullText = sb.toString();
        return fullText;
    }

    @Override
    public String toString(){
        return readFile(filePath);
    }

    public String[] toStringArr(){
        String[] fileLine = text.split("\n");
        return fileLine;
    }


}
