package io.github.imurx.screenshotcopy;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "screencopy")
public class ScreencopyConfig implements ConfigData {
    @Comment("Save screenshots to file aside from copying to clipboard")
    public boolean saveScreenshot = true;
}
