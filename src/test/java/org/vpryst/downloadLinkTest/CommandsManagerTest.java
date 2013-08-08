package org.vpryst.downloadLinkTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.vpryst.downloadLink.CommandsManager;

/**
 * @author vpryst
 */
public class CommandsManagerTest {

    @Test
    public void commandsManagerConstructor() {
        String[] args = null;
        CommandsManager command = new CommandsManager(args);
        assertEquals(command.getName(), "qwerty");
        assertEquals(command.getPass(), "123456");

    }

    @Test
    public void commandsManagerConstructorLogin() {
        String[] args1 = {"-u"};
        CommandsManager command = new CommandsManager(args1);
    }

    @Test
    public void commandsManagerConstructorPassword() {
        String[] args1 = {"-p"};
        CommandsManager command = new CommandsManager(args1);
    }

    @Test
    public void commandsManagerConstructorOption() {
        String[] args1 = {"-u", "Login", "-p", "password"};
        CommandsManager command = new CommandsManager(args1);
        command.setName("cdasdfdsf");
        command.setPass("dfgdfh");
        assertEquals(command.getName(), "cdasdfdsf");
        assertEquals(command.getPass(), "dfgdfh");
    }

    @Test
    public void commandsManagerConstructorLink() {
        String[] args1 = {"-f"};
        CommandsManager command = new CommandsManager(args1);
        assertFalse(command.isRequiredFetch());
    }
}
