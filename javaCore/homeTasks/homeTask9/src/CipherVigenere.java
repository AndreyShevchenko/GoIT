import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CipherVigenere implements Serializable {
    transient private Set<Character> smallLetters;
    transient private Set<Character> bigLetters;
    private String wordKey;
    private String encodedText;

    public String getWordKey() {
        return wordKey;
    }

    public String getEncodedText() {
        return encodedText;
    }

    private void getSetOfLetters() {
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
    }

    private byte[] getKey(boolean isEncrypt) {
        getSetOfLetters();
        StringBuilder wordKey = new StringBuilder(this.wordKey);
        for (int i = 0; i < wordKey.length(); i++) {
            if (!(smallLetters.contains(wordKey.charAt(i)) || bigLetters.contains(wordKey.charAt(i)))) {
                wordKey.deleteCharAt(i);
                i--;
            }
        }
        byte[] keyToAscii = wordKey.toString().toUpperCase().getBytes();
        for (int i = 0; i < keyToAscii.length; i++) {
            if (isEncrypt) {
                keyToAscii[i] -= 65;
            } else {
                keyToAscii[i] = (byte) (91 - keyToAscii[i]);
            }
        }
        return keyToAscii;
    }

    private boolean isWordKeyMissing() {
        try {
            return (this.wordKey.isEmpty());
        } catch (NullPointerException ex) {
            System.out.println("[Error:] missing word-key, encryption impossible");
            return true;
        }
    }

    public String encrypt(String originalText, String wordKey, boolean isEncrypt) {
        this.wordKey = wordKey;
        if (originalText == null) return null;
        if (isWordKeyMissing()) return originalText;
        StringBuilder text = new StringBuilder(originalText);
        byte[] keyToAscii = getKey(isEncrypt);
        int temp;
        int flag;
        for (int i = 0; i < text.length(); i++) {
            flag = 0;
            if (smallLetters.contains(text.charAt(i))) {
                flag = 122;
            }
            if (bigLetters.contains(text.charAt(i))) {
                flag = 90;
            }
            if (flag > 0) {
                temp = text.codePointAt(i);
                temp += keyToAscii[i % keyToAscii.length];
                if (temp > flag) {
                    temp -= 26;
                }
                text.deleteCharAt(i);
                text.insert(i, (char) temp);
            }
        }
        return this.encodedText = text.toString();
    }

    public String decrypt(String cryptText, String wordKey) {
        this.wordKey = wordKey;
        if (cryptText == null) return null;
        if (isWordKeyMissing()) return cryptText;
        return encrypt(cryptText, wordKey, false);
    }
}
