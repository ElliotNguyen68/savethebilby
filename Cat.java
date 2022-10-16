// Cat will have all attribute and method of Predator
public class Cat extends Predator{
    public Cat(){
        super("Cat");
    }

    /*
     * @param giving_birth_prob double
     * @param eat_prob double
     */
    public Cat(double giving_birth_prob,double eat_prob){
        super("Cat",giving_birth_prob,eat_prob);
    }

    @Override
    public String toString() {
        return (String.format("Cat, id: %s, is_alive: %b, health: %d", this.id, this.is_alive,
                this.health));
    }

    // Code for run test
    public static void main(String[] args) {
        Cat a=new Cat();
        System.out.println(a);
        Fox b=new Fox();
        System.out.println(b);
    }
}