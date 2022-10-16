// Fox will have all attribute and method of Predator

public class Fox extends Predator{
    public Fox(){
        super("Fox");
    }
    
    public void setId(String id){
        assert check_id(id,"^F[0-9]{3}$"):"Invalid id";
        this.id=id;
    }


    /*
     * @param giving_birth_prob douple
     * @param eat_prob douple
     */
    public Fox(double giving_birth_prob,double eat_prob){
        super("Fox",giving_birth_prob,eat_prob);
    }

    @Override
    public String toString() {
        return (String.format("Fox, id: %s, is_alive: %b, health: %d", this.id, this.is_alive,
                this.health));
    }
}