public class Cat extends Predator{
    public Cat(){
        super("Cat");
    }
    public Cat(double giving_birth_prob,double eat_prob){
        super("Cat",giving_birth_prob,eat_prob);
    }

    @Override
    public String toString() {
        return (String.format("Cat, id: %s, is_alive: %b, health: %d", this.id, this.is_alive,
                this.health));
    }
    public static void main(String[] args) {
        Cat a=new Cat();
        System.out.println(a);
        Fox b=new Fox();
        System.out.println(b);
        
    }
}