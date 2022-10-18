import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


class ManageConservationist {
    Location list_locations[];
    int num_locations;
    private int limit_bilby=20;
    public int total_bilby_start=0;
    private boolean done_interventory_locations[];

    public ManageConservationist(){}

    /*
     * @param list_locations Location[]
     */
    public void setList_locations(Location[] list_locations) {
        this.list_locations = list_locations;
    }

    /*
     * @param num_locations int
     */
    public void setNum_locations(int num_locations) {
        this.num_locations = num_locations;
    }

    /*
     * @param limit_bilby int
     */
    public void setLimit_bilby(int limit_bilby) {
        this.limit_bilby = limit_bilby;
    }

    /*
     * @param total_bilby_start int
     */
    public void setTotal_bilby_start(int total_bilby_start) {
        this.total_bilby_start = total_bilby_start;
    }

    /*
     * @param num_locations int
     */
    public ManageConservationist(int num_locations) {
        this.num_locations = num_locations;
        list_locations = new Location[num_locations];
        done_interventory_locations=new boolean[num_locations];
    }

    public Location[] getList_locations() {
        return list_locations;
    }

    public int getNum_locations() {
        return num_locations;
    }

    public int getLimit_bilby() {
        return limit_bilby;
    }

    public int getTotal_bilby_start() {
        return total_bilby_start;
    }

    public void statistic() {
        for (Location location : list_locations) {
            location.update_status();
            System.out.println(location);
        }
    }

     /*
     * @param num_bilby int
     * @param num_cat int
     * @param num_fox int
     * @param location_number int
     */
    public void add_location_info(int num_bilby, int num_cat, int num_fox, int location_number) {
        this.list_locations[location_number] = new Location(num_bilby, num_cat, num_fox, location_number);
        this.total_bilby_start+=num_bilby;
    }

    /*
     * @param location_num int
     * @param amount int
     * @param sub_from int
     * @return boolean
     */
    public boolean check_amount_bilby(int location_num,int amount,boolean sub_from){
        // this.list_locations[Location_num].update_status(); 
        boolean check;
        if (sub_from){

            check= this.list_locations[location_num].num_bilby>=amount;
            if (! check){
                System.out.println(String.format("Can not get %d from %d", amount,this.list_locations[location_num].num_bilby));
                return check;
            }
            return true;
        }

        check= this.list_locations[location_num].num_bilby+amount<=this.limit_bilby;
        if (!check){
            System.out.println(String.format("%d + %d will over limit %d", this.list_locations[location_num].num_bilby,amount,this.limit_bilby));
        }
        return true;
    }

    /*
     * @param location_num int
     * @param amount int
     * @param sub_from int
     * @return boolean
     */
    public boolean relocate_2_Location(int location_num1,int location_num2, int amount){
        location_num1-=1;
        location_num2-=1;
        // System.out.println(String.format("%d %d", location_num1,location_num2));
        if (location_num1>=this.num_locations |location_num2>=this.num_locations | location_num1<0 |location_num2<0){
            return false;
        }

        boolean check=check_amount_bilby(location_num1, amount, true) && check_amount_bilby(location_num2, amount, false);
        if (! check ){
            return false;
        }
        ArrayList<Animal> tmp=new ArrayList<>();
        Iterator<Animal> iter_bilby=this.list_locations[location_num1].list_bilby.iterator();
        while (iter_bilby.hasNext() &&(amount>0)){
            Animal tmp_bilby=iter_bilby.next();
            if (tmp_bilby.is_alive){
                tmp.add(tmp_bilby);
                iter_bilby.remove();
                amount-=1;
            }
        }

        this.list_locations[location_num2].list_bilby.addAll(tmp);    
        return true;
    }

    public void interventory(){
        // we have an array to store whether a location did conduct interventory or not,
        // check amount of not conduct yet(location conducted will have value in done_interventory_locations=true)
        // , if number of left locations is >0, so we can do interventory
        // list all available location, their values in done_interventory_locations = false, check valid input by regex
        // and go to location to make die by half function
        int count_left=0;
        for (boolean check: this.done_interventory_locations){
            if (!check){
                count_left+=1;
            }
        }
        if (count_left==0){
            System.out.println("No left location can conduct interventory");
            return;
        }
        System.out.println("Available locations to do interventory: ");
        ArrayList tmp=new ArrayList<Integer>();
        for (int i=0;i<this.done_interventory_locations.length;i++){
            if (!this.done_interventory_locations[i]){
                tmp.add(i);
                System.out.println(this.list_locations[i]);
            }
        }
        String valid_regex="^(";
        for (int i=0;i<tmp.size();i++){
            valid_regex+=String.format("%d|", ((int)tmp.get(i)+1));
        }
        valid_regex=valid_regex.substring(0, valid_regex.length()-1);
        valid_regex+=")$";
        // System.out.println(valid_regex);
        int location=Integer.valueOf(InputValidator.get_input(valid_regex, "Enter location want to conduct interventory: ","Not a valid location"));
        
        this.list_locations[location-1].die_by_haft_predators();
        this.done_interventory_locations[location-1]=true;
        System.out.println("Done interventory");
        System.out.println("Statistc after interventory: ");
         
        this.statistic();
    }

