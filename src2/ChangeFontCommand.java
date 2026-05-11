public class ChangeFontCommand implements Command{
    private String oldFont;
    private String newFont;


    public ChangeFontCommand(String newFont){
        this.newFont = newFont;
    }

    @Override
    public void execute(){
        this.oldFont = EditorConfig.getEditorConfigObject().getFontName();
        EditorConfig.getEditorConfigObject().setFontName(newFont);
    }

    @Override
    public void undo(){
        EditorConfig.getEditorConfigObject().setFontName(oldFont);
    }
}
