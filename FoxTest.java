import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*
* A FoxTest
*/
public class FoxTest {
    private void test_create_fox() {
        System.out.println("Create a Fox");
        Fox f=new Fox();
        System.out.println(f.toString());
    }
    
    // Code for run test
    public static void main(String[] args) {
        FoxTest test=new FoxTest();
        // test positive
        test.test_create_fox();
    }
}