package io.github.imurx.screenshotcopy;

import io.github.imurx.arboard.ImageData;
import io.github.imurx.screenshotcopy.mixins.NativeImageInvoker;
import net.minecraft.client.texture.NativeImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.imurx.arboard.Clipboard;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class ScreenshotCopy {
    public static final String MOD_ID = "screencopy";
    private static final Logger LOGGER = LoggerFactory.getLogger("Screencopy");
    private static Clipboard clipboard;
    public static void init() {
        if(clipboard != null) {
            LOGGER.warn("Someone tried to init me again", new IllegalStateException("Clipboard is already defined, can't init it again"));
            return;
        }
        clipboard = new Clipboard();
    }

    public static void stop() {
        clipboard.close();
        clipboard = null;
    }

    public static void copyScreenshot(NativeImage image) {
        NativeImageInvoker invoker = ((NativeImageInvoker) (Object) image);
        ByteBuffer imageBytes = ByteBuffer.allocate(image.getWidth() * image.getHeight() * 4).order(ByteOrder.LITTLE_ENDIAN);
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                imageBytes.putInt(invoker.invokeGetColor(x, y));
            }
        }
        copyScreenshot(image.getWidth(), image.getHeight(), imageBytes.array());
    }

    public static void copyScreenshot(int width, int height, byte[] array) {
        try(ImageData data = new ImageData(width, height, array)) {
            clipboard.setImage(data);
        }
    }
}
