import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CipherVigenere implements Serializable {
    private StringBuilder originalText;
    private StringBuilder wordKey;
    private Set<Character> smallLetters;
    private Set<Character> bigLetters;
    private byte[] keyToAscii;

    public void setData(String originalText, String wordKey) {
        this.originalText = new StringBuilder(originalText);
        this.wordKey = new StringBuilder(wordKey);
        smallLetters = new HashSet<>();
        bigLetters = new HashSet<>();
        for (int i = 65; i < 123; i++) {
            if (i < 91) {
                bigLetters.add((char) i);
            }
            if (i > 96) {
                smallLetters.add((char) i);
            }
        }
        getKey();
    }

    // метод, который возвращает массив значений сдвига для шифрования текста
    // в отличии от метода Цезаря, этот шифр невозможно разгадать не зная слово-ключ
    private void getKey() {
        for (int i = 0; i < wordKey.length(); i++) {
            if (!(smallLetters.contains(wordKey.charAt(i)) || bigLetters.contains(wordKey.charAt(i)))) {
                wordKey.deleteCharAt(i);
                i--;
            }
        }
        keyToAscii = wordKey.toString().toUpperCase().getBytes();
        for (int i = 0; i < keyToAscii.length; i++) {
            keyToAscii[i] -= 65;
        }
    }

    private boolean isWordKeyMissing() {
        if (wordKey.length() == 0) {
            System.out.println("[Error:] missing word-key, encryption impossible");
        }
        return wordKey.length() == 0;
    }

    public String encrypt() {
        if (isWordKeyMissing()) return originalText.toString();
        int temp;
        int flag;
        for (int i = 0; i < originalText.length(); i++) {
            flag = 0;
            if (smallLetters.contains(originalText.charAt(i))) {
                flag = 122;
            }
            if (bigLetters.contains(originalText.charAt(i))) {
                flag = 90;
            }
            if (flag > 0) {
                temp = originalText.codePointAt(i);
                temp += keyToAscii[i % wordKey.length()];
                if (temp > flag) {
                    temp -= 26;
                }
                originalText.deleteCharAt(i);
                originalText.insert(i, (char) temp);
            }
        }
        return originalText.toString();
    }

    public String decrypt() {
        if (isWordKeyMissing()) return originalText.toString();
        for (int i = 0; i < keyToAscii.length; i++) {
            keyToAscii[i] = (byte) (26 - keyToAscii[i]);
        }
        encrypt();
        return originalText.toString();
    }

}
