public class Fox extends Predator{
    public Fox(){
        super("Fox");
    }

    public Fox(double giving_birth_prob,double eat_prob){
        super("Fox",giving_birth_prob,eat_prob);
    }

    @Override
    public String toString() {
        return (String.format("Fox, id: %s, is_alive: %b, health: %d", this.id, this.is_alive,
                this.health));
    }
    // public Fox(g)
}