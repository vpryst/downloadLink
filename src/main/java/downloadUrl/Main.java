package downloadUrl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        FileOutputStream fileOutput = null;
        String temp = "";
        //HashMap <String, String> Links = new HashMap<String, String>(); 
        HashSet<String> Links = new HashSet<String>();
        
        try {
            fileOutput = new FileOutputStream("temp.html");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        OutputStreamWriter outputWriter = new OutputStreamWriter(fileOutput);
        BufferedWriter bufferWriter = new BufferedWriter(outputWriter);
        BufferedReader bufferReader;
        String getReadLine;

        ConnectionUrl con = new ConnectionUrl();
        //bufferReader = con.setUrl("");

        try {
          /*  while ((getReadLine = bufferReader.readLine()) != null) {
                if (getReadLine.indexOf("form_build_id") >= 0) {
                    System.out.println(getReadLine);
                    if (i == 1) {
                        System.out.println(getReadLine.substring(46, 83));
                        temp = getReadLine.substring(46, 83);
                    }
                    i++;
                }
                bufferWriter.write(getReadLine);
                bufferWriter.newLine();
            }*/

            

            bufferReader = con.setAutentificate(temp);
            
            
            bufferReader = con.setUrl("http://refcardz.dzone.com");
            while ((getReadLine = bufferReader.readLine()) != null) {
                int from, to;
                if ((from = getReadLine.indexOf("/assets/request")) >= 0) {
                    to = getReadLine.indexOf("direct=true")+11;
                    System.out.println(getReadLine);
                    System.out.println(from + " " + to);
                    System.out.println(getReadLine.substring(from, to));
                    Links.add(getReadLine.substring(from, to));
                }
                bufferWriter.write(getReadLine);
                bufferWriter.newLine();
            }
            Iterator <String> it = Links.iterator(); 
            while (it.next()!=null) {
                con.getLink(it.next());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        con.setManagerShutDown();
    }

}
