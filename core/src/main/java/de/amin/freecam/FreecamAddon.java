package de.amin.freecam;

import de.amin.freecam.commands.FreecamCommand;
import de.amin.freecam.util.ReflectionHelper;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class FreecamAddon extends LabyAddon<FreecamConfig> {

  @Override
  public void enable() {
    this.registerSettingCategory();
    this.registerCommand(new FreecamCommand());
    ReflectionHelper.initFreecamMod();
  }


  @Override
  protected Class<? extends FreecamConfig> configurationClass() {
    return FreecamConfig.class;
  }

}
