package org.vpryst.downloadLink;

/**
 * @author vpryst Main Class for execute program
 */
public class Main {
    public static void main(String[] args) {
        CommandsManager command = new CommandsManager(args);
        ConnectionManager connctionManager = new ConnectionManager(command.getName(), command.getPass());

        WriteFile files = new WriteFile(connctionManager);
        files.fetchFile(command.isRequiredFetch());

    }
}