import javax.swing.*;
import java.io.*;
import java.util.Objects;


public class OpenFile {
    private boolean fileIsLoaded;
    private File file;

    public boolean isFileIsLoaded() {
        return fileIsLoaded;
    }

    public File getFile() {
        return file;
    }

    public OpenFile() {
    }

    public void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileIsLoaded = false;
        boolean isTxt = false;


        //Při výběru jiného formátu než je formát obrázku, bude požadovat nový soubor
        while (!isTxt) {

            //výběr místa obrázku
            int response = fileChooser.showOpenDialog(null);

            //Při kliknutí na "cancel" se dialog vypne
            if (response == JFileChooser.CANCEL_OPTION) {
                break;
            }

            //Ověření zda byl obrázek úspěšně vybrán
            if (response == JFileChooser.APPROVE_OPTION) {
                // Získání cesty k obrázku
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                //Ověřuje, zda byl otevřen obrázek
                if (checkTxt(file.getAbsolutePath())) {
                    isTxt = true;
                    fileIsLoaded = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Prosím vyberte .txt soubor");
                }
            }
        }
    }

    public String readFileContent() throws IOException {
        StringBuilder message = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)

            // Print the string
            message.append(st);
        return message.toString();
    }
    public boolean checkTxt(String path) {
        int counter = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '.') {
                counter++;
            }
        }
        if (counter == 1) {
            String extension = null;
            int i = path.lastIndexOf('.');
            if (i > 0) {
                extension = path.substring(i);
            }
            return Objects.equals(extension, ".txt");
        }
        return false;
    }
}
