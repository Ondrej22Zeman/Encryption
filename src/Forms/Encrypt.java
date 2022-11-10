package Forms;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;


public class Encrypt extends JFrame {
    private JPanel mainPanel;
    private JTextField messageToEncrypt;
    private JLabel image;
    private JPanel imagePanel;
    private JPanel loadImagePanel;
    private JButton encryptButton;
    private JButton loadImageButton;
    private JTextField saveLocationField;
    private JButton chooseSaveLocationButton;
    private JPanel detailsPanel;
    private JPasswordField passwordField;
    private JButton goBackButton;

    public Encrypt(String title) {
        super(title);

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.image.setVisible(false);
        this.pack();

        SaveLocation saveLocation = new SaveLocation();

        //Tlačítko pro výběr místa uložení a názvu souboru
        chooseSaveLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Kontrola, jestli byl vybrán soubor pro vytvoření kopie
                    saveLocation.chooseSaveLocation();
                    if (saveLocation.isChosen()) {
                        saveLocationField.setText(saveLocation.getLocation());
                    } else JOptionPane.showMessageDialog(null, "ERROR: Please choose save location");
            }
        });

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Kontrola, jestli byl vybrán obrázek
                if (file.isFileIsLoaded()) {

                    //Kontrola, jestli Uživatel zadal text
                    if (!Objects.equals(messageToEncrypt.getText(), "")) {

                        //Kontrola, jestli uživatel vybral místo pro uložení
                        if (saveLocation.isChosen()) {
                            EncryptMessage encryptMessage;
                            double totalLength = messageToEncrypt.getText().length() + 3;

                            //Kontrola, jestli uživatel vložil heslo. Jestli ano, vloží se za zprávu
                            if (Objects.equals(passwordField.getText(), "")) {
                                encryptMessage = new EncryptMessage(
                                        saveLocation, file, messageToEncrypt.getText()
                                );
                            } else {
                                encryptMessage = new EncryptMessage(
                                        saveLocation, file, messageToEncrypt.getText(), passwordField.getText()
                                );
                                totalLength += passwordField.getText().length() + 1;
                            }

                            //Kontrola, zda se text vejde do obrázku
                            int pixelsNeeded = (int) Math.ceil((totalLength * 4) / 3);
                            if (pixelsNeeded > (file.getHeight() * file.getWidth())) {
                                JOptionPane.showMessageDialog(null, "ERROR: Please choose larger file, OR shorter message");
                            } else {
                                try {
                                    //Dialog informující uživatele o enkrypci
                                    if (encryptMessage.encrypt()) {
                                        JOptionPane.showMessageDialog(null, "Encrypted");
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }

                        } else JOptionPane.showMessageDialog(null, "ERROR: Please choose save location");
                    } else JOptionPane.showMessageDialog(null, "ERROR: Message is empty");
                } else JOptionPane.showMessageDialog(null, "ERROR: Please choose image before encrypting");
            }
        });
        //Tlačítko zpět
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame encryptionOrDecryption = new EncryptionOrDecryption("Choose usage");
                encryptionOrDecryption.setLocationRelativeTo(null);
                encryptionOrDecryption.setVisible(true);
                dispose();
            }
        });
    }
}
