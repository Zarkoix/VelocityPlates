package zarkoix.velocityPlate;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class PlateTileEntity extends TileEntity{
	private PlateAbility ability = new assets.zarkoix.velocityPlate.plateAbility.Default();
	public String a;
	private static final String tagAbility = "a";
	
	@Override
	public void readFromNBT(NBTTagCompound par0){
		System.out.println("read From NBT: " + par0.getTags());
		this.setAbility(par0.getString(tagAbility));
		super.readFromNBT(par0);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par0){
		System.out.println("Write to nbt");
		this.printAbility();
		par0.setString(tagAbility, ability.getClass().getName());
		System.out.println("	reading set value: " + par0.getString(tagAbility));
		super.writeToNBT(par0);
	}
	
	public void setAbility(String par0){
		System.out.println ("setAbility("+par0+"):");
		try {
			System.out.println("	trying to find Class with name : " + par0);
			Class test = Class.forName(par0);
			System.out.println ("	found class with name : " + par0 + "/" + test.getName());
			if(test.getSuperclass() == PlateAbility.class){
				System.out.println("	class is of type PlateAbility : " + par0 + "/" + test.getName() + " extends " + PlateAbility.class.getName());
				ability = (PlateAbility) test.newInstance();
				System.out.println ("	instantiated class : " + par0 + "/" + test.getName() + " and set to field ability: " + this.ability.name() + " " + this.ability.getClass().getName());
			}else{System.out.println("strange - set ability");}
		} catch (InstantiationException e) {
			System.out.println("InstantiationException?");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException?");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound?");
			e.printStackTrace();
		}
		System.out.println("set ability finished with string " + this.ability.name() + " " + par0);
		this.printAbility();
	}
	public void setAbility(PlateAbility par0){
		System.out.println("setAbility called with a PA");
		this.setAbility(par0.getClass().getName());
	}
	public PlateAbility getAbility(){
		return ability;
	}
	public void printAbility(){
		System.out.println("print ability - " + this.ability.name());
	}
}

