package org.vpryst.downloadLinkTest;

import org.testng.annotations.Test;
import org.vpryst.downloadLink.FilePropertyManager;
import static org.testng.Assert.assertEquals;

public class FilePropertyManagerTest {
  @Test
  public void checkParameter() {
      assertEquals(FilePropertyManager.getCommandLineString("CommandsManager.user"),"user");
      assertEquals(FilePropertyManager.getCommandLineString("Commandsnager.user"),"");
      assertEquals(FilePropertyManager.getMessageString("ConnectionUrl.authenticate"),"Authenticate");
      assertEquals(FilePropertyManager.getMessageString("connectionUrl.authenticate"),"");
      assertEquals(FilePropertyManager.getPropertyString("CommandsManager.user"),"qwerty");
      assertEquals(FilePropertyManager.getPropertyString("Commandsnager.user"),"");
  }
}
