import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class SaveTheBilby {

    public static void main(String[] args) {
        Path path = Paths.get("populationStart.txt");
        long lines = 0;
        try {

            // much slower, this task better with sequence access
            //lines = Files.lines(path).parallel().count();

            lines = Files.lines(path).count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(lines);

        ManageConservationist manage_conservation=new ManageConservationist((int)lines);
        Scanner scanner;
        try {
            scanner= new Scanner(new File("populationStart.txt"));
            for(int i=0;i<(int) lines;i++){
                
                List<String> info_this_zone=Arrays.asList(scanner.nextLine().split(","));
                // System.out.print(info_this_zone.get(2));
                manage_conservation.add_zone_info(Integer.valueOf(info_this_zone.get(0)),Integer.valueOf(info_this_zone.get(1)),Integer.valueOf(info_this_zone.get(2)), i);
            }
           
        } catch (Exception e) {
            // TODO: handle exception
        } 
        // System.out.println(manage_conservation);

        manage_conservation.run_simulation();
      
    }
}
