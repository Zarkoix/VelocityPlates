package zarkoix.velocityPlate;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler{

        // Client stuff
        public void registerRenderers() {
                // Nothing here as the server doesn't render graphics or entities!
        }
        
        @Override
    	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        	//return containers
    		switch(ID){
    		case 90:
    			
    		}
    		return null;
    	}

    	@Override
    	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {  		
    		return null;
    	}

}