package downloadUrl;

import java.util.HashSet;
import java.util.Iterator;

public class WriteFile {
    private HashSet<String> map = new HashSet<String>();
    private Iterator<String> it = null;
    private int tmp = 1;
    private ConnectionUrl con = new ConnectionUrl();
    
    public WriteFile(CommandsManager comand) {
        /*con.Autentificate("http://refcardz.dzone.com", comand.getName(), comand.getPass());
        map = con.getUrlLink("http://refcardz.dzone.com");
        it = map.iterator();
        while (it.hasNext()) {
            con.saveFiles(it.next(),String.valueOf(tmp));
            //System.out.println(it.next());
            tmp++;       
        }*/
        System.out.println(map.size());
        //con.saveFiles("http://cdn.dzone.com/sites/all/files/refcardz/rc171-010d-MongoDB_1.pdf","1");
        //con.ManagerShutDown();
    }
}
