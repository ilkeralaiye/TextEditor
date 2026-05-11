import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Main{
    public static void main(String[] args){
        TextModel model = new TextModel();
        CommandManager commandManager = new CommandManager();
        EditorConfig config = EditorConfig.getEditorConfigObject();

        JFrame frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font(config.getFontName(), Font.PLAIN, config.getFontSize()));
    }
}