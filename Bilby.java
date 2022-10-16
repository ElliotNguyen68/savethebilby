import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*
* A mode Bilby
*/
public class Bilby extends Animal{

    /*
     * @param id String
     * @return boolean
     */

    public Bilby(){
        this.id=String.format("B%03d",  Bilby.id_int);
        this.is_alive = true;
        this.set_giving_birth_prob(0.15);
    }
    
    /*
     * @param giving_birth_prob douple
     */
    public Bilby(double giving_birth_prob){
        this.set_giving_birth_prob(giving_birth_prob);
        this.id=String.format("B%03d",  Bilby.id_int);
        this.is_alive = true;
    }

    /*
     * @param id String
     */
    public void setId(String id){
        assert check_id(id,"^B[0-9]{3}$"):"Invalid id";
        this.id=id;
    }

    @Override
    public String toString() {
        return (String.format("Bilby, id: %s, is_alive: %b, giving_birth_prob: %,.3f", this.id, this.is_alive,this.giving_birth_prob));
    }
}