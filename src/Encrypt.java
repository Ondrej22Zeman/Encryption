import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;


public class Encrypt extends JFrame {
    private JPanel mainPanel;
    private JTextField messageToEncrypt;
    private JButton encryptButton;
    private JTextField saveLocationField;
    private JButton chooseSaveLocationButton;
    private JPanel detailsPanel;
    private JButton goBackButton;
    private JPanel checkPanel;
    private JCheckBox checkNormalAlphabet;
    private JCheckBox checkAdvancedAlphabet;
    private JTextField keyTextField;
    private JTextField iterationTextField;

    public Encrypt(String title) {
        super(title);

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        checkNormalAlphabet.setSelected(true);

        SaveFile saveFile = new SaveFile();

        //Tlačítko pro výběr místa uložení a názvu souboru
        chooseSaveLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Kontrola, jestli byl vybrán soubor pro vytvoření kopie
                saveFile.chooseSaveLocation();
            }
        });


        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alphabet a = new Alphabet();
                Character[] alphabet;

                if (!Objects.equals(messageToEncrypt.getText(), "")) {

                    //Kontrola, jestli uživatel vybral místo pro uložení
                    if (saveFile.isChosen()) {
                        if (a.checkDigits(keyTextField.getText()) || Objects.equals(keyTextField.getText(), "")) {
                            if (a.checkDigits(iterationTextField.getText()) || Objects.equals(iterationTextField.getText(), "")) {
                                if (checkNormalAlphabet.isSelected()) {
                                    alphabet = a.getNormalCzechAlphabet();
                                } else {
                                    alphabet = a.getAdvancedCzechAlphabet();
                                }
                                int iterations;
                                if (Objects.equals(iterationTextField.getText(), "")) {
                                    iterations = 1;
                                } else {
                                    iterations = Integer.parseInt(keyTextField.getText());
                                }
                                int key;
                                if (Objects.equals(iterationTextField.getText(), "")) {
                                    key = 1;
                                } else {
                                    key = Integer.parseInt(keyTextField.getText());
                                }
                                Encryption encryption = new Encryption(alphabet);
                                String encryptedMessage = encryption.encrypt(messageToEncrypt.getText(), key, iterations);
                                try {
                                    saveFile.parseMessageToFile(encryptedMessage);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                //Dialog informující uživatele o enkrypci

                                JOptionPane.showMessageDialog(null, "Encrypted");
                            } else JOptionPane.showMessageDialog(null, "ERROR: Zadejte iterace ve formě čísla");
                        } else JOptionPane.showMessageDialog(null, "ERROR: Zadejte klíč ve formě čísla");
                    } else JOptionPane.showMessageDialog(null, "ERROR: Zpráva je prázdná");
                } else JOptionPane.showMessageDialog(null, "ERROR: Vyberte místo pro uložení souboru");
            }

        });
        //Tlačítko zpět
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame encryptionOrDecryption = new EncryptionOrDecryption("Vyberte využití");
                encryptionOrDecryption.setLocationRelativeTo(null);
                encryptionOrDecryption.setVisible(true);
                dispose();
            }
        });

        checkNormalAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAdvancedAlphabet.setSelected(!checkNormalAlphabet.isSelected());
            }
        });
        checkAdvancedAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkNormalAlphabet.setSelected(!checkAdvancedAlphabet.isSelected());
            }
        });



    }
}
