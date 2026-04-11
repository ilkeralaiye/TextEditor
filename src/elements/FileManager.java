package elements;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;


class FileManager { 

    private String filePath;
    private Notepad root;
    private FileWriter fileWriter;

    public FileManager(Notepad root) {
        this.root = root;
        this.filePath = null;
    }

    public void AskForFilePath() {
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Location");

        int userSelection = fileChooser.showSaveDialog(root.frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            this.filePath = fileToSave.getAbsolutePath();
        } 
        
    }

    private boolean SavetoFile() {

        try {
            fileWriter = new FileWriter(this.filePath);
        } catch (IOException e) {
            System.out.println("Error occurs at declaration of fileWriter: "  + e.getMessage());
            return false;
        }

        String text = root.textArea.getText();

        try {
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error occurs at file writing: " + e.getMessage());
            return false;
        }
        return true;
    }

    // || ===== || ===== || ===== || ===== || ===== || Main Notepad functions || ===== || ===== || ===== || ===== || ===== || 
    public boolean Save() {
        /*
        If we have a filePath that is declared before, 
        we should save the file to that path, 
        otherwise we should ask the user for a file path
        */

        if (filePath == null) {
            this.AskForFilePath();
        }

        return SavetoFile();
    }

    public boolean SaveAs() { 
        /*
        At SaveAs function, we should ask the user for a file path 
        whether there is a filepath that is declared before.
        */
        this.AskForFilePath();
        return SavetoFile();
    }

    public boolean Open() {
        
        this.AskForFilePath();
        
        if (this.filePath == null) {
            return false;
        }

        try {
            File fileToOpen = new File(this.filePath);
            Scanner fileScanner = new Scanner(fileToOpen);
            StringBuilder fileContent = new StringBuilder();
            
            while (fileScanner.hasNextLine()) {
                fileContent.append(fileScanner.nextLine());
                fileContent.append("\n");
            }
            
            root.textArea.setText(fileContent.toString()); 
            return true;

        } catch (IOException e) {
            System.out.println("Error occurs reading the file: " + e.getMessage());
            return false;
        }
    }

    public void Close() {
        root.frame.dispose();
    }

}