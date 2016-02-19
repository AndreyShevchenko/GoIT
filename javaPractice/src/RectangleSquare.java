/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class RectangleSquare {
    private int[] widthValues = new int[2];

    private void getTotalWidth(int[] x, int[] w) {
        widthValues[0] = x[0];
        widthValues[1] = x[0] + w[0];
        for (int i = 1; i < x.length; i++) {
            if (x[i] < widthValues[0]) widthValues[0] = x[i];
            if (widthValues[1] < (x[i] + w[i])) widthValues[1] = x[i] + w[i];
        }
    }

    private int[] getTotalHeight(int[] x, int[] h, int[] w) {
        int heightValues[] = new int[widthValues[1] - widthValues[0] + 1];
        int offset = widthValues[0];
        for (int i = 0; i < x.length; i++) {
            for (int j = x[i]; j <= x[i] + w[i]; j++) {
                if (heightValues[j - offset] < h[i]) heightValues[j - offset] = h[i];
            }
        }
        return heightValues;
    }

    public int measure(int[] x, int[] h, int[] w) {
        int result = 0;
        getTotalWidth(x, w);
        int[] heightValues = getTotalHeight(x, h, w);
        for (int i = widthValues[0]; i < widthValues[1]; i++) {
            if (heightValues[i] <= heightValues[i + 1]) {
                result += heightValues[i];
            } else {
                result += heightValues[i + 1];
            }
        }
        return result;
    }
}
