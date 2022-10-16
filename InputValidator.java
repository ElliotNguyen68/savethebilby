import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InputValidator {
    /*
     * @param valid_input_regex String
     * @param enter_sentence String
     * @param error_message String
     * @retrun String
     */
    public static String get_input(String valid_input_regex,String enter_sentence,String error_message){
        // this function aim to validate input of user, must satisfy a regex codition,
        //  enter_sentence will be displayed as guildline what user need to enter.
        boolean check=false;
        String input="";
        Scanner scanner=new Scanner(System.in);
        while (! check){
            if (enter_sentence.equals("")==false){
                System.out.println(enter_sentence);
            }
            input=scanner.next();
            Pattern pattern=Pattern.compile(valid_input_regex);
            Matcher matcher = pattern.matcher(input);
            boolean found=matcher.find();
            if (! found){
                System.out.println(error_message);
            }
            else{
                check=true;
            }
        }
        return input;
    }

    /*
     * @param valid_input_regex String
     * @param enter_sentence String
     * @retrun String
     */
    public static String get_input(String valid_input_regex,String enter_sentence){
        // this function aim to validate input of user, must satisfy a regex codition,
        //  enter_sentence will be displayed as guildline what user need to enter.
        boolean check=false;
        String input="";
        Scanner scanner=new Scanner(System.in);
        while (! check){
            if (enter_sentence.equals("")==false){
                System.out.println(enter_sentence);
            }
            input=scanner.next();
            Pattern pattern=Pattern.compile(valid_input_regex);
            Matcher matcher = pattern.matcher(input);
            boolean found=matcher.find();
            if (! found){
                System.out.println("Invalid input, please type in again !!!");
            }
            else{
                check=true;
            }
        }
        return input;
    }
}