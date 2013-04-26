package org.vpryst.downloadLinkTest;

import org.testng.annotations.Test;
import org.vpryst.downloadLink.CommandsManager;

/**
 * @author vpryst
 */
public class CommandsManagerTest {
    private String[] args = new String[4];

    @Test
    public void commandsManagerConstructor() {
        args = null;
        CommandsManager command = new CommandsManager(args);
        
    }

    @Test(expectedExceptions = java.lang.NullPointerException.class)
    public void commandsManagerConstructorLogin() {
        args[0] = "-l";
        args[1] = "login";
        CommandsManager command = new CommandsManager(args);
    }

    @Test
    public void commandsManagerConstructorOption() {
        String[] args1 = {"-l", "Login", "-p", "password"};
        CommandsManager command = new CommandsManager(args1);
        command.setName("cdasdfdsf");
        command.setPass("dfgdfh");
        command.getName();
        command.getPass();
    }
}
