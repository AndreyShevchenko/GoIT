/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class ReverseBits {

    public int reverse(int input) {
        boolean isEnd = false;
        int mask1 = 1;
        int mask2 = mask1 << 31;
        int result1;
        int result2;
        boolean isCheck1;
        boolean isCheck2;
        do {
            result1 = input | mask1;
            result2 = input | mask2;
            isCheck1 = (input == result1);
            isCheck2 = (input == result2);
            if (isCheck1 != isCheck2) {
                if (isCheck1) {
                    input = result2;
                    mask1 = ~mask1;
                    input = input & mask1;
                    mask1 = ~mask1;
                } else {
                    input = result1;
                    mask2 = ~mask2;
                    input = input & mask2;
                    mask2 = ~mask2;
                }
            }
            mask1 = mask1 << 1;
            if (mask1 == mask2) {
                isEnd = true;
            }
            mask2 = mask2 >>> 1;
        } while (!isEnd);
        return input;
    }
}
