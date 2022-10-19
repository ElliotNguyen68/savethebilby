import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/*
* A mode Location
*/
class Location {
    int location_number;
    int num_fox;
    int num_cat;
    int num_bilby;

    int born_bilby;
    int born_fox;
    int born_cat;

    int dead_bilby;
    int dead_fox;
    int dead_cat;
    
    int init_bilby;
    int init_fox;
    int init_cat;
    ArrayList<Animal> list_bilby = new ArrayList<Animal>();
    ArrayList<Animal> list_cat = new ArrayList<Animal>();
    ArrayList<Animal> list_fox = new ArrayList<Animal>();

    private int death_this_month_bilby=0;
    private int death_this_month_cat=0;
    private int death_this_month_fox=0;

    private int born_this_month_bilby=0;
    private int born_this_month_cat=0;
    private int born_this_month_fox=0;
    /*
     * @param num_bilby int
     * @param num_cat int
     * @param num_fox int
     * @param zone_number int
     */

    private void reset_status(){
        this.death_this_month_bilby=0;
        this.death_this_month_fox=0;
        this.death_this_month_cat=0;
        this.born_this_month_fox=0 ;
        this.born_this_month_bilby=0 ;
        this.born_this_month_cat=0 ;
    }

    public Location(int num_bilby, int num_cat, int num_fox, int zone_number) {
        // alive
        this.num_bilby = num_bilby;
        this.num_fox = num_fox;
        this.num_cat = num_cat;
        this.location_number = zone_number;
        
        // dead
        this.dead_bilby = num_bilby;
        this.dead_fox = num_fox;
        this.dead_cat = num_cat;

        // init-start
        this.init_bilby=num_bilby;
        this.init_cat=num_cat;
        this.init_fox=num_fox;
    }

    public void init_animal() {
        // this function aim to init animal in zone.
        for (int i = 0; i < this.num_bilby; i++) {
            Animal new_bilby = new Bilby();
            this.list_bilby.add(new_bilby);
        }
        for (int i = 0; i < this.num_cat; i++) {
            Predator new_cat = new Cat();
            this.list_cat.add(new_cat);
        }
        for (int i = 0; i < this.num_fox; i++) {
            Predator new_fox = new Fox();
            this.list_fox.add(new_fox);
        }

    }

    public void update_status() {
        // this function will update number of alive individual per species, after a month
        int num_bilby = 0;
        int num_bilby_dead = 0;
        for (Animal animal : this.list_bilby) {
            if (animal.is_alive) {
                num_bilby += 1;
            }
            else{

                num_bilby_dead+=1;
            }
        }
        this.dead_bilby=num_bilby_dead;
        this.num_bilby = num_bilby;

        int num_cat = 0;
        int num_cat_dead=0;
        for (Animal animal : this.list_cat) {
            if (animal.is_alive) {
                num_cat += 1;
            }
            else{
                num_cat_dead+=1;
            }
        }
        this.dead_cat=num_cat_dead;
        this.num_cat = num_cat;

        int num_fox = 0;
        int num_fox_dead = 0;
        for (Animal animal : list_fox) {
            if (animal.is_alive) {
                num_fox += 1;
            }
            else{
                num_fox_dead+=1;
            }
        }
        this.dead_fox=num_fox_dead;
        this.num_fox = num_fox;

    }

    public boolean check_remain_bilby() {
        // check remain bilby in zone
        return this.num_bilby > 0;
    }
    
    /*
     * @param type String
     * @param number int
     */
    public void generate_new_animal(String type, int number) {
        // Given animal type, with a number of new one, add new #number animals of type to list animal of this zone.
        ArrayList<Animal> tmp ;
        if (type == "Bilby") {
            tmp = this.list_bilby;
            for (int i = 0; i < number; i++) {
                Animal new_bilby = new Bilby();
                this.list_bilby.add(new_bilby);
            }
            // this.num_bilby_so_far += number;
            return;
        }
        if (type == "Fox") {
            tmp = this.list_fox;
        } else {
            tmp = this.list_cat;
        }
        
        for (int i = 0; i < number; i++) {
            Animal new_type = new Predator(type);
            tmp.add(new_type);
        }
    }

    public void giving_birth() {
        // Giving birth process, once a month, for all species in zone, 
        // Go through every specie, every individual and check for their giving-birth ability.
        int num_new_bilby = 0;
        for (int i = 0; i < this.list_bilby.size(); i++) {
            if (this.list_bilby.get(i).giving_birth_now()) {
                num_new_bilby += 1;
            }
        }
        int num_new_cat = 0;
        for (int i = 0; i < this.list_cat.size(); i++) {
            if (this.list_cat.get(i).giving_birth_now()) {
                num_new_cat += 1;
            }
        }
        int num_new_fox = 0;
        for (int i = 0; i < this.list_fox.size(); i++) {
            if (this.list_fox.get(i).giving_birth_now()) {
                num_new_fox += 1;
            }
        }
        if (num_new_bilby > 0) {
            this.generate_new_animal("Bilby", num_new_bilby);
            this.born_bilby+=num_new_bilby;
        }
        if (num_new_cat > 0) {
            this.generate_new_animal("Cat", num_new_cat);
            this.born_cat+=num_new_cat;
        }
        if (num_new_fox > 0) {
            this.generate_new_animal("Fox", num_new_fox);
            this.born_fox+=num_new_fox;
        }
        // System.out.println(String.format("Location %d, new born bilby %d, new born cat %d, new born fox %d", this.location_number+1,num_new_bilby,num_new_cat,num_new_fox));
        this.born_this_month_bilby=num_new_bilby;
        this.born_this_month_cat=num_new_cat;
        this.born_this_month_fox=num_new_fox;
    }

