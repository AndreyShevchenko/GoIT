/**
 * Created by Андрей Шевченко on 05.02.2016.
 */
public class MainClass {

    public static void main(String[] args){
        int num = 13;
        int mask = 1;
        int temp;
        int result = 0;
        do {
            temp = num | mask;
            if (temp == num) {
                result += 1;
            }
            num = num >> 1;
        } while (num != 0);
        System.out.println(result);
    }
}
