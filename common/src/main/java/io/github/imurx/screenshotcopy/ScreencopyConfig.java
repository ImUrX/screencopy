package io.github.imurx.screenshotcopy;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "screencopy")
public class ScreencopyConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean saveScreenshot = true;
}
