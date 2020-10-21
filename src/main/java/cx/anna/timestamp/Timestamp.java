package cx.anna.timestamp;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "timestamp8346", name="Timestamp Mod", version = "mars")
public class Timestamp
{

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new what());

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onItemTooltips(ItemTooltipEvent event)
    {
        NBTTagCompound nbt1 = event.itemStack.serializeNBT();
        if (nbt1.hasKey("tag")) {
            NBTTagCompound nbt2 = nbt1.getCompoundTag("tag");
            if (nbt2.hasKey("ExtraAttributes")) {
                NBTTagCompound nbt3 = nbt2.getCompoundTag("ExtraAttributes");
                if (nbt3.hasKey("expertise_kills")){
                    event.toolTip.add("\u00a73Expertise Kills: "+nbt3.getInteger("expertise_kills"));
                }
                if (nbt3.hasKey("timestamp")) {
                    String timestamp = nbt3.getString("timestamp").split(" ")[0];
                    String[] timestamp2 = timestamp.split("/");
                    event.toolTip.add("\u00a7dItem Obtained On: "+addzero(timestamp2[1])+"/"+addzero(timestamp2[0])+"/"+"20"+timestamp2[2]);
                }
            }
        }
    }
    private String addzero (String monthday){
        if (monthday.length()==1){
            return "0"+monthday;
        } else {
            return monthday;
        }
    }
}
