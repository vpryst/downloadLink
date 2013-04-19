package downloadUrl;

import java.util.HashSet;

public class WriteFile {
	public static void main(String[] args) {
		HashSet<String> map = new HashSet<String>();
		ConnectionUrl con = new ConnectionUrl();
		con.setAutentificate();
		map = con.getUrlLink();
		con.saveFiles("http://refcardz.dzone.com/assets/request/refcard/2243?oid=rchom2243&direct=true", "1.html");
		con.setManagerShutDown();
	}

}
