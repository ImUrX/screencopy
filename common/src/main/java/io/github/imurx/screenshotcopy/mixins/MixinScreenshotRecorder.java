package io.github.imurx.screenshotcopy.mixins;

import io.github.imurx.screenshotcopy.ScreenshotCopy;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.function.Consumer;

@Mixin(ScreenshotRecorder.class)
public abstract class MixinScreenshotRecorder {
    @Inject(at = @At("HEAD"), method = "method_1661")
    private static void onInnerScreenshot(NativeImage image, File _file, Consumer<Text> _consumer, CallbackInfo _ci) {
        ScreenshotCopy.copyScreenshot(image);
    }
}
