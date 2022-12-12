package de.amin.freecam.commands.impl;

import de.amin.freecam.FreecamMod;
import de.amin.freecam.commands.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class FreeCamCommand implements Command {

    @Override
    public boolean onCommand(Minecraft mc, EntityPlayerSP player, String commandName, String[] args) {
        FreecamMod.getInstance().toggleFreecam();
        return true;
    }
}
