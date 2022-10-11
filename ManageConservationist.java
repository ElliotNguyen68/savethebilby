import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


class ManageConservationist {
    Zone list_zones[];
    int num_zones;
    private int limit_bilby=20;
    public int total_bilby_start=0;

    public ManageConservationist(int num_zones) {
        this.num_zones = num_zones;
        list_zones = new Zone[num_zones];
    }

    public void statistic() {
        for (Zone zone : list_zones) {
            zone.update_status();
            // System.out.println(zone.num_bilby_so_far);
            System.out.println(String.format("Location %d, bilby: %d, cat: %d, fox: %d", zone.zone_number+1,zone.num_bilby,zone.num_cat,zone.num_fox));
            // System.out.println(zone.num_bilby);
            // System.out.println(zone.num_fox);
            // System.out.println(zone.num_cat);
        }
    }

    public void add_zone_info(int num_bilby, int num_cat, int num_fox, int zone_number) {
        this.list_zones[zone_number] = new Zone(num_bilby, num_cat, num_fox, zone_number);
        this.total_bilby_start+=num_bilby;
    }

    public boolean check_amount_bilby(int zone_num,int amount,boolean sub_from){
        this.list_zones[zone_num].update_status(); 
        if (sub_from){
            return this.list_zones[zone_num].num_bilby>=amount;
        }
        return this.list_zones[zone_num].num_bilby+amount<=this.limit_bilby;
    }

    public boolean relocate_2_zone(int zone_num1,int zone_num2, int amount){
        zone_num1-=1;
        zone_num2-=1;
        if (zone_num1>=this.num_zones |zone_num2>=this.num_zones | zone_num1<0 |zone_num2<0){
            return false;
        }

        boolean check=check_amount_bilby(zone_num1, amount, true) && check_amount_bilby(zone_num2, amount, false);
        if (! check ){
            return false;
        }
        ArrayList<Animal> tmp=new ArrayList<>();
        Iterator<Animal> iter_bilby=this.list_zones[zone_num1].list_bilby.iterator();
        while (iter_bilby.hasNext() &&(amount>0)){
            Animal tmp_bilby=iter_bilby.next();
            if (tmp_bilby.is_alive){
                tmp.add(tmp_bilby);
                iter_bilby.remove();
                amount-=1;
            }
        }

        this.list_zones[zone_num2].list_bilby.addAll(tmp);    
        return true;
    }

    public void interventory(){
        for (Zone zone:this.list_zones){
            zone.die_by_haft_predators();
        }
    }

    
    public void action_option(int option ){
        Scanner scanner =new Scanner(System.in);
        if (option==3){
            // scanner.close();
            return ;
        }
        if(option==1){
            boolean check=false;
            String des;
            while (!check){
                System.out.println("Enter 2 location and amount bilby to relocate");

                int zone_num1=Integer.valueOf(InputValidator.get_input("^([0-9]*)$", "1st location (from):"));
                int zone_num2=Integer.valueOf(InputValidator.get_input("^([0-9])*$", "2nd location (to):"));
                

                int amount=Integer.valueOf(InputValidator.get_input("^([0-9]*)$", "Amount: "));
                check=this.relocate_2_zone(zone_num1, zone_num2, amount)&& (zone_num1!=zone_num2);
                if (! check){

                    des=InputValidator.get_input("^(y|Y|n|N)$","Invalid input, do you want to try another(Y-N) ? ").toLowerCase();
                    if (des.equals("n")){
                        check=true;
                    }
                }
                else{
                    System.out.println("Statistic after relocate");
                    this.statistic();
                    System.out.println("Done relocation, do you want to try another(Y-N) ? ");
                    des=scanner.next().strip().toLowerCase();
                    if (des.equals("n")){
                        check=true;
                    } 
                    else{
                        this.action_option(1);
                    }
                }
                    
            }

        }
        if (option==2){
            this.interventory();
            System.out.println("Done interventory");

        }
        // scanner.close();
    }

    public void limit_bilby(){
        for (Zone zone: this.list_zones){
            zone.limit_bilby(this.limit_bilby);
        }
    }

