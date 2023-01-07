package de.amin.freecam.labymod;

import de.amin.freecam.FreecamConfig;
import de.amin.freecam.FreecamMod;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;

import java.util.List;

public class FreecamAddon extends LabyModAddon {
    @Override
    public void onEnable() {
        FreecamMod.getInstance().init();
        getApi().registerForgeListener(FreecamMod.getInstance());
    }

    @Override
    public void loadConfig() {
        FreecamConfig config = FreecamMod.getInstance().getConfig();
        if (getConfig().has("noClip")) config.setNoClip(getConfig().get("noClip").getAsBoolean());
        if (getConfig().has("showHand")) config.setShowHand(getConfig().get("showHand").getAsBoolean());
        if (getConfig().has("flySpeed")) config.setFlySpeed(getConfig().get("flySpeed").getAsFloat());

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        list.add(new BooleanElement("NoClip", new ControlElement.IconData(Material.GHAST_TEAR), value -> {
            getConfig().addProperty("noClip", value);
            FreecamMod.getInstance().getConfig().setNoClip(value);
        }, FreecamMod.getInstance().getConfig().isNoClip()));

        list.add(new BooleanElement("Show Hand", new ControlElement.IconData(Material.TORCH), value -> {
            getConfig().addProperty("showHand", value);
            FreecamMod.getInstance().getConfig().setShowHand(value);
        }, FreecamMod.getInstance().getConfig().isShowHand()));

        FloatSliderElement flySpeedSlider = new FloatSliderElement("Flight Speed", new ControlElement.IconData(Material.FEATHER), FreecamMod.getInstance().getConfig().getFlySpeed());
        flySpeedSlider.setRange(0.01f, 0.2f);
        flySpeedSlider.addCallback(value -> {
            getConfig().addProperty("flySpeed", value);
            FreecamMod.getInstance().getConfig().setFlySpeed(value);
        });
        list.add(flySpeedSlider);
    }


}
