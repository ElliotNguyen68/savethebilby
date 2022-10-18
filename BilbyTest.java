import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*
* A BilbyTest
*/
public class BilbyTest {
    private void test_create_by_non_default() {
        System.out.println("Create a Bilby object with the non-default constructor with valid field values");
        Bilby b2=new Bilby(0.15);
        System.out.println(b2); 
    }
    
    private void test_get_method() {
        System.out.println("Create a Bilby object with the default constructor");
        Bilby b1=new Bilby();
        System.out.println(b1);
        System.out.print("Test get_id() method: ");
        System.out.println(b1.getId());
        
        System.out.print("Test get_giving_birth_prob() method: ");
        System.out.println(b1.getGiving_birth_prob());

        System.out.print("Test set_giving_birth_prob() method with valid value: ");
        b1.set_giving_birth_prob(.15);
        System.out.println(b1.getGiving_birth_prob());

        System.out.print("Test setId() method with valid value: ");
        b1.setId("B101");
        System.out.println(b1.getId());

         

    }

    private void test_create_by_default(){
        System.out.println("Create a Bilby object with the default constructor");
        Bilby b1=new Bilby();
        System.out.println(b1);
    }

    private void test_nagative_set(){
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

        System.out.println("Test set id with invalid format");

        try {
            b3.setId("dfkj");

        } catch (AssertionError e) {
            // TODO: handle exception
            System.out.println("Invalid format");
        }

        
    }

    private void test_nagative_create_by_invalid_non_default() {
        System.out.println("Create a Bilby object with the non-default constructor with invalid field values");
        try {
            Bilby b5=new Bilby(1.4);
        } catch (AssertionError e) {
            System.out.println("Fail to init object");
            // TODO: handle exception
        }
    }

    // Code for run test
    public static void main(String[] args) {
        BilbyTest bilby=new BilbyTest();
        // test positive
        bilby.test_create_by_default();
        bilby.test_create_by_non_default();
        bilby.test_get_method();
        // test nagative
        bilby.test_nagative_create_by_invalid_non_default();
        bilby.test_nagative_set();
    }
}