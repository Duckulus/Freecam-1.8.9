package de.amin.freecam.commands;

import de.amin.freecam.util.ReflectionHelper;
import net.labymod.api.client.chat.command.Command;

public class FreecamCommand extends Command {

  public FreecamCommand() {
    super("freecam");
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (prefix.equalsIgnoreCase("freecam")) {
      ReflectionHelper.toggleFreecam();
    }
    return true;
  }
}
