package de.amin.freecam.labymod;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;

public class MixinLoader implements IClassTransformer {

    private static final String MIXIN_CONFIG = "mixins.freecam.json";

    public MixinLoader() throws ReflectiveOperationException, IOException {
        File mixinLibrary = new File("libraries/org/spongepowered/mixin/0.7.11/mixin-0.7.11.jar");
        if (!mixinLibrary.exists()) {
            // Download library
            mixinLibrary.getParentFile().mkdirs();
            HttpsURLConnection c = (HttpsURLConnection) new URL("https://repo.spongepowered.org/repository/maven-public/org/spongepowered/mixin/0.7.11-SNAPSHOT/mixin-0.7.11-20180703.121122-1.jar").openConnection();
            c.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
            Files.copy(c.getInputStream(), mixinLibrary.toPath());
        }

        // Add jar file to parent of LaunchClassLoader
        Field parent = Launch.classLoader.getClass().getDeclaredField("parent");
        parent.setAccessible(true);
        Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        addURL.setAccessible(true);
        addURL.invoke(parent.get(Launch.classLoader), mixinLibrary.toURI().toURL());
        addURL.invoke(Launch.classLoader, mixinLibrary.toURI().toURL());

        // Initialize Mixin
        MixinBootstrap.init();
        Mixins.addConfiguration(MIXIN_CONFIG);
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        return basicClass;
    }

}