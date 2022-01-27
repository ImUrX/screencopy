package io.github.imurx.screenshotcopy.fabric.mixins;

import io.github.imurx.screenshotcopy.ScreencopyConfig;
import io.github.imurx.screenshotcopy.ScreenshotCopy;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.function.Consumer;

@Mixin(ScreenshotRecorder.class)
public abstract class MixinScreenshotRecorder {
    @Inject(at = @At("HEAD"), method = "method_1661", cancellable = true)
    private static void onInnerScreenshot(NativeImage image, File _file, Consumer<Text> messageReceiver, CallbackInfo ci) {
        try {
            ScreenshotCopy.copyScreenshot(image);
            if(!AutoConfig.getConfigHolder(ScreencopyConfig.class).getConfig().saveScreenshot) {
                messageReceiver.accept(new TranslatableText("screencopy.success"));
                ci.cancel();
            }
        } catch(Exception ex) {
            messageReceiver.accept(new TranslatableText("screencopy.failure", ex.toString()));
            if(!AutoConfig.getConfigHolder(ScreencopyConfig.class).getConfig().saveScreenshot) ci.cancel();
        }
    }
}
