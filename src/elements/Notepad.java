package elements;

import java.util.Scanner;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;   
import javax.swing.undo.UndoManager;

public class Notepad {
    
    
    protected JTextArea textArea;
    protected Scanner fileScanner;
    protected JFrame frame;
    protected FileManager fileManager;
    protected UndoManager undoManager;
    private JScrollPane scrollPane;


    private final int WIDTH = 500, HEIGHT = 450;

    // || ===== || ===== || ===== || ===== || ===== || Constructor functions || ===== || ===== || ===== || ===== || ===== || 
    private Notepad() {        
        this.constructTextArea();
        this.handleUndoRedo();
        this.constructFrame();
        this.constructButtons();
        
        this.frame.setVisible(true);

        this.fileManager = new FileManager(this);        
    }

    private void constructButtons() {
        // We will construct the buttons here and add them to the frame.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBounds(0, 0, 400, 50);

        JButton saveButton = new JButton("Save");
        JButton saveAsButton = new JButton("Save As");
        JButton openButton = new JButton("Open");
        JButton closeButton = new JButton("Close");
        JButton findButton = new JButton("Find");
        
        saveButton.addActionListener(e -> Save());
        saveAsButton.addActionListener(e -> SaveAs());
        openButton.addActionListener(e -> Open());
        closeButton.addActionListener(e -> Close());
        findButton.addActionListener(e -> Find());

        buttonPanel.add(saveButton);
        buttonPanel.add(saveAsButton);
        buttonPanel.add(openButton);
        buttonPanel.add(closeButton);
        buttonPanel.add(findButton);

        this.frame.add(buttonPanel);
    }

    public static Notepad getInstance() {
        return new Notepad();
    }

    private void constructFrame() {
        try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error occurs at setting look and feel: " + e.getMessage());
        }

        this.frame = new JFrame("Notepad");
        this.frame.setSize(this.WIDTH, this.HEIGHT);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(null);
        this.frame.add(scrollPane);
        
    }

    private void constructTextArea() {
        this.textArea = new JTextArea(); 
        this.scrollPane = new JScrollPane(this.textArea);
        this.scrollPane.setBounds(150, 40, 200, 300);
    }

    private void handleUndoRedo() {
        this.undoManager = new UndoManager();
        this.textArea.getDocument().addUndoableEditListener(undoManager);

        this.textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
                    if (undoManager.canUndo()) {
                        undoManager.undo();
                    }
                }
                
                else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
                    if (undoManager.canRedo()) {
                        undoManager.redo();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyTyped(KeyEvent e) {}
        });

    }

    // || ===== || ===== || ===== || ===== || ===== || Auxiliary functions || ===== || ===== || ===== || ===== || ===== || 
    
    private String askForTextToFind() {
        return JOptionPane.showInputDialog(frame,"Enter the text:","Find",JOptionPane.QUESTION_MESSAGE);

    }

    // || ===== || ===== || ===== || ===== || ===== || Main Notepad functions || ===== || ===== || ===== || ===== || ===== ||
    public boolean Save() { 
        return this.fileManager.Save();
    }  
    public boolean SaveAs() { 
        return this.fileManager.SaveAs(); 
    }
    public boolean Open() { 
        return this.fileManager.Open(); 
    }
    public void Close() { 
        this.fileManager.Close(); 
    }

    public void Find() {
        
        String textToFind = askForTextToFind();

        if (textToFind == null || textToFind.isEmpty()) {
            return;
        }

        String textArray[] = this.textArea.getText().split(" ");

        int length = textArray.length;
        int startIndex = 0;
        for (int i=0;i<length;i++) {
            String w = textArray[i];
            if (w.equals(textToFind)) {
                textArea.select(startIndex, startIndex + w.length());
                textArea.requestFocus();
                return;
            }
            startIndex += w.length() + 1; 

        }

    }

}
