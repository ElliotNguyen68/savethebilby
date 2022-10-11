
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Bilby extends Animal {
    static int id_int=0;

    boolean check_id(String id){
        Pattern pattern=Pattern.compile("^(B|F|C)[0-9]{3}$");
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
        int tmp_id =Bilby.id_int+1;
        Bilby.id_int+=1;
        this.id=String.format("B%03d",  tmp_id);
        this.is_alive = true;
        this.giving_birth_prob = 0.15; 
    }

    public Bilby(String predator_type,int id_id){
        if (predator_type=="Fox"){
            this.id=String.format("F%03d",id_id);
        }
        else{
            this.id=String.format("C%03d",id_id);
        }
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


    @Override
    boolean giving_birth_now() {
        // TODO Auto-generated method stub
        if (this.is_alive == false) {
            return false;
        }
        return Math.random() <= this.giving_birth_prob;
    }

    public String toString() {
        return (String.format("id: %s, is_alive: %b, giving_birth_prob: %,.3f", this.id, this.is_alive,this.giving_birth_prob));
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
    public void set_giving_birth_prob(double giving_birth_prob){
        if(giving_birth_prob<0| giving_birth_prob>1){
            // assertTrue("Not a valid giving birth probability!", false);
            assert false :"Not a valid giving birth probability!" ;
 
        }  ; 
        this.giving_birth_prob=giving_birth_prob;
    }

    public static void main(String[] args) {
        Bilby bilby_0=new Bilby();
        System.out.println(bilby_0);
        Bilby bilby_2=new Bilby();
        System.out.println(bilby_2);
         
        // Bilby bilby_1=new Bilby("B123");
        // System.out.println(bilby_1);
        // Bilby bilby_2=new Bilby("B123", 0.9);
        // System.out.println(bilby_2);
    }
}