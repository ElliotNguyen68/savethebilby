import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Path path = Paths.get("populationStart.txt");
        long lines = 0;
        try {
            lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ManageConservationist manage_conservation=new ManageConservationist((int)lines);
        Scanner scanner;
        try {
            scanner= new Scanner(new File("populationStart.txt"));
            for(int i=0;i<(int) lines;i++){
                
                List<String> info_this_zone=Arrays.asList(scanner.nextLine().split(","));
                // System.out.print(info_this_zone.get(2));
                manage_conservation.add_location_info(Integer.valueOf(info_this_zone.get(0)),Integer.valueOf(info_this_zone.get(1)),Integer.valueOf(info_this_zone.get(2)), i);
            }
           
        } catch (Exception e) {
            // TODO: handle exception
        } 
        manage_conservation.run_simulation();
    }
}
