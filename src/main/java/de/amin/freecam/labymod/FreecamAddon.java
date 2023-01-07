package de.amin.freecam.labymod;

import de.amin.freecam.FreecamMod;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;

import java.util.List;

public class FreecamAddon extends LabyModAddon {
    @Override
    public void onEnable() {
        FreecamMod.getInstance().init();
        getApi().registerForgeListener(FreecamMod.getInstance());
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }
}
