import Forms.EncryptionOrDecryption;

import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Abeceda
        final Character[] ALPHABET_advanced = {
                'a', 'á', 'b', 'c', 'd', 'ď', 'e', 'é', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ň', 'o', 'ó',
                'p', 'q', 'r', 'ř', 's', 'š', 't', 'ť', 'u', 'ú', 'ů', 'v', 'w', 'x', 'y', 'z', 'ž', '1', '2', '3',
                '4', '5', '6', '7', '8', '9', '0', ':', '"', ' ', '_', '-', '?', '%', ')', '(', '<', '>', '.', '/',
                '@', '&', '$',
        };
        final Character[] ALPHABET_normal = {
                'a', 'á', 'b', 'c', 'd', 'ď', 'e', 'é', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ň', 'o', 'ó',
                'p', 'q', 'r', 'ř', 's', 'š', 't', 'ť', 'u', 'ú', 'ů', 'v', 'w', 'x', 'y', 'z', 'ž'
        };

        //možnost zvolit kolikrát se zpráva "zamíchá"
        final int numberOfCycles = 8;

        Encryption encryption = new Encryption(ALPHABET_advanced, numberOfCycles);
        Decryption decryption = new Decryption(ALPHABET_advanced, numberOfCycles);
        String message = "Ondra je borec";

        int key = 22; // klíč
        String encryptedMessage = encryption.encrypt(message, key);
        System.out.println(encryptedMessage);
        System.out.println(decryption.decrypt(encryptedMessage, key));

        JFrame frame = new EncryptionOrDecryption("Choose usage");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}