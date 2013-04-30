package org.vpryst.downloadLinkTest;

import org.apache.commons.cli.ParseException;
import org.testng.annotations.Test;
import org.vpryst.downloadLink.CommandsManager;
import static org.testng.Assert.*;

/**
 * @author vpryst
 */
public class CommandsManagerTest {

    @Test
    public void commandsManagerConstructor() {
        String[] args = null;
        CommandsManager command = new CommandsManager(args);
        assertEquals(command.getName(), "");
        assertEquals(command.getPass(), "");

    }

    @Test
    public void commandsManagerConstructorLogin() {
        String[] args1 = {"-l"};
        CommandsManager command = new CommandsManager(args1);
    }
    @Test
    public void commandsManagerConstructorPassword() {
        String[] args1 = {"-p"};
        CommandsManager command = new CommandsManager(args1);
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

    @Test
    public void commandsManagerConstructorLink() {
        String[] args1 ={"-g"};
        CommandsManager command = new CommandsManager(args1);
        assertFalse(command.isRequiredFetch());
    }
}
