package org.vpryst.downloadLink;

public class Main {
    public static void main(String[] args) {
        //CommandsManager command = new CommandsManager(args);
        //WriteFile files = new WriteFile(command.getName(),command.getPass());
        WriteFile files = new WriteFile("Anonim123","123456");
        files.getLinks();
        files.fetchFile();
    }
}