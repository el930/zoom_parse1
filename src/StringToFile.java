import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;


    import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

    public class StringToFile {

        public StringToFile(String filePath, String text){

            try (FileWriter f = new FileWriter(filePath);
                 BufferedWriter b = new BufferedWriter(f);
                 PrintWriter writer = new PrintWriter(b);){


                writer.println(text);

                //  writer.close();
            }
            catch (Exception errorObj){
                System.out.println("There was an error with the file");
                errorObj.printStackTrace();
            }

        }


    }



