package zarkoix.velocityPlate;

import net.minecraft.block.material.Material;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
//import cpw.mods.fml.common.Mod.PreInit;    // used in 1.5.2
//import cpw.mods.fml.common.Mod.Init;       // used in 1.5.2
//import cpw.mods.fml.common.Mod.PostInit;   // used in 1.5.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.util.Arrays;
import java.util.List;
import zarkoix.velocityPlate.blocks.Plate;
import zarkoix.velocityPlate.network.PacketHandler;
import zarkoix.velocityPlate.utils.ClassFinder;

@Mod(modid="VelocityPlateID", name="Velocity Plate", version="0.1.4")
@NetworkMod(clientSideRequired=true, serverSideRequired=true, 
channels={"VP:setPAServer", "VP:setPAClient"}, packetHandler = PacketHandler.class)
public class VelocityPlate {
	public static List<PlateAbility> modules = null;
	
        // The instance of your mod that Forge uses.
        @Instance(value = "VelocityPlateID")
        public static VelocityPlate instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="zarkoix.velocityPlate.client.ClientProxy", serverSide="zarkoix.velocityPlate.CommonProxy")
        public static CommonProxy proxy;
        
        @EventHandler // used in 1.6.2
        //@PreInit    // used in 1.5.2
        public void preInit(FMLPreInitializationEvent event) {
        	ModMetadata modMeta = event.getModMetadata();
    		modMeta.authorList = Arrays.asList(new String[] { "Zarkoix", "GeckoTheGeek42" });
    		modMeta.autogenerated = false;
    		modMeta.credits = "Thanks to Mojang, Forge, and all your support.";
    		modMeta.description = "Pressure plates just for redstone? Not Anymore!";
    		modMeta.url = "Facebook.com";
        	
        }
        
        @EventHandler // used in 1.6.2
        //@Init       // used in 1.5.2
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                System.out.println("Velocity Plate initializing");
                instance = this;
                
                try {
					System.out.println("Beginning search for modules");
					modules = ClassFinder.search("assets.zarkoix.velocityPlate.plateAbility", PlateAbility.class);
					System.out.println("Finished search for modules");
				} catch (InstantiationException e) { 
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			
			
				if(modules != null){
					System.out.println(modules.size() + " module(s) loaded :)");
				}else{System.out.println("Modules is null :(");} 
				
                Plate plate = new Plate(450, Material.iron);
                
                NetworkRegistry.instance().registerGuiHandler(this, proxy);
                GameRegistry.registerTileEntity(zarkoix.velocityPlate.tileEntities.PlateTileEntity.class, "velocityPlate");
                LanguageRegistry.addName(plate, "Plate");
               // MinecraftForge.EVENT_BUS.register(new Listeners());
               //NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandling());
               
                
					        
              
        }
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}