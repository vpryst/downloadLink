package downloadUrl;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	protected static final Logger LOGGER_CONNECTION_URL = Logger.getLogger(ConnectionUrl.class);
	protected static final Logger LOGGER_WRITE_FILE = Logger.getLogger(WriteFile.class);
	protected static final Logger LOGGER_COMMANDS_MANAGER = Logger.getLogger(CommandsManager.class);  
	public Log() {
		PropertyConfigurator.configure("log4j.properties");
	}
}
