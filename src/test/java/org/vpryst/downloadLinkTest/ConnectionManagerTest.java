package org.vpryst.downloadLinkTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.vpryst.downloadLink.ConnectionManager;
import org.vpryst.downloadLink.FilePropertyManager;

public class ConnectionManagerTest {
    @Test
    public void connectionTestWithoutParam() {
        ConnectionManager manager = new ConnectionManager(null, null);
        assertFalse(manager.autentificate(FilePropertyManager.getPropertyString("WriteFile.link")));
    }

    @Test
    public void connectionTestWithParam() {
        ConnectionManager manager = new ConnectionManager("Anonim123", "123456", 12);
        //ConnectionManager manager = new ConnectionManager("Anonim", "123456", 12);
        assertTrue(manager.autentificate(FilePropertyManager.getPropertyString("WriteFile.link")));
    }

    @Test
    public void getConnectionTest() {
        ConnectionManager manager = new ConnectionManager("dfg", "fgdf");
        manager.closeHttpClien();

    }

    @Test(expected = java.lang.NullPointerException.class)
    public void getConnectionTestNull() {
        ConnectionManager manager = new ConnectionManager();
        assertFalse(manager.autentificate("fgjdghjgdhfgjdghjfghjhdfghghj"));
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void getConnectionTestNullClose() {
        ConnectionManager manager = new ConnectionManager();
        manager.closeHttpClien();
    }
}
