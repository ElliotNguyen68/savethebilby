import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Abstract class for Animal, contain code for animals
 */
abstract class Animal {
    // using this static variable to create incremental id, whenever an object belong to Bilby class or extended from Bilby class is instanced, this variable will increased by 1.
    static int id_int=0;

    String id;
    public boolean is_alive;
    public double giving_birth_prob;

    public Animal(){
        Animal.id_int+=1;
        this.is_alive=true;
    }
     
    void force_die() {
        this.is_alive = false;
    }


    boolean giving_birth_now() {
        // TODO Auto-generated method stub
        if (this.is_alive == false) {
            return false;
        }
        return Math.random() <= this.giving_birth_prob;
    }

    public String getId() {
        return id;
    }

    public boolean isIs_alive() {
        return is_alive;
    }

    public double getGiving_birth_prob() {
        return giving_birth_prob;
    }

    /*
     * @param is_alive boolean
     */
    public void set_is_alive(boolean is_alive){
        this.is_alive=is_alive;
    }
    /*
     * @param is_alive boolean
     */
    boolean check_id(String id,String pattern){
        Pattern pattern_=Pattern.compile(pattern);
        Matcher matcher = pattern_.matcher(id);
        boolean found=matcher.find();
        if (! found){
            return false;
        }
        else{
            return true;
        }
    }

    /*
     * @param giving_birth_prob double
     */
    public void set_giving_birth_prob(double giving_birth_prob){
        assert(giving_birth_prob>=0 && giving_birth_prob<=1) :"Not a valid giving birth probability!" ; 
        this.giving_birth_prob=giving_birth_prob;
    }
}