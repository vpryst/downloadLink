package downloadUrl;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class CommandsManager {

	private String name = "";
	private String pass = "";

	public CommandsManager(String[] args) {
		Options options = new Options();

		options.addOption("l", true, "login");
		options.addOption("p", true, "password");

		CommandLineParser parser = new PosixParser();
		CommandLine cmd;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("l") && cmd.hasOption("p")) {
				setName(cmd.getOptionValue("l"));
				setPass(cmd.getOptionValue("p"));
			} else if (cmd.hasOption("v")) {
				setName("Anonim123");
				setPass("123456");
			}
		} catch (ParseException e) {
			// e.printStackTrace();
			Log.LOGGER_COMMANDS_MANAGER.error(e.getMessage());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
