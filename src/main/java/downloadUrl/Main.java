package downloadUrl;


public class Main {
    public static void main(String[] args) {
        CommandsManager command = new CommandsManager(args);
        WriteFile files = new WriteFile(command);
    }

}
