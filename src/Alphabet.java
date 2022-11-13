import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alphabet {

    private final Character[] normalCzechAlphabet = {
            'a', 'á', 'b', 'c', 'č', 'd', 'ď', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ň', 'o',
            'ó', 'p', 'q', 'r', 'ř', 's', 'š', 't', 'ť', 'u', 'ú', 'ů', 'v', 'w', 'x', 'y', 'ý', 'z', 'ž'
    };
    private final Character[] advancedCzechAlphabet = {
            'a', 'á', 'b', 'c', 'č', 'd', 'ď', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ň', 'o',
            'ó', 'p', 'q', 'r', 'ř', 's', 'š', 't', 'ť', 'u', 'ú', 'ů', 'v', 'w', 'x', 'y', 'ý', 'z', 'ž', '1', '2',
            '3', '4', '5', '6', '7', '8', '9', '0', ':', '"', ' ', '_', '-', '?', '%', ')', '(', '<', '>', '.', '/',
            '@', '&', '$', '#', ';', '[', ']', '+', '{', '}', '|', '´', '°',
    };
    private final Character[] normalSlovakAlphabet = {
            'a', 'á', 'ä', 'b', 'c', 'č', 'd', 'ď', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'ľ', 'm', 'n',
            'ň', 'o', 'ó', 'ô', 'p', 'q', 'r', 'ŕ', 's', 'š', 't', 'ť', 'u', 'ú', 'v', 'w', 'x', 'y', 'z', 'ž'
    };
    private final Character[] advancedSlovakAlphabet = {
            'a', 'á', 'ä', 'b', 'c', 'č', 'd', 'ď', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'ľ', 'm', 'n',
            'ň', 'o', 'ó', 'ô', 'p', 'q', 'r', 'ŕ', 's', 'š', 't', 'ť', 'u', 'ú', 'v', 'w', 'x', 'y', 'z', 'ž', '1',
            '2', '3', '4', '5', '6', '7', '8', '9', '0', ':', '"', ' ', '_', '-', '?', '%', ')', '(', '<', '>', '.',
            '/', '@', '&', '$', '#', ';', '[', ']', '+', '{', '}', '|', '´', '°',
    };

    public Alphabet() {
    }

    public Character[] getNormalCzechAlphabet() {
        return normalCzechAlphabet;
    }

    public Character[] getAdvancedCzechAlphabet() {
        return advancedCzechAlphabet;
    }

    public boolean checkDigits(String str) {
        String regex = "[0-9]+";

        Pattern p = Pattern.compile(regex);

        if (str == null) {
            return false;
        }

        Matcher m = p.matcher(str);

        return m.matches();

    }
}
