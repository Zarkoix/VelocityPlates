package assets.zarkoix.velocityPlate.plateAbility;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import zarkoix.velocityPlate.PlateAbility;


public class Jump extends PlateAbility {
	@Override
	public void active (World par1World, int par2, int par3, int par4, Entity par5Entity){
		par5Entity.setVelocity(par5Entity.motionX, 5.0, par5Entity.motionZ);
	}	
	@Override
	public String name(){
		return "Jump";
	}
	
}
