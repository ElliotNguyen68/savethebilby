/*
Predator inherit from Bilby, so Predator object can use method and attribute of Bilby class, furthermore, it has it's own method and attribute.
A mode Predator
 */
class Predator extends Animal {
    public int health;
    public double eat_prob;
    public String type;

    /*
     * @param type String
     */
    public Predator(String type) {
        assert (type =="Fox" | type =="Cat");
        if (type == "Fox") {
            // this.id = id;
            this.giving_birth_prob = 0.1;
            this.eat_prob = 0.4;
            this.id=String.format("F%03d", Predator.id_int);
        } else {
            // this.id = id;
            this.giving_birth_prob = 0.2;
            this.eat_prob = 0.6;
            this.id=String.format("C%03d", Predator.id_int);

        }
        this.type = type;
        this.health = 3;
    }
    
    public int getHealth() {
        return health;
    }

    public double getEat_prob() {
        return eat_prob;
    }

    public String getType() {
        return type;
    }

    /*
     * @param type String
     * @param giving_birth_prob double
     * @param eat_prob double
     */
    public Predator(String type,double giving_birth_prob,double eat_prob) {
        assert (type =="Fox" | type =="Cat");
        if (type == "Fox") {
            this.id=String.format("F%03d", Predator.id_int);
        } else {
            this.id=String.format("C%03d", Predator.id_int);

        }
        this.set_giving_birth_prob(giving_birth_prob);
        assert(eat_prob>=0 && eat_prob<=1) :"Not a valid giving eat probability!" ; 
        this.eat_prob=eat_prob;
        this.type = type;
        this.health = 3;
    }

    
    public boolean eatable_now() {
        // this funciton will check for an animal will eat in this month
        // check for living status
        if (this.is_alive == false) {
            return false;
        }
        // if alive check probability eat
        return Math.random() <= this.eat_prob;
    }

     /*
     * @param increase boolean
     */
    public void update_health(boolean increase) {
        // this function will update health of predator
        // if not alive return
        if (!this.is_alive) {
            return;
        }
        if (increase == true) {
            this.health += 1;
            return;
        }
        this.health -= 1;
        if (this.health <= 0) {
            this.is_alive = false;
        }
    }

    @Override
    public String toString() {
        return (String.format("type: %s, id: %s, is_alive: %b, health: %d", this.type, this.id, this.is_alive,
                this.health));
    }
    public void die_by_haft(){
        if (Math.random()<0.5){
            this.is_alive=false;
        }
    }

    // Code for test
    public static void main(String[] args) {
        Predator a=new Predator("Cat");
        System.out.println(a);
        Predator b=new Predator("Cat");
        System.out.println(b);
        Predator c=new Predator("Fox");
        System.out.println(c);
        Animal e=new Bilby();
        System.out.println(e);
    }
}