    /*
     * @param option int
     */
    public void action_option(int option ){
        Scanner scanner =new Scanner(System.in);
        if (option==3){
            return ;
        }
        if(option==1){
            boolean check=false;
            String des;
            while (!check){
                System.out.println("Enter 2 location and amount bilby to relocate");

                int location_num1=Integer.valueOf(InputValidator.get_input("^([0-9]*)$", "1st location (from):"));
                int location_num2=Integer.valueOf(InputValidator.get_input("^([0-9]*)$", "2nd location (to):"));
                

                int amount=Integer.valueOf(InputValidator.get_input("^([0-9]*)$", "Amount: "));
                check=this.relocate_2_Location(location_num1, location_num2, amount)&& (location_num1!=location_num2);
                if (! check){

                    des=InputValidator.get_input("^(y|Y|n|N)$","Invalid input, do you want to try another(Y-N) ? ").toLowerCase();
                    if (des.equals("n")){
                        check=true;
                    }
                }
                else{
                    System.out.println("Statistic after relocate");
                    this.statistic();
                    // System.out.println("");
                    des=InputValidator.get_input("^(y|Y|n|N)$","Done relocation, do you want to try another(Y-N) ? ","Invalid input!");
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
        }
    }

    public void limit_bilby(){
        for (Location Location: this.list_locations){
            Location.limit_bilby(this.limit_bilby);
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
            for(Location Location: this.list_locations){
                Location.update_status();
                System.out.println(String.format("-------Location %dth-------",Location.location_number+1));

                System.out.println("Alive");

                System.out.println(String.format("bilby: %d, cat: %d, fox: %d", Location.num_bilby,Location.num_cat,Location.num_fox));

                System.out.println("New born");

                System.out.println(String.format("bilby: %d, cat: %d, fox: %d", Location.born_bilby,Location.born_cat,Location.born_fox));

                System.out.println("Have died");

                System.out.println(String.format("bilby: %d, cat: %d, fox: %d", Location.dead_bilby,Location.dead_cat,Location.dead_fox));
                
                myWriter.write(String.format("%d, %d, %d, %d, %d, %d \n", Location.num_bilby, Location.dead_bilby, 
                Location.num_fox,Location.dead_fox,Location.num_cat, Location.dead_cat));
                
                total_bilby_end+=Location.num_bilby;
                total_new_birth_bilby+=Location.list_bilby.size()-Location.init_bilby;
                total_death_bilby+=Location.dead_bilby;
                total_init_bilby+=Location.init_bilby;
                total_init_predator+=Location.init_fox+Location.init_cat;
                total_predator_end+=Location.num_cat+Location.num_fox;
            }       

            System.out.println("============ Bilby population change =========");
             
            System.out.println(String.format("Bilby at the start: %d, end: %d, change rate: %d%%", this.total_bilby_start,total_bilby_end,100*(total_bilby_end-this.total_bilby_start)/this.total_bilby_start));
            
            System.out.println("============ Bilby population stability factor =========");

            System.out.println(String.format("BilBy new birth: %d, dead: %d stability factor %d%%",total_new_birth_bilby,total_death_bilby, 100*(total_new_birth_bilby+total_death_bilby)/total_init_bilby));

            System.out.println("============ Predator population change =========");
           
            System.out.println(String.format("Predator at the end: %d, start: %d change rate: %d%%",total_predator_end,total_init_predator,100*(total_predator_end-total_init_predator)/total_init_predator));
            
            myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        return ;
    }

    public void run_simulation() {
        boolean continue_simulate =true;
        String descision="y";
        for (Location Location : this.list_locations) {
            Location.init_animal();
        }
        System.out.println("=========Start simulation===========");
        
        System.out.println("Polulation at the begining");
        this.statistic();

        for (int k = 0; k < 12; k++) {
            for (int i = 0; i < this.num_locations; i++) {
                Location Location = this.list_locations[i];
                Location.one_month_process();
                Location.update_status();
                
            }
            System.out.println(String.format("=========Month %d=========",k+1));
            this.statistic();
            for (int i = 0; i < this.num_locations; i++) {
                Location Location = this.list_locations[i];
                if (Location.num_bilby>this.limit_bilby){
                    System.out.println(String.format("WARNING: Location %d have %d bilbies which is excess limit(%d)", i+1,Location.num_bilby,this.limit_bilby));
                }
            }

 
            if (descision.toLowerCase().equals("n")){
                continue_simulate=false;
            }
            else{
                boolean move_on=false;
                while (! move_on){
                    boolean valid_choice=false;
                    while (! valid_choice){
                        System.out.println("Select following options:");
                        System.out.println("1.Relocate bilby between 2 location");
                        System.out.println("2.Conduct interventory");
                        System.out.println("3.Pass (move to next month)");
                        int option=Integer.valueOf(InputValidator.get_input("^([1-3])$", "Your selection [1-3]: "));
                        if (option==1 ||option==3 ||option==2){
                            this.action_option(option);
                            valid_choice=true;
                            if (option==3){
                                move_on=true;
                            }
                        }
                        else{
                            valid_choice=false;
                            System.out.println("Not a valid input");
                        }
                    }
                }
            
            }
            if (! continue_simulate){
                break;
            }
            this.limit_bilby();
            descision=InputValidator.get_input("^(y|Y|n|N)$","Continue simulation (Y-N)? ").toLowerCase();

        }
        this.finalize();
    }

}

