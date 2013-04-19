package downloadUrl;

import java.util.HashSet;
import java.util.Iterator;

public class WriteFile {
	public static void main(String[] args) {
		HashSet<String> map = new HashSet<String>();
		Iterator<String> it = null;
		int tmp = 1;
		ConnectionUrl con = new ConnectionUrl();
		con.setAutentificate();
		map = con.getUrlLink();
		it = map.iterator();
		while (it.hasNext()) {
		    con.saveFiles(it.next(),String.valueOf(tmp));
		    //System.out.println(it.next());
		    tmp++;
		    
		}
		System.out.println(map.size());
		//con.saveFiles("http://cdn.dzone.com/sites/all/files/refcardz/rc171-010d-MongoDB_1.pdf","1");
		con.setManagerShutDown();
	}

}