    public void finalize(){
        int total_bilby_end=0;
        int total_new_birth_bilby=0;
        int total_death_bilby=0; 
        int total_init_bilby=0;
        int total_init_predator=0;
        int total_predator_end=0;
        try {
            FileWriter myWriter = new FileWriter("populationFinal.txt");
            System.out.println("============ Population details at each location =========");
            myWriter.write("============ Population details at each location =========\n");
            for(Zone zone: this.list_zones){
                zone.update_status();
                System.out.println(String.format("-------Location %dth-------",zone.zone_number+1));
                myWriter.write(String.format("-------Location %dth-------\n",zone.zone_number+1));

                System.out.println("Alive");
                myWriter.write("Alive \n");

                System.out.println(String.format("bilby: %d, cat: %d, fox: %d", zone.num_bilby,zone.num_cat,zone.num_fox));
                myWriter.write(String.format("bilby: %d, cat: %d, fox: %d \n", zone.num_bilby,zone.num_cat,zone.num_fox));

                System.out.println("New born");
                myWriter.write("New born \n");

                System.out.println(String.format("bilby: %d, cat: %d, fox: %d", zone.num_bilby_so_far-zone.init_bilby,zone.num_cat_so_far-zone.init_cat,zone.num_fox_so_far-zone.init_fox));
                myWriter.write(String.format("bilby: %d, cat: %d, fox: %d \n", zone.num_bilby_so_far-zone.init_bilby,zone.num_cat_so_far-zone.init_cat,zone.num_fox_so_far-zone.init_fox));

                System.out.println("Have died");
                myWriter.write("Have died \n");

                System.out.println(String.format("bilby: %d, cat: %d, fox: %d", zone.num_bilby_so_far-zone.num_bilby,zone.num_cat_so_far-zone.num_cat,zone.num_fox_so_far-zone.num_fox));
                myWriter.write(String.format("bilby: %d, cat: %d, fox: %d \n", zone.num_bilby_so_far-zone.num_bilby,zone.num_cat_so_far-zone.num_cat,zone.num_fox_so_far-zone.num_fox));
                
                
                total_bilby_end+=zone.num_bilby;
                total_new_birth_bilby+=zone.num_bilby_so_far-zone.init_bilby;
                total_death_bilby+=zone.num_bilby_so_far-zone.num_bilby;
                total_init_bilby+=zone.init_bilby;
                total_init_predator+=zone.init_fox+zone.init_cat;
                total_predator_end+=zone.num_cat+zone.num_fox;
            }       

            System.out.println("============ Bilby population change =========");
            myWriter.write("============ Bilby population change =========\n");
             

            System.out.println(String.format("Bilby at the start: %d, end: %d, change rate: %d%%", this.total_bilby_start,total_bilby_end,100*(total_bilby_end-this.total_bilby_start)/this.total_bilby_start));
            myWriter.write(String.format("Bilby at the start: %d, end: %d, change rate: %d%% \n", this.total_bilby_start,total_bilby_end,100*(total_bilby_end-this.total_bilby_start)/this.total_bilby_start));
            
            
            System.out.println("============ Bilby population stability factor =========");
            myWriter.write("============ Bilby population stability factor =========\n");


            System.out.println(String.format("BilBy new birth: %d, dead: %d stability factor %d%%",total_new_birth_bilby,total_death_bilby, 100*(total_new_birth_bilby+total_death_bilby)/total_init_bilby));
            myWriter.write(String.format("BilBy new birth: %d, dead: %d stability factor %d%%",total_new_birth_bilby,total_death_bilby, 100*(total_new_birth_bilby+total_death_bilby)/total_init_bilby));

            System.out.println("============ Predator population change =========");
            myWriter.write("============ Predator population change =========\n");
           
            System.out.println(String.format("Predator at the end: %d, start: %d change rate: %d%%",total_predator_end,total_init_predator,100*(total_predator_end-total_init_predator)/total_init_predator));
            myWriter.write(String.format("Predator at the end: %d, start: %d change rate: %d%%",total_predator_end,total_init_predator,100*(total_predator_end-total_init_predator)/total_init_predator));

            
            myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        return ;
    }

    public void run_simulation() {
        boolean continue_simulate =true;
        for (Zone zone : this.list_zones) {
            zone.init_animal();
        }
        System.out.println("=========Start simulation===========");
        
        System.out.println("Polulation at the begining");
        this.statistic();

        boolean did_interventory=false;
        for (int k = 0; k < 12; k++) {
            for (int i = 0; i < this.num_zones; i++) {
                Zone zone = this.list_zones[i];
                zone.one_month_process();
            }
            System.out.println(String.format("=========Month %d=========",k+1));
            this.statistic();

            String descision=InputValidator.get_input("^(y|Y|n|N)$","Continue simulation (Y-N)? ").toLowerCase();
 
            if (descision.toLowerCase().equals("n")){
                continue_simulate=false;
            }
            else{
                boolean valid_choice=false;
                while (! valid_choice){
                    System.out.println("Select following options:");
                    System.out.println("1.Relocate bilby between 2 location");
                    if(! did_interventory){
                        System.out.println("2.Conduct interventory");
                    }
                    System.out.println("3.Pass");
                    // System.out.println("Your selection [1-3]: ");
                    // int option=Integer.valueOf(scanner.next().strip());
                    int option=Integer.valueOf(InputValidator.get_input("^([1-3])$", "Your selection [1-3]: "));
                    if (option==1 ||option==3 ||(option==2 && ! did_interventory)){
                        this.action_option(option);
                        valid_choice=true;
                        if (option==2){
                            did_interventory=true;
                        }
                    }
                    else{
                        valid_choice=false;
                        System.out.println("Not a valid input");
                    }
                }
            
            }
            if (! continue_simulate){
                this.finalize();
                break;
            }
            this.limit_bilby();
        }
    }

}

