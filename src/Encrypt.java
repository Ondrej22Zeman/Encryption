import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;


public class Encrypt extends JFrame {
    private JPanel mainPanel;
    private JTextArea messageToEncrypt;
    private JButton encryptButton;
    private JTextField saveLocationField;
    private JButton chooseSaveLocationButton;
    private JPanel detailsPanel;
    private JButton goBackButton;
    private JPanel checkPanel;
    private JCheckBox checkNormalCzechAlphabet;
    private JCheckBox checkAdvancedCzechAlphabet;
    private JTextField keyTextField;
    private JTextField iterationTextField;
    private JCheckBox checkNormalSlovakAlphabet;
    private JCheckBox checkAdvancedSlovakAlphabet;
    private JCheckBox checkNormalEnglishAlphabet;

    public Encrypt(String title) {
        super(title);

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        checkNormalCzechAlphabet.setSelected(true);

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
                        //kotrola zda je klíč číslo/není prázdný
                        if (a.checkDigits(keyTextField.getText()) || Objects.equals(keyTextField.getText(), "")) {
                            //kotrola zda je iterace číslo/není prázdný
                            if (a.checkDigits(iterationTextField.getText()) || Objects.equals(iterationTextField.getText(), "")) {
                                //výběr abecedy podle checkboxu
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

                                int key;
                                if (Objects.equals(keyTextField.getText(), "")) {
                                    key = 1;
                                } else {
                                    key = Integer.parseInt(keyTextField.getText());
                                }
                                EncryptMessage encryptMessage = new EncryptMessage(alphabet);

                                String encryptedMessage = encryptMessage.encrypt(messageToEncrypt.getText(), key, iterations);
                                //kotrola jestli zpráva neobsahuje neplatné znaky
                                if (encryptedMessage == null) {
                                    JOptionPane.showMessageDialog(null, "ERROR: Zpráva obsahuje neplatné znaky");
                                } else {
                                    try {
                                        saveFile.parseMessageToFile(encryptedMessage);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    //Dialog informující uživatele o enkrypci
                                    JOptionPane.showMessageDialog(null, "Enkryptováno");
                                    goBack();
                                }
                            } else JOptionPane.showMessageDialog(null, "ERROR: Zadejte iterace ve formě čísla");
                        } else JOptionPane.showMessageDialog(null, "ERROR: Zadejte klíč ve formě čísla");
                    } else JOptionPane.showMessageDialog(null, "ERROR: Vyberte místo pro uložení souboru");
                } else JOptionPane.showMessageDialog(null, "ERROR: Zpráva je prázdná");
            }

        });
        //Tlačítko zpět
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        checkNormalCzechAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = checkNormalCzechAlphabet.isSelected();
                makeSureOnlyOneCheckIsSelected("checkNormalCzechAlphabet");

                if (!isSelected) {
                    makeSureOneCheckIsSelected();
                }
            }
        });
        checkAdvancedCzechAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = checkAdvancedCzechAlphabet.isSelected();
                makeSureOnlyOneCheckIsSelected("checkAdvancedCzechAlphabet");
                if (!isSelected) {
                    makeSureOneCheckIsSelected();
                }
            }
        });
        checkNormalSlovakAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = checkNormalSlovakAlphabet.isSelected();
                makeSureOnlyOneCheckIsSelected("checkNormalSlovakAlphabet");
                if (!isSelected) {
                    makeSureOneCheckIsSelected();
                }
            }
        });
        checkAdvancedSlovakAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = checkAdvancedSlovakAlphabet.isSelected();
                makeSureOnlyOneCheckIsSelected("checkAdvancedSlovakAlphabet");
                if (!isSelected) {
                    makeSureOneCheckIsSelected();
                }
            }
        });
        checkNormalEnglishAlphabet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = checkNormalEnglishAlphabet.isSelected();
                makeSureOnlyOneCheckIsSelected("checkNormalEnglishAlphabet");
                if (!isSelected) {
                    makeSureOneCheckIsSelected();
                }
            }
        });


    }

    //funkce zpět na výběr enkrypce/dekrypce
    private void goBack() {
        setVisible(false);
        JFrame encryptionOrDecryption = new EncryptionOrDecryption("Vyberte využití");
        encryptionOrDecryption.setLocationRelativeTo(null);
        encryptionOrDecryption.setVisible(true);
        dispose();
    }

    //funkce zajišťující aby aspoň jedna abeceda byla vybrána
    private void makeSureOneCheckIsSelected() {
        if (!checkNormalCzechAlphabet.isSelected() &&
                !checkAdvancedCzechAlphabet.isSelected() &&
                !checkAdvancedSlovakAlphabet.isSelected() &&
                !checkNormalEnglishAlphabet.isSelected() &&
                !checkNormalSlovakAlphabet.isSelected()) {
            checkNormalCzechAlphabet.setSelected(true);
        }
    }

    //funkce zajišťující výběr pouze jednoho checkboxu
    private void makeSureOnlyOneCheckIsSelected(String check) {
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
