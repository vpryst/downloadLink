package org.vpryst.downloadLink;

/**
 * @author vpryst
 *
 */
public class Main {
    public static void main(String[] args) {
        CommandsManager command = new CommandsManager(args);
        WriteFile files = new WriteFile(command.getName(),command.getPass());
        files.getLinks();
        files.fetchFile();
       
    }
}