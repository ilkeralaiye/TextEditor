    public class Notepad {
    
    private String filePath;

    private Notepad() {

    }

    public static Notepad getInstance() {
        return new Notepad();
    }

    public boolean Save() { return true; }

    public boolean SaveAs() { return true; }

    public boolean Open() { return true; }

    public boolean Close() { return true; }

    public String Find() { return "Text found"; }

    public String ReverseAction() { return "Action reversed"; }

}
