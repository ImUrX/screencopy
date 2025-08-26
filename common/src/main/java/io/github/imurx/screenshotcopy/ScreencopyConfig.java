package io.github.imurx.screenshotcopy;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "screencopy")
public class ScreencopyConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean copyScreenshot = true;

    @ConfigEntry.Gui.Tooltip
    public boolean saveScreenshot = true;

    @ConfigEntry.Gui.Tooltip
    public boolean messageOnCopy = true;
}
