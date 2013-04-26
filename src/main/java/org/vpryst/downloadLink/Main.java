package org.vpryst.downloadLink;

public class Main {
    public static void main(String[] args) {
        String[] s = {"-l"};
        CommandsManager command = new CommandsManager(args);
        WriteFile files = new WriteFile(command.getName(),command.getPass());
        files.getLinks();
        files.fetchFile();
    }
}