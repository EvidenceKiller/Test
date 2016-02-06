import java.util.ArrayList;

/**
 * User : user
 * Date : 2016-01-22
 * Time : 11:41
 */
public class test1 {

    public static void main(String[] args) {
        ArrayList<String> stringList = new ArrayList<String>();
//        stringList.add(0, "1");
        stringList.add(1, "2");
        stringList.add(2, "3");
        System.out.println("stringList : " + stringList.toString());
        stringList.add(0, "4");
        System.out.println("stringList : " + stringList.toString());
    }
}
