package zarkoix.velocityPlate.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import zarkoix.velocityPlate.PlateAbility;
import zarkoix.velocityPlate.PlateTileEntity;
import zarkoix.velocityPlate.VelocityPlate;

import com.mcf.davidee.guilib.basic.BasicScreen;
import com.mcf.davidee.guilib.basic.Label;
import com.mcf.davidee.guilib.core.Button;
import com.mcf.davidee.guilib.core.Button.ButtonHandler;
import com.mcf.davidee.guilib.core.Container;
import com.mcf.davidee.guilib.vanilla.ButtonVanilla;

import cpw.mods.fml.common.FMLLog;

public class PlateSwitchGUI extends BasicScreen implements ButtonHandler
{
	public PlateSwitchGUI(EntityPlayer par0, World par1World, int x, int y, int z)
	{
		super(null);
		System.out.println("plateswitch gui called");
		tile = (PlateTileEntity) par1World.getBlockTileEntity(x, y, z);
		tileX = x;
		tileY = y;
		tileZ = z;
		world = par1World;
	}

	private World world;
	private int tileX;
	private int tileY;
	private int tileZ;

	private Button selectButton;
	private Button leftButton;
	private Button rightButton;
	private Label selectedLabel;

	private Container container;
;
	private int selectedAbility = 0;
	private PlateTileEntity tile;


	@Override
	public void buttonClicked(Button button) {
		if(button == leftButton){
			System.out.println("decrementing");
			this.decrement();}

		if(button == rightButton){
			System.out.println("incrementing");
			this.increment();}

		if(button == selectButton){
			System.out.println("You clicked on the selection button");
			if(tile != null && VelocityPlate.modules.get(selectedAbility) != null){
				System.out.println("	gui select pressed and conditions met: " + tile.getAbility().name());
				tile.setAbility(VelocityPlate.modules.get(selectedAbility));
				selectedLabel.setText(tile.getAbility().name() + " is selected");
			}else{
				System.out.println("tile is null");
			}
		}
	}



	@Override
	protected void revalidateGui() {
		System.out.println("revalidateGUI");
		selectedLabel.setPosition(width/2, 0);
		selectButton.setPosition(this.width/2 - selectButton.getWidth()/2, this.height/2 - selectButton.getHeight()/2);
		leftButton.setPosition((this.width/3) - 19 , this.height/2 - leftButton.getHeight()/2);
		rightButton.setPosition(((this.width/3)*2) + 19, this.height/2 - rightButton.getHeight()/2);
		container.revalidate(0, 0, width, height);
	}


	@Override
	protected void createGui() {
		System.out.println("createGUI");

		if(tile == null){System.out.println("Very Strange");}

		container = new Container();

		selectedLabel = new Label("No Ability Selected");

		if(tile.getAbility() != null){
			selectedLabel.setText(tile.getAbility().name() + " is selected");
		}

		selectButton = new ButtonVanilla(this.width/3, 20, VelocityPlate.modules.get(selectedAbility).name(), this);
		leftButton = new ButtonVanilla(10, 20, "<", this);
		rightButton = new ButtonVanilla(10, 20, ">", this);

		container.addWidgets(selectButton, leftButton, rightButton, selectedLabel);
		containers.add(container);
	}


	@Override
	protected void reopenedGui() {
	}
	public void updateScreen(){
		super.updateScreen();
		if (mc.thePlayer == null || !mc.thePlayer.isEntityAlive())
			close();

	}
	@Override
	protected void unhandledKeyTyped(char c, int code) {
		if (code == Keyboard.KEY_ESCAPE)
			close();
	}
	@Override
	public void drawBackground() {
		super.drawBackground();
	}
	public void increment(){
		if (selectedAbility == VelocityPlate.modules.size() - 1){
			selectedAbility = 0;
		}else{
			selectedAbility++;
		}
		selectButton.setText(VelocityPlate.modules.get(selectedAbility).name());
	}
	public void decrement(){
		if(selectedAbility == 0){
			selectedAbility = VelocityPlate.modules.size() - 1;
		}else{
			selectedAbility--;
		}
		selectButton.setText(VelocityPlate.modules.get(selectedAbility).name());
	}
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
}