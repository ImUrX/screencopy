package io.github.imurx.screenshotcopy.mixins;

import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(NativeImage.class)
public interface NativeImageInvoker {

    @Invoker("getColor")
    int invokeGetColor(int x, int y);
}
