import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Zone {
    int zone_number;
    int num_fox;
    int num_cat;
    int num_bilby;
    int num_cat_so_far;
    int num_bilby_so_far;
    int num_fox_so_far;
    int init_bilby;
    int init_fox;
    int init_cat;
    ArrayList<Animal> list_bilby = new ArrayList<Animal>();
    ArrayList<Animal> list_cat = new ArrayList<Animal>();
    ArrayList<Animal> list_fox = new ArrayList<Animal>();

    public Zone(int num_bilby, int num_cat, int num_fox, int zone_number) {
        // alive
        this.num_bilby = num_bilby;
        this.num_fox = num_fox;
        this.num_cat = num_cat;
        this.zone_number = zone_number;
        // alive + dead + new born
        this.num_bilby_so_far = this.num_bilby;
        this.num_fox_so_far = this.num_fox;
        this.num_cat_so_far = this.num_cat;
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
            Predator new_cat = new Predator("Cat");
            this.list_cat.add(new_cat);
        }
        for (int i = 0; i < this.num_fox; i++) {
            Predator new_fox = new Predator("Fox");
            this.list_fox.add(new_fox);
        }

    }

    public void update_status() {
        // this function will update number of alive individual per species, after a month
        int num_bilby = 0;
        for (Animal animal : this.list_bilby) {
            if (animal.is_alive) {
                num_bilby += 1;
            }
        }
        this.num_bilby = num_bilby;

        int num_cat = 0;
        for (Animal animal : this.list_cat) {
            if (animal.is_alive) {
                num_cat += 1;
            }
        }
        this.num_cat = num_cat;

        int num_fox = 0;
        for (Animal animal : list_fox) {
            if (animal.is_alive) {
                num_fox += 1;
            }
        }
        this.num_fox = num_fox;

    }

    public boolean check_remain_bilby() {
        // check remain bilby in zone
        return this.num_bilby > 0;
    }

    public void generate_new_animal(String type, int number) {
        // Given animal type, with a number of new one, add new #number animals of type to list animal of this zone.
        ArrayList<Animal> tmp;
        if (type == "Bilby") {
            tmp = this.list_bilby;
            for (int i = 0; i < number; i++) {
                Animal new_bilby = new Bilby();
                this.list_bilby.add(new_bilby);
            }
            this.num_bilby_so_far += number;
            return;
        }
        int number_this_type_so_far;
        if (type == "Fox") {
            number_this_type_so_far = this.num_fox_so_far;
            tmp = this.list_fox;
        } else {
            tmp = this.list_cat;
            number_this_type_so_far = this.num_cat_so_far;
        }
        for (int i = 0; i < number; i++) {
            Animal new_type = new Predator(type);
            tmp.add(new_type);
        }
        number_this_type_so_far += number;
        if (type == "Fox") {
            this.num_fox_so_far = number_this_type_so_far;
        } else {
            this.num_cat_so_far = number_this_type_so_far;
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
        }
        if (num_new_cat > 0) {
            this.generate_new_animal("Cat", num_new_cat);
        }
        if (num_new_fox > 0) {
            this.generate_new_animal("Fox", num_new_fox);
        }
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
        for (Animal animal : list_predator) {
            ((Predator)animal).update_health(false);
        }
    }

    public void one_month_process() {
        this.giving_birth();
        this.update_status();
        this.predator_hunting();
        this.update_health_all_predators();
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
        } 
    }
    
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
            this.update_status();
        }
    }
}
