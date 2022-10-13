import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        this.giving_birth_prob = 0.15; 
    }
     
    public Bilby(String id) {
        // this will be constructor of bilby
        if (!this.check_id(id)){
            // assertTrue("Not a valid id!", false);
            assert false:"Not a valid id!" ;
        }
        this.id = id;
        this.is_alive = true;
        this.giving_birth_prob = 0.15;
    };

    public Bilby(String id, Double birth_prob){
        if (!this.check_id(id)){
            // assertTrue("Not a valid id!", false);
            assert false:"Not a valid id!" ;

        };
        if(birth_prob<0| birth_prob>1){
            // assertTrue("Not a valid giving birth probability!", false);
            assert false:"Not a valid giving birth probability!" ;

        }  ;
        this.id = id;
        this.is_alive = true;
        this.giving_birth_prob = birth_prob;
    };


    @Override
    void force_die() {
        this.is_alive = false;
    }


    public String toString() {
        return (String.format("Bilby, id: %s, is_alive: %b, giving_birth_prob: %,.3f", this.id, this.is_alive,this.giving_birth_prob));
    }

    public String get_id(){
        return this.id;
    }

    public boolean get_is_alive(){
        return this.is_alive;
    }

    public double get_giving_birth_prob(){
        return this.giving_birth_prob;
    }

    public void set_is_alive(boolean is_alive){
        this.is_alive=is_alive;
    }

    public void set_id(String id){
        if (!this.check_id(id)){
            // assertTrue("Not a valid id!", false);
            assert false: "Not a valid id!";
        }
        this.id=id; 
    }

    private void test(){
        System.out.println("Create a Bilby object with the default constructor");
        Bilby b1=new Bilby();
        System.out.println(b1);


        System.out.println("Create a Bilby object with the non-default constructor with valid field values");
        Bilby b2=new Bilby("B005",0.14);
        System.out.println(b2); 

        // System.out.println("Create a Bilby object with the non-default constructor with invalid field values");
        // Bilby b3=new Bilby("C005",0.14);

        // System.out.println("Create a Bilby object with the non-default constructor with invalid field values");
        // Bilby b4=new Bilby("C005",1.1);

        System.out.print("Test get_id() method: ");
        System.out.println(b1.get_id());
        
        System.out.print("Test get_giving_birth_prob() method: ");
        System.out.println(b1.get_giving_birth_prob());

        System.out.print("Test set_id() method with valid id: ");
        b1.set_id("B000");
        System.out.println(b1.get_id());

        System.out.print("Test set_giving_birth_prob() method with valid value: ");
        b1.set_giving_birth_prob(.19);
        System.out.println(b1.get_giving_birth_prob());

        Bilby b3=new Bilby();
        System.out.println(b3);
        // System.out.print("Test set_id() method with invalid id: ");
        // b1.set_id("C000");
        // System.out.println(b1.get_id());

        // System.out.print("Test set_giving_birth_prob() method with invalid value: ");
        // b1.set_giving_birth_prob(1.1);
        // System.out.println(b1.get_giving_birth_prob());


    }

    public static void main(String[] args) {
        Bilby bilby=new Bilby();
        bilby.test();
         
        // Bilby bilby_1=new Bilby("B123");
        // System.out.println(bilby_1);
        // Bilby bilby_2=new Bilby("B123", 0.9);
        // System.out.println(bilby_2);
        }
}