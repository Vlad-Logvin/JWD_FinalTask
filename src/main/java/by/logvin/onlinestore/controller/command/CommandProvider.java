package by.logvin.onlinestore.controller.command;

import by.logvin.onlinestore.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.SAVE_NEW_USER, new SaveNewUser());
        commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPage());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        commands.put(CommandName.LOGOUT, new Logout());
    }


    public Command takeCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
    }
}
