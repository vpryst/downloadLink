package org.vpryst.downloadLinkTest;

import org.testng.annotations.Test;
import org.vpryst.downloadLink.ParserHtml;

public class ParseHtmlTest {
  @Test
  public void parse() {
      ParserHtml pp = new ParserHtml("http://refcardz.dzone.com/");
  }
}
