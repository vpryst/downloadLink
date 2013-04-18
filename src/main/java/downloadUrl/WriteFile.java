package downloadUrl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;

public class WriteFile {
    private FileOutputStream fileOutput = null;
    // HashMap <String, String> Links = new HashMap<String, String>();
    private HashSet<String> links = new HashSet<String>();
    private OutputStreamWriter outputWriter;
    private BufferedWriter bufferWriter;
    private BufferedReader bufferReader;
    private String getReadLine;
    ConnectionUrl con = null;

    public WriteFile() {

        con = new ConnectionUrl();
        bufferReader = con.setAutentificate();
        try {
                fileOutput = new FileOutputStream("temp.html");
                outputWriter = new OutputStreamWriter(fileOutput);
                bufferWriter = new BufferedWriter(outputWriter);
                while ((getReadLine = bufferReader.readLine()) != null) {
                    bufferWriter.write(getReadLine);
                    bufferWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        /*
         * getLinks(links); getFiles(links);
         */

    }

    public HashSet<String> getLinks() {
        try {
            bufferReader = con.setUrl("http://refcardz.dzone.com",999, "Get Links");
            while ((getReadLine = bufferReader.readLine()) != null) {
                int from, to;
                if ((from = getReadLine.indexOf("/assets/request")) >= 0) {
                    to = getReadLine.indexOf("direct=true") + 11;
                    System.out.println(getReadLine);
                    System.out.println(from + " " + to);
                    System.out.println(getReadLine.substring(from, to));
                    links.add(getReadLine.substring(from, to));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }

    public int getFiles(HashSet<String> mapLinks) {
        String Url;
        int i = 1;
        Iterator<String> it = mapLinks.iterator();
        while (it.next() != null) {
            Url = it.next();
            System.out.println(ConnectionUrl.Url + Url);
            bufferReader = con.setUrl(ConnectionUrl.Url + Url,0,"Get Files");
            //bufferReader = con.setUrl("http://cdn.dzone.com/sites/all/files/refcardz/rc008-designpatterns_online.pdf");
            try {
                fileOutput = new FileOutputStream(i + ".pdf");
                outputWriter = new OutputStreamWriter(fileOutput);
                bufferWriter = new BufferedWriter(outputWriter);
                while ((getReadLine = bufferReader.readLine()) != null) {
                    bufferWriter.write(getReadLine);
                    bufferWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
        return 1;
    }

    public void getFileinputStream() {
        /*
         * FileInputStream inFile = null; String tmp = ""; try { //inFile = new
         * FileInputStream("http://refcardz.dzone.com/assets/request/refcard/3091?oid=rchom3091&direct=true"); URL url = new
         * URL("http://cdn.dzone.com/sites/all/files/refcardz/rc008-designpatterns_online.pdf"); System.out.println(url.getFile()); inFile =
         * new FileInputStream(url.getFile()); InputStreamReader streamReader = new InputStreamReader(inFile); BufferedReader bufer = new
         * BufferedReader(streamReader); org.apache.commons.io.FileUtils.copyURLToFile(url, inFile); while ((tmp = bufer.readLine()) !=
         * null) { System.out.println(tmp); } } catch (IOException e) { e.printStackTrace(); }
         */

        /*
         * InputStream in=null; try { in = new URL( "http://refcardz.dzone.com/assets/request/refcard/3091?oid=rchom3091&direct=true"
         * ).openStream(); } catch (MalformedURLException e) { // TODO Auto-generated catch block e.printStackTrace(); } catch (IOException
         * e) { // TODO Auto-generated catch block e.printStackTrace(); } try { InputStreamReader inR = new InputStreamReader( in );
         * BufferedReader buf = new BufferedReader( inR ); String line; while ( ( line = buf.readLine() ) != null ) { System.out.println(
         * line ); } } catch (IOException e) { } finally { try { in.close(); } catch (IOException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); } }
         */
        InputStream in = null;
        try {
            in = new URL("http://refcardz.dzone.com/assets/request/refcard/3091?oid=rchom3091&direct=true").openStream();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            try {
                System.out.println(IOUtils.toString(in));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
            IOUtils.closeQuietly(in);
        }

    }

    public void setShutdonConection() {
        con.setManagerShutDown();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HashSet<String> map = new HashSet<String>();

        WriteFile file = new WriteFile();
        try {

            map = file.getLinks();
            file.getFiles(map);
            //file.getFileinputStream();
        } finally {
            file.setShutdonConection();
        }

    }

}
