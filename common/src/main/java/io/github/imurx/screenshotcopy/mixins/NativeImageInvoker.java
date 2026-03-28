package io.github.imurx.screenshotcopy.mixins;

import com.mojang.blaze3d.platform.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(NativeImage.class)
public interface NativeImageInvoker {

    @Invoker("getPixelABGR")
    int invokeGetColor(int x, int y);
}
