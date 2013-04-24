package org.vpryst.downloadLink;

import java.util.HashSet;
import java.util.Iterator;

import org.apache.log4j.Logger;

public class WriteFile {
    
    private final Logger logger = Logger.getLogger(WriteFile.class);
	private HashSet<String> map = new HashSet<String>();
	private Iterator<String> it = null;
	private int tmp = 1;
	private ConnectionUrl con = new ConnectionUrl();

	public WriteFile(CommandsManager comand) {
		/*
		 * con.Autentificate("http://refcardz.dzone.com", comand.getName(),
		 * comand.getPass()); map = con.getUrlLink("http://refcardz.dzone.com");
		 * it = map.iterator(); while (it.hasNext()) {
		 * con.saveFiles(it.next(),String.valueOf(tmp));
		 * //System.out.println(it.next()); tmp++; }
		 */
		logger.info(Messager.getString("org.vpryst.download.WriteFile.startWritefile"));
		System.out.println(map.size());
		// con.saveFiles("http://cdn.dzone.com/sites/all/files/refcardz/rc171-010d-MongoDB_1.pdf","1");
		logger.info(Messager.getString("org.vpryst.download.WriteFile.endWritefile"));
		// con.ManagerShutDown();
	}
}
