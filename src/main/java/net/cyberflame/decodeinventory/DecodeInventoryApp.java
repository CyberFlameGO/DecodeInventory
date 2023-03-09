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

        decodeInventory("rO0ABXcEAAAACXNyABpvcmcuYnVra2l0LnV0aWwuaW8uV3JhcHBlcvJQR+zxEm8FAgABTAADbWFwdAAPTGphdmEvdXRpbC9NYXA7eHBzcgA1Y29tLmdvb2dsZS5jb21tb24uY29sbGVjdC5JbW11dGFibGVNYXAkU2VyaWFsaXplZEZvcm0AAAAAAAAAAAIAAkwABGtleXN0ABJMamF2YS9sYW5nL09iamVjdDtMAAZ2YWx1ZXNxAH4ABHhwdXIAE1tMamF2YS5sYW5nLk9iamVjdDuQzlifEHMpbAIAAHhwAAAAA3QAAj09dAABdnQABHR5cGV1cQB+AAYAAAADdAAeb3JnLmJ1a2tpdC5pbnZlbnRvcnkuSXRlbVN0YWNrc3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAADDB0AAlXSElURV9CRURzcQB+AABzcQB+AAN1cQB+AAYAAAAEcQB+AAhxAH4ACXEAfgAKdAAEbWV0YXVxAH4ABgAAAARxAH4ADHNxAH4ADQAADDB0AA9ESUFNT05EX1BJQ0tBWEVzcQB+AABzcQB+AAN1cQB+AAYAAAAFcQB+AAh0AAltZXRhLXR5cGV0AAxkaXNwbGF5LW5hbWV0AAhlbmNoYW50c3QAC3JlcGFpci1jb3N0dXEAfgAGAAAABXQACEl0ZW1NZXRhdAAKVU5TUEVDSUZJQ3QAFnsidGV4dCI6IkdvbGQgRGlnZ2VyIn1zcQB+AAN1cQB+AAYAAAAEdAAJRElHX1NQRUVEdAARTE9PVF9CT05VU19CTE9DS1N0AAdNRU5ESU5HdAAKRFVSQUJJTElUWXVxAH4ABgAAAARzcQB+AA0AAAAFc3EAfgANAAAAA3NxAH4ADQAAAAFxAH4AK3NxAH4ADQAAAA9zcQB+AABzcQB+AAN1cQB+AAYAAAA...RlcmlhbHVxAH4ABgAAAARxAH4AIHQAC1RJTEVfRU5USVRZdABoSDRzSUFBQUFBQUFBLytOaVlPQmk0SFBLeVUvT2RzMHJ5U3lwREVsTTUyQmd5a3hoRU0zTnpFdE5Ma3BNSzdFcXppak55VTR0aWsvS3IrQmtZUFVzU2MwdFpvQUFBQmZCb1BrL0FBQUFxAH4AVnNxAH4AAHNxAH4AA3VxAH4ABgAAAARxAH4ACHEAfgAJcQB+AApxAH4AFHVxAH4ABgAAAARxAH4ADHNxAH4ADQAADDBxAH4AVnNxAH4AAHNxAH4AA3VxAH4ABgAAAARxAH4ACHEAfgAbcQB+AFpxAH4AW3VxAH4ABgAAAARxAH4AIHEAfgBddABoSDRzSUFBQUFBQUFBLytOaVlPQmk0SFBLeVUvT2RzMHJ5U3lwREVsTTUyQmd5a3hoRU0zTnpFdE5Ma3BNSzdFcXppak55VTR0aWsvS3IrQmtZUFVzU2MwdFpvQUFBQmZCb1BrL0FBQUFxAH4AVnBzcQB+AABzcQB+AAN1cQB+AAYAAAAEcQB+AAhxAH4ACXEAfgAKcQB+ABR1cQB+AAYAAAAEcQB+AAxzcQB+AA0AAAwwcQB+AFZzcQB+AABzcQB+AAN1cQB+AAYAAAAEcQB+AAhxAH4AG3EAfgBacQB+AFt1cQB+AAYAAAAEcQB+ACBxAH4AXXQA1Eg0c0lBQUFBQUFBQS8rTmlZT0JpNEhQS3lVL09kczByeVN5cERFbE01MkJneWt4aEVNM056RXROTGtwTUs3RXF6aWpOeVU0dGlrL0tyK0JrWVBVc1NjMHQ1bUlBYW1Sa1lBbk95UzloWUdSZ2RjNHZ6U3R4Z09qa1EraE15U3dDU1lPVk1SS25qSWs0WmN6RUtXTWhUaGtyY2NyWWlGUEdUcHd5RHBneVJvd1FUeTlLTEM2T1R3SkZDMHcxSjB5MUhIWkRHUmdBOUY1V3Nzd0JBQUE9cQB+AFZw");
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
