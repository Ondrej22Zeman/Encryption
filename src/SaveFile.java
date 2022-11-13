import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Objects;

public class SaveFile {

    private String location;
    private boolean isChosen = false;

    //Zobrazení okna pro výběr místa pro uložení souboru
    public void chooseSaveLocation() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
        fileChooser.setFileFilter(filter);

        fileChooser.setDialogTitle("Specify a file to save");

        JFrame parentFrame = new JFrame();

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            isChosen = true;
            File fileToSave = fileChooser.getSelectedFile();

            location = fileToSave.getAbsolutePath();

            if (!checkTxt(fileToSave.getAbsolutePath())) {
                location += ".txt";
            }
        }
    }

    //uložení textu do .txt souboru
    public void parseMessageToFile(String message) throws IOException {
        File file = new File(location);
        PrintWriter out = new PrintWriter(file);
        out.println(message);
        out.close();
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
            return true;
        }
        return false;
    }

    public SaveFile() {
    }

    public boolean isChosen() {
        return isChosen;
    }

    public String getLocation() {
        return location;
    }
}
