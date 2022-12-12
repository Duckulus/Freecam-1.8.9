package de.amin.freecam.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public interface Command {

    /**
     * executes this command
     * @return true if succesful, false if not
     */
    boolean onCommand(Minecraft minecraft, EntityPlayerSP player, String commandName, String[] args);

}
