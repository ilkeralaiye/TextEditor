public class TypeCommand implements Command{
    private TextModel content;
    private String newText;
    private EditorState backupState;


    public TypeCommand(TextModel content, String newText){
        this.content = content;
        this.newText = newText;
    }

    @Override
    public void execute(){
        backupState = content.save();
        content.setText(newText);
    }

    @Override
    public void undo(){
        content.restore(backupState);
    }
}
