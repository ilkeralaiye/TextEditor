// import java.io.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        
        // Frame instance
        JFrame frame = new JFrame("File Reader");

        // Declaration of notepad instance
        Notepad notepad = Notepad.getInstance();

        // Frame settings
        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setVisible(true);

    }

}