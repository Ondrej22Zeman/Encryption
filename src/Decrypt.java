import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class Decrypt extends JFrame {
    private JPanel mainPanel;
    private JPanel middlePanel;
    private JButton decryptButton;
    private JPanel bottomPanel;
    private JTextArea decryptedMessageField;
    private JButton goBackButton;
    private JPanel checkPanel;
    private JCheckBox checkNormalCzechAlphabet;
    private JCheckBox checkAdvancedCzechAlphabet;
    private JButton loadMessageButton;
    private JTextField keyTextField;
    private JTextField iterationTextField;
    private JCheckBox checkNormalSlovakAlphabet;
    private JCheckBox checkNormalEnglishAlphabet;
    private JCheckBox checkAdvancedSlovakAlphabet;

    public Decrypt(String title) {
        super(title);

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        OpenFile file = new OpenFile();
        checkNormalCzechAlphabet.setSelected(true);

        loadMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file.chooseFile();
            }
        });
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alphabet a = new Alphabet();
                //kotrola zda je klíč číslo/není prázdný
                if (a.checkDigits(keyTextField.getText()) || Objects.equals(keyTextField.getText(), "")) {
                    //kotrola zda je iterace číslo/není prázdný
                    if (a.checkDigits(iterationTextField.getText()) || Objects.equals(iterationTextField.getText(), "")) {
                        //Kontrola, zda je soubor vybrán úspěšně
                        if (file.isFileIsLoaded()) {

                            //výběr abecedy podle checkboxu
                            Character[] alphabet;
                            if (checkNormalCzechAlphabet.isSelected()) {
                                alphabet = a.getNormalCzechAlphabet();
                            } else if (checkAdvancedCzechAlphabet.isSelected()) {
                                alphabet = a.getAdvancedCzechAlphabet();
                            } else if (checkNormalSlovakAlphabet.isSelected()) {
                                alphabet = a.getNormalSlovakAlphabet();
                            } else if (checkAdvancedSlovakAlphabet.isSelected()) {
                                alphabet = a.getAdvancedSlovakAlphabet();
                            } else {
                                alphabet = a.getNormalEnglishAlphabet();
                            }

                            //počet iterací
                            int iterations;
                            if (Objects.equals(iterationTextField.getText(), "")) {
                                iterations = 1;
                            } else {
                                iterations = Integer.parseInt(iterationTextField.getText());
                            }
                            System.out.println(iterations);

                            //klíč
                            int key;
                            if (Objects.equals(keyTextField.getText(), "")) {
                                key = 1;
                            } else {
                                key = Integer.parseInt(keyTextField.getText());
                            }

                            DecryptMessage decryptMessage = new DecryptMessage(alphabet);
                            String messageToEncrypt = null;
                            try {
                                messageToEncrypt = file.readFileContent();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            String decryptedMessage = decryptMessage.decrypt(messageToEncrypt, key, iterations);
                            decryptedMessageField.setText(decryptedMessage);
                        } else JOptionPane.showMessageDialog(null, "ERROR: Vyberte prosím soubor");
                    } else JOptionPane.showMessageDialog(null, "ERROR: Zadejte iterace ve formě čísla");
                } else JOptionPane.showMessageDialog(null, "ERROR: Zadejte klíč ve formě čísla");

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

        checkNormalCzechAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = checkNormalCzechAlphabet.isSelected();
                makeSureOnlyOneCheckIsSelected("checkNormalCzechAlphabet");

                if (!isSelected){
                    makeSureOneCheckIsSelected();
                }
            }
        });
        checkAdvancedCzechAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = checkAdvancedCzechAlphabet.isSelected();
                makeSureOnlyOneCheckIsSelected("checkAdvancedCzechAlphabet");
                if (!isSelected){
                    makeSureOneCheckIsSelected();
                }
            }
        });
        checkNormalSlovakAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = checkNormalSlovakAlphabet.isSelected();
                makeSureOnlyOneCheckIsSelected("checkNormalSlovakAlphabet");
                if (!isSelected){
                    makeSureOneCheckIsSelected();
                }
            }
        });
        checkAdvancedSlovakAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = checkAdvancedSlovakAlphabet.isSelected();
                makeSureOnlyOneCheckIsSelected("checkAdvancedSlovakAlphabet");
                if (!isSelected){
                    makeSureOneCheckIsSelected();
                }
            }
        });
        checkNormalEnglishAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = checkNormalEnglishAlphabet.isSelected();
                makeSureOnlyOneCheckIsSelected("checkNormalEnglishAlphabet");
                if (!isSelected){
                    makeSureOneCheckIsSelected();
                }
            }
        });


    }
    //funkce zajišťující aby aspoň jedna abeceda byla vybrána
    private void makeSureOneCheckIsSelected(){
        if (    !checkNormalCzechAlphabet.isSelected() &&
                !checkAdvancedCzechAlphabet.isSelected() &&
                !checkAdvancedSlovakAlphabet.isSelected() &&
                !checkNormalEnglishAlphabet.isSelected() &&
                !checkNormalSlovakAlphabet.isSelected()){
            checkNormalCzechAlphabet.setSelected(true);
        }
    }
    //funkce zajišťující výběr pouze jednoho checkboxu
    private void makeSureOnlyOneCheckIsSelected(String check){
        switch (check) {
            case "checkNormalCzechAlphabet" -> {
                checkAdvancedCzechAlphabet.setSelected(false);
                checkNormalSlovakAlphabet.setSelected(false);
                checkAdvancedSlovakAlphabet.setSelected(false);
                checkNormalEnglishAlphabet.setSelected(false);
            }
            case "checkAdvancedCzechAlphabet" -> {
                checkNormalCzechAlphabet.setSelected(false);
                checkNormalSlovakAlphabet.setSelected(false);
                checkAdvancedSlovakAlphabet.setSelected(false);
                checkNormalEnglishAlphabet.setSelected(false);
            }
            case "checkNormalSlovakAlphabet" -> {
                checkAdvancedCzechAlphabet.setSelected(false);
                checkNormalCzechAlphabet.setSelected(false);
                checkAdvancedSlovakAlphabet.setSelected(false);
                checkNormalEnglishAlphabet.setSelected(false);
            }
            case "checkAdvancedSlovakAlphabet" -> {
                checkAdvancedCzechAlphabet.setSelected(false);
                checkNormalCzechAlphabet.setSelected(false);
                checkNormalSlovakAlphabet.setSelected(false);
                checkNormalEnglishAlphabet.setSelected(false);
            }
            case "checkNormalEnglishAlphabet" -> {
                checkAdvancedCzechAlphabet.setSelected(false);
                checkNormalCzechAlphabet.setSelected(false);
                checkNormalSlovakAlphabet.setSelected(false);
                checkAdvancedSlovakAlphabet.setSelected(false);
            }
        }
    }
}