    public void predator_hunting() {
        // Let predators in zone hunting 
        // check for remain bilby, if no bilby left , return
        if (this.check_remain_bilby() == false) {
            return;
        }
        // create a list of predators(cat-fox) after shuffle, so make sure randomess in which ones can eat
        ArrayList<Animal> list_predator = new ArrayList<>();
        list_predator.addAll(this.list_cat);
        list_predator.addAll(this.list_fox);
        Collections.shuffle(list_predator, new Random(3));
        int num_remain_bilby = this.num_bilby;
        int num_eat_bilby = 0;
        for (Animal animal : list_predator) {
            if (((Predator)animal).eatable_now()) {
                if (num_eat_bilby < num_remain_bilby) {
                    num_eat_bilby += 1;
                    ((Predator) animal).update_health(true);
                }
            }
        }
        // if number of bilby got ate > 0, go to list bilby, kill these bilby
        // System.out.print(String.format("Num death bilby: %d, ",num_eat_bilby));
        this.death_this_month_bilby+=num_eat_bilby;
        if (num_eat_bilby > 0) {
            for (Animal bilby : this.list_bilby) {
                if (bilby.is_alive) {
                    bilby.is_alive = false;
                    num_eat_bilby -= 1;
                }
                if (num_eat_bilby <= 0) {
                    break;
                }
            }
        }
    }

    public void update_health_all_predators() {
        ArrayList<Animal> list_predator = new ArrayList<>();
        list_predator.addAll(this.list_cat);
        list_predator.addAll(this.list_fox);
    
        int num_die_cat=0;
        int num_die_fox=0;

        for (Animal animal : list_predator) {
            ((Predator)animal).update_health(false);
            if (((Predator)animal).health==0){
                if (((Predator)animal).type=="Cat"){
                    num_die_cat+=1;
                }
                else{
                    num_die_fox+=1;
                }
            }
        }
        this.death_this_month_cat+=num_die_cat;
        this.death_this_month_fox+=num_die_fox;
        // System.out.print(String.format("num death cat: %d, ", num_die_cat));
        // System.out.println(String.format("num death fox: %d", num_die_fox));
    }

    public void one_month_process() {
        this.death_this_month_bilby=0;
        this.death_this_month_cat=0;
        this.death_this_month_fox=0;

        this.giving_birth();
        this.update_status();
        this.predator_hunting();
        this.update_health_all_predators();
        this.update_status();
    }

    public void write_status() {
        for (Animal animal : list_bilby) {
            System.out.println(animal);
        }
        for (Animal animal : list_fox) {
            System.out.println(animal);
        }
        for (Animal animal : list_cat) {
            System.out.println(animal);
        }
    }

    public void die_by_haft_predators(){
        ArrayList<Animal> list_predator = new ArrayList<>();
        list_predator.addAll(this.list_cat);
        list_predator.addAll(this.list_fox);
        for (Animal animal : list_predator) {
            ((Predator)animal).die_by_haft();
            if (((Predator)animal).is_alive==false){
                if (((Predator)animal).type=="Cat"){
                    this.death_this_month_cat+=1;
                } 
                else{
                    this.death_this_month_fox+=1;
                }
            }
        } 
    }
    
    /*
     * @param lim_bilby int
     */
    public void limit_bilby(int lim_bilby){
        this.update_status();

        if (this.num_bilby>lim_bilby){
            int diff=this.num_bilby-lim_bilby;
            for(Animal animal :this.list_bilby) {
                if (diff>0 && animal.is_alive){
                    animal.force_die();
                    diff-=1;
                }
                if (diff<=0){
                    break;
                }
            }
            System.out.println(String.format("All %d excess bilbies in location %d  have died", this.num_bilby-lim_bilby,this.location_number+1));
            this.update_status();
        }
    }

    public void show_born_dead(){
        System.out.println(String.format("Location %d",this.location_number+1));
        System.out.println(String.format("New born - bilby: %d, cat: %d, fox: %d",this.born_this_month_bilby,this.born_this_month_cat,this.born_this_month_fox));
        System.out.println(String.format("New dead - bilby: %d, cat: %d, fox: %d",this.death_this_month_bilby,this.death_this_month_cat,this.death_this_month_fox));
        }

    @Override
    public String toString() {
        return String.format("Location %d, bilby: %d, cat: %d, fox: %d", this.location_number+1,this.num_bilby,this.num_cat,this.num_fox);
    }
}
