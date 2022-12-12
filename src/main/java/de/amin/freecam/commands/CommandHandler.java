package de.amin.freecam.commands;

import de.amin.freecam.Wrapper;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

public class CommandHandler {

    public static String PREFIX = "-";
    private HashMap<String, Command> commands;

    public CommandHandler() {
        commands = new HashMap<>();
    }

    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    public boolean handleMessage(String message) {
        if(!message.startsWith(PREFIX)) return false;
        String commandName = message.substring(1).split(" ")[0];
        String[] args = message.length()>=commandName.length()+2 ? message.substring(commandName.length()+2).split(" ") : new String[0];
        Command command = commands.get(commandName);
        if(command!=null) {
            command.onCommand(Minecraft.getMinecraft(), Minecraft.getMinecraft().thePlayer, commandName, args);
        }else {
            Wrapper.sendMessage("Command not found");
        }
        return true;

    }

}
