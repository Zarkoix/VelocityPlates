package zarkoix.velocityPlate.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import zarkoix.velocityPlate.CommonProxy;
import zarkoix.velocityPlate.gui.PlateSwitchGUI;

public class ClientProxy extends CommonProxy {
        
        @Override
        public void registerRenderers() {}
        
        @Override
        public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){ 
        	System.out.println("finding client GUI");
        	//return gui
        		switch(ID){
        			case 90:
        				System.out.println("Found gui at case: " + ID);
        				return new PlateSwitchGUI(player, world, x, y, z);
        					
        		}
        		return null;
        
}
}