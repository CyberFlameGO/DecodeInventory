package net.cyberflame.decodeinventory;

import com.dumptruckman.bukkit.configuration.json.JsonConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

public class DecodeInventoryApp extends JavaPlugin
{
    private static DecodeInventoryApp plugin;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("b64-str", "insert string here");
        config.options().copyDefaults(true);
        saveConfig();
        DecodeInventoryApp.plugin = this;
        Objects.requireNonNull(getCommand("decodedgive")).setExecutor(new DecodeCommand());
    }

    @SuppressWarnings("FinalStaticMethod")
    public static final DecodeInventoryApp getInstance()
    {
        return DecodeInventoryApp.plugin;
    }


    public static ItemStack[] decodeInventory(String data) throws IOException {
        try
            {
                byte[] rawData = Base64.getDecoder().decode(data);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(rawData);
                BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
                ItemStack[] items = new ItemStack[dataInput.readInt()];
                for (int i = 0; i < items.length; i++)
                    {
                        items[i] = (ItemStack) dataInput.readObject();
                    }
                dataInput.close();
                return items;
            }
        catch (ClassNotFoundException | IOException e)
            {
                throw new IOException("Unable to decode class type.", e);
            }
    }
}
