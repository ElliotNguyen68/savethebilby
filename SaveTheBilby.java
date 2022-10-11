import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class SaveTheBilby {

    public static void main(String[] args) {

        ManageConservationist manage_conservation=new ManageConservationist(10);
        Scanner scanner;
        try {
            scanner= new Scanner(new File("populationStart.txt"));
            for(int i=0;i<10;i++){
                
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
