import java.util.Arrays;

public class DecryptMessage {
    public DecryptMessage(Character[] alphabet) {
        this.alphabet = alphabet;
    }
    private final Character[] alphabet;

    public String decrypt(String message, int key, int numberOfCycles) {
        int passCharIndex;
        char revealedCharIndex;
        final StringBuilder decryptedMessage = new StringBuilder();
        char tempChar;
        boolean isUppercase;

        //Podle počtu "zamíchání" se provede určitý počet dekrypcí
        for (int i = 0; i < numberOfCycles; i++) {
            int keyStream = key;
            for (int j = 0; j < message.length(); j++) {
                tempChar = message.charAt(j); //Získání písmene ze zprávy
                passCharIndex = Arrays.asList(alphabet).indexOf(Character.toLowerCase(tempChar)); //Získání indexu písmene z abecedy
                isUppercase = Character.isUpperCase(tempChar); //Ošetřuje velikost některých písmen

                keyStream = calcIndex(passCharIndex, keyStream); //Výpočet indexu hledaného písmene v abecedě
                revealedCharIndex = alphabet[keyStream]; //Získání písmene podle vypočítaného indexu

                revealedCharIndex = isUppercase //Ošetření velkého písmene
                        ? Character.toUpperCase(revealedCharIndex)
                        : Character.toLowerCase(revealedCharIndex);

                decryptedMessage.append(revealedCharIndex); //Vytvoření dekryptované zprávy pomocí přidávání písmen
            }
            message = decryptedMessage.toString();
            decryptedMessage.setLength(0); //V případě více cyklů se enkryptovaná zpráva uloží a StringBuilde vynuluje pro další enkryptovanou zprávu
        }
        return message;
    }

    private int calcIndex(int character, int keyStream) { //Výpočet indexu hledaného písmene v abecedě
        int sum = character - keyStream;
        while (sum < 0) {
            sum += this.alphabet.length;
        }
        return sum;
    }
}
