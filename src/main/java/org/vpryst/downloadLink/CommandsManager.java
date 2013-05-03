package org.vpryst.downloadLink;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

/**
 *  
 */
public class CommandsManager {

    private final Logger LOGGER = Logger.getLogger(CommandsManager.class);

    private final String COMMANDS_MANAGER_USER_KEY = "CommandsManager.user";
    private final String COMMANDS_MANAGER_PASSWORD_KEY = "CommandsManager.password";
    private final String COMMANDS_MANAGER_U = FilePropertyManager.getCommandLineString("CommandsManager.u");
    private final String COMMANDS_MANAGER_USER = FilePropertyManager.getCommandLineString(COMMANDS_MANAGER_USER_KEY);
    private final String USER_DESCRIPTION = FilePropertyManager.getMessageString("CommandsManager.userDescription");
    private final String COMMANDS_MANAGER_P = FilePropertyManager.getCommandLineString("CommandsManager.p");
    private final String COMMANDS_MANAGER_PASSWORD = FilePropertyManager.getCommandLineString(COMMANDS_MANAGER_PASSWORD_KEY);
    private final String PASSWORD_DESCRIPTION = FilePropertyManager.getMessageString("CommandsManager.passwordDescription");
    private final String COMMANDS_MANAGER_FETCH_F = FilePropertyManager.getCommandLineString("CommandsManager.fetchF");
    private final String COMMANDS_MANAGER_FETCH_FILE = FilePropertyManager.getCommandLineString("CommandsManager.fetchFile");
    private final String FETCH_FILE_DESCRIPTION = FilePropertyManager.getMessageString("CommandsManager.fetchFileDescription");
    private final String COMMANDS_MANAGER_H = FilePropertyManager.getCommandLineString("CommandsManager.h");
    private final String COMMANDS_MANAGER_HELP = FilePropertyManager.getCommandLineString("CommandsManager.help");
    private final String HELP_DESCRIPTION = FilePropertyManager.getMessageString("CommandsManager.helpDescription");

    private final String USER_PASSWORD_MESSAGE = "Insert User and Password";

    private String name = "";
    private String pass = "";

    private boolean isGetLink = true;
    private Options options = new Options();
    private String[] args;

    /**
     * @param args Constructor take String array and parse to commands
     */
    public CommandsManager(String[] args) {
        this.args = args;
        checkComanLineArgs();
    }

    /**
     * Check command line arguments.
     */
    public void checkComanLineArgs() {
        options.addOption(COMMANDS_MANAGER_U, COMMANDS_MANAGER_USER, true, USER_DESCRIPTION);
        options.addOption(COMMANDS_MANAGER_P, COMMANDS_MANAGER_PASSWORD, true, PASSWORD_DESCRIPTION);
        options.addOption(COMMANDS_MANAGER_FETCH_F, COMMANDS_MANAGER_FETCH_FILE, false, FETCH_FILE_DESCRIPTION);
        options.addOption(COMMANDS_MANAGER_H, COMMANDS_MANAGER_HELP, false, HELP_DESCRIPTION);
        CommandLineParser parser = new PosixParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption(COMMANDS_MANAGER_U) && cmd.hasOption(COMMANDS_MANAGER_P) || cmd.hasOption(COMMANDS_MANAGER_USER) &&
                cmd.hasOption(COMMANDS_MANAGER_PASSWORD)) {
                setName(cmd.getOptionValue(COMMANDS_MANAGER_U));
                setPass(cmd.getOptionValue(COMMANDS_MANAGER_P));
            } else if (cmd.hasOption(COMMANDS_MANAGER_FETCH_F)) {
                isGetLink = false;
            } else if (cmd.hasOption(COMMANDS_MANAGER_H)) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp(COMMANDS_MANAGER_H, options);
                System.exit(0);
            }
        } catch (ParseException e) {
            // e.printStackTrace();
            LOGGER.warn(e);
        }
    }

    public String getName() {
        if (name.equals("")) {
            name = COMMANDS_MANAGER_U;
        } else if (COMMANDS_MANAGER_USER.equals("")) {
            FilePropertyManager.setProperty(COMMANDS_MANAGER_USER_KEY, name);
        }
        if (name.equals("")) {
            LOGGER.info(USER_PASSWORD_MESSAGE);
            System.exit(0);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        if (pass.equals("")) {
            pass = COMMANDS_MANAGER_PASSWORD;
        } else if (COMMANDS_MANAGER_PASSWORD.equals("")) {
            FilePropertyManager.setProperty(COMMANDS_MANAGER_PASSWORD_KEY, pass);
        }
        if (pass.equals("")) {
            LOGGER.info(USER_PASSWORD_MESSAGE);
            System.exit(0);
        }
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isRequiredFetch() {

        return isGetLink;
    }
}
