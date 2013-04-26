package org.vpryst.downloadLinkTest;

import org.testng.annotations.Test;
import org.vpryst.downloadLink.ConnectionManager;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class ConnectionManagerTest {
    @Test
    public void connectionTestWithoutParam() {
        ConnectionManager manager = new ConnectionManager();
        manager.autentificate(null, null, null);
    }

    @Test
    public void connectionTestWithParam() {
        ConnectionManager manager = new ConnectionManager();
        manager.autentificate("http://refcardz.dzone.com/", "", "");
    }

    @Test
    public void getConnectionTest() {
        ConnectionManager manager = new ConnectionManager();
        manager.getConnection();
    }
    
    @Test
    public void getConnectionTestMock() {
        ConnectionManager man = new ConnectionManager();
        ConnectionManager manager = mock(ConnectionManager.class);
        
        //assertEquals(manager.autentificate("http://refcardz.dzone.com", "dfsdfgsd", "sdfsdf"),true);
        assertTrue(man.autentificate("http://refcardz.dzone.com", "Anonim123", "123456"));
        
    }
    
}
