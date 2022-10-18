import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class SaveTheBilby {

    public static void welcome(){
        System.out.println("Welcome to Bilby survival simulation");
        String name=InputValidator.get_input("^[a-z,A-Z]{1,16}$", "Please enter the name of area : ","Name not valid, only in a-Z, lenght<=16");
    }
    public static void main(String[] args) {
        welcome();
        System.out.println("df");
        int count_line=0;
        try
        {
            // Scanner console = new Scanner(System.in);
            FileReader reader = new FileReader("populationStart.txt");
            try
            {
                Scanner fileInput = new Scanner(reader);
                while (fileInput.hasNextLine())
                {
                    count_line+=1;
                    fileInput.nextLine();
                }

            }
            finally
            {
                try
                {
                    reader.close();
                }
                catch (Exception e)
                {
                    System.out.println("Error in reading from file! Exiting...");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in writing to file! Exiting...");
        }
        
        ManageConservationist manage_conservation=new ManageConservationist(count_line);

        try
        {
            // Scanner console = new Scanner(System.in);
            FileReader reader = new FileReader("populationStart.txt");
            try
            {
                Scanner fileInput = new Scanner(reader);
                for(int i=0;i<count_line;i++)
                {
                    List<String> info_this_zone=Arrays.asList(fileInput.nextLine().split(","));
                    // System.out.println(info_this_zone);
                    manage_conservation.add_location_info(Integer.parseInt(info_this_zone.get( 0)), Integer.parseInt(info_this_zone.get(1)), Integer.parseInt(info_this_zone.get(2)),i);
                }

                System.out.println("End of file reached!");
            }
            finally
            {
                try
                {
                    reader.close();
                }
                catch (Exception e)
                {
                    System.out.println("Error in reading from file! Exiting...");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in writing to file! Exiting...");
        }
        
        manage_conservation.run_simulation();

    }
        

}
