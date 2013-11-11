package zarkoix.velocityPlate.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

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
		tileX = x;
		tileY = y;
		tileZ = z;
		world = par1World;
	}

	private World world;
	private int tileX;
	private int tileY;
	private int tileZ;

	private Button select;
	private Button left;
	private Button right;
	private Label selected;

	private Container container;

	private String plate;
	private int selectedAbility = 0;
	private PlateTileEntity tile;


	@Override
	public void buttonClicked(Button button) {
		System.out.println("omg you clicked a button - " + button.getText());
		if(button == left){
			System.out.println("decrementing");
			this.decrement();}
		if(button == right){
			System.out.println("incrementing");
			this.increment();}
		if(button == select)
			System.out.println("You clicked on the selection button");
			System.out.println("TileX: " + tileX + " TileY: " + tileY + " TileZ: " + tileZ);
			PlateTileEntity tile = (PlateTileEntity) world.getBlockTileEntity(tileX, tileY, tileZ);
			if(tile != null){
				tile.ability = VelocityPlate.modules.get(selectedAbility);
				selected.setText("Ability Selected: " + VelocityPlate.modules.get(selectedAbility));
			}
		}
	


	@Override
	protected void revalidateGui() {
		System.out.println("revalidateGUI");
		selected.setPosition(width/2, 0);
		select.setPosition(this.width/2 - select.getWidth()/2, this.height/2 - select.getHeight()/2);
		left.setPosition((this.width/3) - 19 , this.height/2 - left.getHeight()/2);
		right.setPosition(((this.width/3)*2) + 19, this.height/2 - left.getHeight()/2);
		if(selectedAbility != 0){
			selected.setText("Ability Selected: " + VelocityPlate.modules.get(selectedAbility));
		}
		container.revalidate(0, 0, width, height);
	}


	@Override
	protected void createGui() {
		System.out.println("createGUI");
		container = new Container();
		selected = new Label("Please Select An Ability");
		select = new ButtonVanilla(this.width/3, 20, VelocityPlate.modules.get(selectedAbility), this);
		left = new ButtonVanilla(10, 20, "<", this);
		right = new ButtonVanilla(10, 20, ">", this);
		container.addWidgets(select, left, right, selected);
		containers.add(container);
	}


	@Override
	protected void reopenedGui() {
	}
	public void updateScreen(){
		super.updateScreen();
		select.setText(VelocityPlate.modules.get(selectedAbility));
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
	}
	public void decrement(){
		if(selectedAbility == 0){
			selectedAbility = VelocityPlate.modules.size() - 1;
		}else{
			selectedAbility--;
		}
	}
}