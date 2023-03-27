package de.amin.freecam;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
public class FreecamConfig extends AddonConfig {

  @SwitchSetting
  private ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private ConfigProperty<Boolean> noClip = new ConfigProperty<>(true);

  @SwitchSetting
  private ConfigProperty<Boolean> showHand = new ConfigProperty<>(false);

  @SliderSetting(steps = 0.1f, min = 0.0f, max = 3f)
  private ConfigProperty<Float> flySpeed = new ConfigProperty<>(0.05f);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return enabled;
  }

  public boolean isNoClip() {
    return noClip.get();
  }


  public boolean isShowHand() {
    return showHand.get();
  }


  public float getFlySpeed() {
    return flySpeed.get();
  }

}
