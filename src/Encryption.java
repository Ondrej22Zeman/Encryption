import java.util.Arrays;

public class Encryption {

    private final Character[] alphabet;

    public Encryption(Character[] alphabet) {
        this.alphabet = alphabet;
    }


    public String encrypt(String message, int key, int numberOfCycles) {
        final StringBuilder encryptedMessage = new StringBuilder();
        char tempChar;
        boolean isUppercase;
        char encryptedChar;
        int index;

        //Podle počtu "zamíchání" se provede určitý počet enkrypcí
        for (int i = 0; i < numberOfCycles; i++) {
            int tempKey = key;
            for (int j = 0; j < message.length(); j++) {
                tempChar = message.charAt(j); //Získání písmene ze zprávy

                isUppercase = Character.isUpperCase(tempChar); //Ošetřuje velikost některých písmen

                index = Arrays.asList(alphabet).indexOf(Character.toLowerCase(tempChar));//Získání indexu písmene z abecedy
                encryptedChar = alphabet[(index + tempKey) % alphabet.length];//Získání enkryptovaného písmene
                tempKey = index; //Přesun klíče

                encryptedChar = isUppercase //Ošetření velkého písmene
                        ? Character.toUpperCase(encryptedChar)
                        : Character.toLowerCase(encryptedChar);

                encryptedMessage.append(encryptedChar); //Vytvoření enkryptované zprávy pomocí přidávání písmen
            }
            message = encryptedMessage.toString();
            encryptedMessage.setLength(0); //V případě více cyklů se enkryptovaná zpráva uloží a StringBuilde vynuluje pro další enkryptovanou zprávu
        }
        return message;
    }
}
