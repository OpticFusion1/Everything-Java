package optic_fusion1.everything;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;

public class Everything implements Runnable {

    static {
        try {
            // Makes it so everything outputs as UTF-8
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new InternalError("VM does not support mandatory encoding UTF-8");
        }

        // Creates the plugin directory that pf4j uses and sets the correct env property
        File pluginsDirectory = new File("everything", "plugins");
        if (!pluginsDirectory.exists()) {
            pluginsDirectory.mkdirs();
        }
        System.setProperty("pf4j.pluginsDir", pluginsDirectory.toString());
    }

    @Override
    public void run() {
        handlePlugins();
    }

    /**
     * Loads and enables every PF4J plugin. TODO: Implement a way to pass an instance of Everything to the plugins
     */
    private void handlePlugins() {
        PluginManager pluginManager = new DefaultPluginManager();
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
    }

}
