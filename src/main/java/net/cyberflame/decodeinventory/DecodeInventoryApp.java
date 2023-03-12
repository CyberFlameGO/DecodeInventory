package net.cyberflame.decodeinventory;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import java.util.Base64;

import com.dumptruckman.bukkit.configuration.json.JsonConfiguration;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DecodeInventoryApp {

    public static void main(String[] args) throws IOException {

        decodeInventory("rO0ABXcEAAAACXNyABpvcmcuYnVra2l0LnV0aWwuaW8uV3JhcHBlcvJQR+zxEm8FAgABTAADbWFwdAAPTGphdmEvdXRpbC9NYXA7eHBzcgA1Y29tLmdvb2dsZS5jb21tb24uY29sbGVjdC5JbW11dGFibGVNYXAkU2VyaWFsaXplZEZvcm0AAAAAAAAAAAIAAkwABGtleXN0ABJMamF2YS9sYW5nL09iamVjdDtMAAZ2YWx1ZXNxAH4ABHhwdXIAE1tMamF2YS5sYW5nLk9iamVjdDuQzlifEHMpbAIAAHhwAAAABHQAAj09dAABdnQABHR5cGV0AAZhbW91bnR1cQB+AAYAAAAEdAAeb3JnLmJ1a2tpdC5pbnZlbnRvcnkuSXRlbVN0YWNrc3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAADDB0AAVTVE9ORXNxAH4ADgAAAEBzcQB+AABzcQB+AAN1cQB+AAYAAAAEcQB+AAhxAH4ACXEAfgAKcQB+AAt1cQB+AAYAAAAEcQB+AA1zcQB+AA4AAAwwcQB+ABFxAH4AEnNxAH4AAHNxAH4AA3VxAH4ABgAAAARxAH4ACHEAfgAJcQB+AApxAH4AC3VxAH4ABgAAAARxAH4ADXNxAH4ADgAADDBxAH4AEXEAfgASc3EAfgAAc3EAfgADdXEAfgAGAAAABHEAfgAIcQB+AAlxAH4ACnEAfgALdXEAfgAGAAAABHEAfgANc3EAfgAOAAAMMHEAfgARcQB+ABJzcQB+AABzcQB+AAN1cQB+AAYAAAAEcQB+AAhxAH4ACXEAfgAKcQB+AAt1cQB+AAYAAAAEcQB+AA1zcQB+AA4AAAwwcQB+ABFxAH4AEnNxAH4AAHNxAH4AA3VxAH4ABgAAAARxAH4ACHEAfgAJcQB+AApxAH4AC3VxAH4ABgAAAARxAH4ADXNxAH4ADgAADDBxAH4AEXEAfgASc3EAfgAAc3EAfgADdXEAfgAGAAAABHEAfgAIcQB+AAlxAH4ACnEAfgALdXEAfgAGAAAABHEAfgANc3EAfgAOAAAMMHEAfgARcQB+ABJzcQB+AABzcQB+AAN1cQB+AAYAAAAEcQB+AAhxAH4ACXEAfgAKcQB+AAt1cQB+AAYAAAAEcQB+AA1zcQB+AA4AAAwwcQB+ABFxAH4AEnNxAH4AAHNxAH4AA3VxAH4ABgAAAARxAH4ACHEAfgAJcQB+AApxAH4AC3VxAH4ABgAAAARxAH4ADXNxAH4ADgAADDBxAH4AEXEAfgAS");
    }

    public static String dumpItem(ItemStack itemStack) {
        JsonConfiguration json = new JsonConfiguration();
        json.set("item", itemStack);
        return json.saveToString();
    }

    public static void decodeInventory(String data) throws IOException {
        try {
            byte[] rawData = Base64.getDecoder().decode(data);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(rawData);
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];
            for (int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) dataInput.readObject();
            }
            dataInput.close();
            for(ItemStack item: items){
                System.out.print(dumpItem(item) + " ");
            }
            System.out.println();
        } catch (ClassNotFoundException | IOException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
}
