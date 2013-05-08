package org.vpryst.downloadLinkTest;


import org.junit.Test;
import org.vpryst.downloadLink.ParserHtml;
import static org.mockito.Mockito.*;

public class ParseHtmlTest {
  @Test
  public void parse() {
      //ParserHtml pp = new ParserHtml("http://refcardz.dzone.com/");
      ParserHtml pp = mock(ParserHtml.class);
      pp.createNameLinkMap();
  }
}
