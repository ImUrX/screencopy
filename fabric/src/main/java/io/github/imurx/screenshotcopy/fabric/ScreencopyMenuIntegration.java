package io.github.imurx.screenshotcopy.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.imurx.screenshotcopy.ScreencopyConfig;
import me.shedaniel.autoconfig.AutoConfig;

public class ScreencopyMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(ScreencopyConfig.class, parent).get();
    }
}
