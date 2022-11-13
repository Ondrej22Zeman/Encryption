import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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

    //výběr souboru
    public void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
        fileChooser.setFileFilter(filter);
        fileIsLoaded = false;
        boolean isTxt = false;


        //Při výběru jiného formátu než je formát .txt, bude požadovat nový soubor
        while (!isTxt) {
            //výběr místa souboru
            int response = fileChooser.showOpenDialog(null);
            //Při kliknutí na "cancel" se dialog vypne
            if (response == JFileChooser.CANCEL_OPTION) {
                break;
            }

            //Ověření zda byl soubor úspěšně vybrán
            if (response == JFileChooser.APPROVE_OPTION) {
                // Získání cesty k souboru
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                //Ověřuje, zda byl otevřen soubor
                if (checkTxt(file.getAbsolutePath())) {
                    isTxt = true;
                    fileIsLoaded = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Prosím vyberte .txt soubor");
                }
            }
        }
    }
    //přečtení souboru a uložení do zprávy
    public String readFileContent() throws IOException {
        StringBuilder message = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            message.append(st);
        return message.toString();
    }
    //kontrola zda se jedná o .txt soubor
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
