package zarkoix.velocityPlate;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class PlateTileEntity extends TileEntity{
	public PlateAbility ability;
	public String abilityString;

	   @Override
	   public void writeToNBT(NBTTagCompound par1)
	   {
		   System.out.println("writing to nbt");
	      super.writeToNBT(par1);
	      par1.setString("abilityString", ability.getClass().getName());
	   }

	   @Override
	   public void readFromNBT(NBTTagCompound par1)
	   {
		  System.out.println("reading from nbt");
	      super.readFromNBT(par1);
	      abilityString = par1.getString("abilityString");
	      	this.setAbility(abilityString);
	   }
	   
	   public void setAbility(String par0){
		   try {
			ability = (PlateAbility) Class.forName((par0)).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   public void setAbility(PlateAbility par0){
		   ability = par0;
	   }
}

