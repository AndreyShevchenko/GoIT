import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    private List<List<String>> output = new ArrayList<>();
    private List<String> substring = new ArrayList<>();
    private int indexBeginOfData;
    private String str;

    private int addToSubstring(int i, int indexEndOfData) {
        substring.add(str.substring(indexBeginOfData, indexEndOfData));
        return i + 1;
    }

    private void addToOutput() {
        output.add(substring);
        substring = new ArrayList<>();
    }

    public List<List<String>> parse(String input) {
        if (input == null || input.length() == 0) return null;
        str = input.replaceAll("\"\"", "\"");
        str = str.concat("\n");
        boolean isOpenQuotes = false;
        indexBeginOfData = 0;
        for (int i = 0; i < str.length(); i++) {
            char symbol = str.charAt(i);
            if (isOpenQuotes) {
                if ((symbol == ',' || symbol == '\n') && str.charAt(i - 1) == '"') {
                    isOpenQuotes = false;
                    indexBeginOfData = addToSubstring(i, i - 1);
                }
            } else {
                if (symbol == '"') {
                    isOpenQuotes = true;
                    indexBeginOfData = i + 1;
                }
                if (symbol == ',' || symbol == '\n') {
                    indexBeginOfData = addToSubstring(i, i);
                }
            }
            if (symbol == '\n' && !isOpenQuotes) {
                addToOutput();
            }
        }
        return output;
    }
}
