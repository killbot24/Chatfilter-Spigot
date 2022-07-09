package kineticnetwork.net.chatfilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class config {

    public void Config() {
        try {
            String[] list = {"chickon98isachicken, I dont like this", "chickon98isachickena, Its tasty", "chickon98isachickena,You can guess"};
            String url = "Noot noot!";
            ChatFilter.config.options().header("Blocked words can not contain capitals. The following format must be followed \n Word,Reason eg'penguin, he have flipper' \n Method 1 standard mute will use for signs and anvil, Method 2 will only show for sender  ");
            ChatFilter.config.addDefault("Blocked words", (Object) list);
            ChatFilter.config.addDefault("url", url);
            ChatFilter.config.addDefault("Mute Method", 1);
            ChatFilter.config.options().copyDefaults(true);
            ChatFilter.instance.saveConfig();
        } catch (Exception e) {
            // some reason idk why this errors on frist time run
            ChatFilter.instance.getLogger().info("Ah i see this is first time run. Please use /chatfilter reload in game");
        }
    }
}
