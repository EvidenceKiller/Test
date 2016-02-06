/**
 * Created by user on 16-1-12.
 */
public class test {
    public static void main(String[] args) {
        String temp = "7.7";
        double d = Double.parseDouble(temp);
        System.out.println("double : " + d);
        int i1 = (int) d;
        System.out.println("int1 : " + i1);
        int i2 = (int) Math.round(d);
        System.out.println("int2 : " + i2);
    }
}
