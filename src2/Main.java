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

        JLabel statusLabel = new JLabel("Character number: 0");

        model.addObserver(text -> statusLabel.setText("Character number: " + text.length()));

        JPanel buttonPanel = new JPanel();
        JButton btnType = new JButton("Confirm Text");
        JButton btnUndo = new JButton("Undp");
        JButton btnFont = new JButton("Increase size of the font (18)");
        JButton btnIterate = new JButton("List rows (Console)");

        buttonPanel.add(btnType);
        buttonPanel.add(btnUndo);
        buttonPanel.add(btnFont);
        buttonPanel.add(btnIterate);

        btnType.addActionListener(e -> {
            TypeCommand cmd = new TypeCommand(model, textArea.getText());
            commandManager.executeCommand(cmd);
            System.out.println("Text has been updated!");
        });

        btnUndo.addActionListener(e -> {
            commandManager.undo();
            textArea.setText(model.getText());
            System.out.println("Last operation has been reversed!");
        });

        btnFont.addActionListener(e -> {
            System.out.println("Iteration on rows!");
            String[] linesList = textArea.getText().split("\n");
            LineIterator iterator = new LineIterator(Arrays.asList(linesList));

            while(iterator.hasNext()){
                System.out.println("Line: " + iterator.hasNext());
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.setVisible(true);



    }

}