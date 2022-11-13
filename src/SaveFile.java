import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFile {

    private String location;
    private boolean isChosen = false;

    //Zobrazení okna pro výběr místa pro uložení obrázku
    public void chooseSaveLocation() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        JFrame parentFrame = new JFrame();

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            isChosen = true;
            File fileToSave = fileChooser.getSelectedFile();
            location = fileToSave.getAbsolutePath() + ".txt";
        }
    }
    public void parseMessageToFile(String message) throws IOException {
        File file = new File(location);
        if (!file.exists()) {
            if (file.createNewFile()) {
                PrintWriter out = new PrintWriter(file);
                out.println(message);
                out.close();
            }
        }
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
