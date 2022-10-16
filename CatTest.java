import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*
* A CatTest
*/
public class CatTest {
    private void test_create_cat() {
        System.out.println("Create a Cat");
        Cat c=new Cat();
        System.out.println(c.toString());
    }
    
    // Code for run test
    public static void main(String[] args) {
        CatTest test=new CatTest();
        // test positive
        test.test_create_cat();
    }
}