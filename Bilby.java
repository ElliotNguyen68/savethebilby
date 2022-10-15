import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Bilby extends Animal{

    boolean check_id(String id){
        Pattern pattern=Pattern.compile("^B[0-9]{3}$");
        Matcher matcher = pattern.matcher(id);
        boolean found=matcher.find();
        if (! found){
            return false;
        }
        else{
            return true;
        }
    }

    public Bilby(){
        this.id=String.format("B%03d",  Bilby.id_int);
        this.is_alive = true;
        this.set_giving_birth_prob(0.15);
    }
    
    public Bilby(double giving_birth_prob){
        this.set_giving_birth_prob(giving_birth_prob);
        this.id=String.format("B%03d",  Bilby.id_int);
        this.is_alive = true;
    }


    @Override
    public String toString() {
        return (String.format("Bilby, id: %s, is_alive: %b, giving_birth_prob: %,.3f", this.id, this.is_alive,this.giving_birth_prob));
    }

    private void test(){
        System.out.println("Create a Bilby object with the default constructor");
        Bilby b1=new Bilby();
        System.out.println(b1);


        System.out.println("Create a Bilby object with the non-default constructor with valid field values");
        Bilby b2=new Bilby(0.14);
        System.out.println(b2); 


        System.out.println("Create a Bilby object with the non-default constructor with invalid field values");
        try {
            Bilby b5=new Bilby(1.4);
        } catch (AssertionError e) {
            System.out.println("Fail to init object  ");
            // TODO: handle exception
        }

    

        System.out.print("Test get_id() method: ");
        System.out.println(b1.getId());
        
        System.out.print("Test get_giving_birth_prob() method: ");
        System.out.println(b1.getGiving_birth_prob());

        System.out.print("Test set_giving_birth_prob() method with valid value: ");
        b1.set_giving_birth_prob(.19);
        System.out.println(b1.getGiving_birth_prob());

        Bilby b3=new Bilby();

        System.out.print("Test set_giving_birth_prob() method with invalid value: ");
    
        try {
            b3.set_giving_birth_prob(1.1);
        } catch (AssertionError e) {
            System.out.println("Fail to set_giving_birth_prob ");
            // TODO: handle exception
        }

        System.out.println("Test format id of bilby:");

        Pattern pattern=Pattern.compile("^B[0-9]{3}$");

        Matcher matcher = pattern.matcher(b3.getId());
        assert matcher.find(): "Fail in id";
        System.out.println("Right format bilby");
    }

    public static void main(String[] args) {
        Bilby bilby=new Bilby();
        bilby.test();
         
        }
}