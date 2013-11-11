package zarkoix.velocityPlate;


import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.EnumMobType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Plate extends BlockPressurePlate{
	public Plate(int par1, Material par2Material) {
		super(par1, null, par2Material, EnumMobType.everything);
	}

	public String plateType;
	public String standard;
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
		PlateTileEntity tile = (PlateTileEntity) par1World.getBlockTileEntity(par2, par3, par4);
		if (tile != null)
		{
		   plateType = tile.ability;
		}
		
			if (plateType != null){
				try {
					Class a = Class.forName(plateType);
					if(a.getSuperclass() == PlateAbility.class){
						
						Class<PlateAbility> b = (Class<PlateAbility>) a;
						PlateAbility c = b.newInstance();
						c.active(par1World, par2, par3, par4, par5Entity);
						
						}else{System.out.println("superClass of plateType is not PlateAbility very strange");}
					
						}catch (ClassNotFoundException e) {}
						 catch (InstantiationException e) {}
						 catch (IllegalAccessException e) {}

				}
			}	
   
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		System.out.println("block activated");
        	//open GUI
        	par5EntityPlayer.openGui(VelocityPlate.instance, 90, par1World, par2, par3, par4);
        	return true;
        
    }
@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		System.out.println("createTileEntity called");
	   return new PlateTileEntity();
	}
@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}
}
  


