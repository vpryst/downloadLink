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
    private String name = "";
    private String pass = "";

    private static boolean isGetLink = true;

    /**
     * @param args Constructor take String array and pase to commands
     */
    public CommandsManager(String[] args) {
        Options options = new Options();

        options.addOption(FilePropertyManager.getCommandLineString("CommandsManager.l"), true,
            FilePropertyManager.getCommandLineString("CommandsManager.login"));
        options.addOption(FilePropertyManager.getCommandLineString("CommandsManager.p"), true,
            FilePropertyManager.getCommandLineString("CommandsManager.password"));
        options.addOption(FilePropertyManager.getCommandLineString("CommandsManager.linkProperty"), false,
            FilePropertyManager.getCommandLineString("CommandsManager.link"));
        options.addOption(FilePropertyManager.getCommandLineString("CommandsManager.helpSh"), false,
            FilePropertyManager.getCommandLineString("CommandsManager.help"));
        CommandLineParser parser = new PosixParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption(FilePropertyManager.getCommandLineString("CommandsManager.l")) &&
                cmd.hasOption(FilePropertyManager.getCommandLineString("CommandsManager.p"))) {
                setName(cmd.getOptionValue(FilePropertyManager.getCommandLineString("CommandsManager.l")));
                setPass(cmd.getOptionValue(FilePropertyManager.getCommandLineString("CommandsManager.p")));
            } else if (cmd.hasOption(FilePropertyManager.getCommandLineString("CommandsManager.linkProperty"))) {
                isGetLink = false;
            } else if (cmd.hasOption(FilePropertyManager.getCommandLineString("CommandsManager.helpSh"))) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp(FilePropertyManager.getCommandLineString("CommandsManager.helpSh"),
                    FilePropertyManager.getCommandLineString("CommandsManager.helpSh"), options,
                    FilePropertyManager.getCommandLineString("CommandsManager.helpSh"));
                System.exit(0);
            }
        } catch (ParseException e) {
            // e.printStackTrace();
            LOGGER.warn(e);
        }
    }

    public String getName() {
        if (name.equals("")) {
            name = FilePropertyManager.getPropertyString("commandsManager.login");
        }
        if (name.equals("")) {
            LOGGER.info("Insert Login and Password");
            System.exit(0);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        if (pass.equals("")) {
            pass = FilePropertyManager.getPropertyString("commandsManager.password");
        }
        if (pass.equals("")) {
            LOGGER.info("Insert Login and Password");
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
