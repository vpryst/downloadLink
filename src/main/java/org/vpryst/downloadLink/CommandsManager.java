package org.vpryst.downloadLink;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

public class CommandsManager {

    private final Logger logger = Logger.getLogger(CommandsManager.class);
    private String name = "";
    private String pass = "";
    
    public static boolean GET_LINK = true;

    /**
     * @param args
     */
    public CommandsManager(String[] args) {
        Options options = new Options();

        options.addOption(Messager.getString("org.vpryst.downloadLink.CommandsManager.l"), true,
            Messager.getString("org.vpryst.downloadLink.CommandsManager.login"));
        options.addOption(Messager.getString("org.vpryst.downloadLink.CommandsManager.p"), true,
            Messager.getString("org.vpryst.downloadLink.CommandsManager.password"));

        CommandLineParser parser = new PosixParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption(Messager.getString("org.vpryst.downloadLink.CommandsManager.l")) &&
                cmd.hasOption(Messager.getString("org.vpryst.downloadLink.CommandsManager.p"))) {
                setName(cmd.getOptionValue(Messager.getString("org.vpryst.downloadLink.CommandsManager.l")));
                setPass(cmd.getOptionValue(Messager.getString("org.vpryst.downloadLink.CommandsManager.p")));
            } else if (cmd.hasOption(Messager.getString("org.vpryst.downloadLink.CommandsManager.linkProperty"))) {
                GET_LINK = false;
            }
        } catch (ParseException e) {
            // e.printStackTrace();
            logger.error(e);
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
