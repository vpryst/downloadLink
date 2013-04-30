package org.vpryst.downloadLinkTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;
import org.vpryst.downloadLink.CommandsManager;

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
        assertEquals(command.getName(), "cdasdfdsf");
        assertEquals(command.getPass(), "dfgdfh");
    }

    @Test
    public void commandsManagerConstructorLink() {
        String[] args1 ={"-g"};
        CommandsManager command = new CommandsManager(args1);
        assertFalse(command.isRequiredFetch());
    }
}
