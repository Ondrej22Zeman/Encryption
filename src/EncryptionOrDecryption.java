import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptionOrDecryption extends JFrame {
    private JPanel mainPanel;
    private JPasswordField passwordField;
    private JButton decryptButton;
    private JButton encryptButton;
    private JPanel topPanel;

    public EncryptionOrDecryption(String title) {
        super(title);

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        //Tlačítko pro enkrypci
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame encrypt = new Encrypt("Enkryptování");
                encrypt.setLocationRelativeTo(null);
                encrypt.setVisible(true);
                dispose();
            }
        });
        //Tlačítko pro dekrypci
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame decrypt = new Decrypt("Dekryptování");
                decrypt.setLocationRelativeTo(null);
                decrypt.setVisible(true);
                dispose();
            }
        });
    }
}
