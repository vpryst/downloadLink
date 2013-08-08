package org.vpryst.downloadLinkTest;


import org.junit.Test;
import org.vpryst.downloadLink.ConnectionManager;
import org.vpryst.downloadLink.WriteFile;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WriteFileTest {
    @Test(expected = java.lang.NullPointerException.class)
    public void checkMap() {
        ConnectionManager conection = mock(ConnectionManager.class);
        WriteFile file = new WriteFile(conection);
        file.fetchFile(true);
    }
    
    @Test(expected = java.lang.NullPointerException.class)
    public void checkDataLink() {
        ConnectionManager conection = mock(ConnectionManager.class);
        WriteFile file = new WriteFile(conection);
        file.fetchFile(false);
    }

}
