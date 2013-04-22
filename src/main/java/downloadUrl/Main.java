package downloadUrl;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
	public static void main(String[] args) {
		Log logg = new Log();
		CommandsManager command = new CommandsManager(args);
		WriteFile files = new WriteFile(command);
	}

}
