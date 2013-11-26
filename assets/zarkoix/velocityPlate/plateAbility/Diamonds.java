package assets.zarkoix.velocityPlate.plateAbility;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zarkoix.velocityPlate.PlateAbility;

public class Diamonds extends PlateAbility {
	@Override
	public void active (World par1World, int par2, int par3, int par4, Entity par5Entity){
		if(par5Entity instanceof EntityPlayer){
			((EntityPlayer) par5Entity).inventory.addItemStackToInventory(new ItemStack(Item.diamond, 64)); 
		}
	}	
	@Override
	public String name(){
		return "Diamonds";
	}
}
