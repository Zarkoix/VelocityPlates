package zarkoix.velocityPlate;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class PlateTileEntity extends TileEntity{
	public String ability;

	   @Override
	   public void writeToNBT(NBTTagCompound par1)
	   {
		   System.out.println("writing to nbt");
	      super.writeToNBT(par1);
	      par1.setString(ability, "ability");
	   }

	   @Override
	   public void readFromNBT(NBTTagCompound par1)
	   {
		  System.out.println("reading from nbt");
	      super.readFromNBT(par1);
	      ability = par1.getString("ability");
	   }
	}

