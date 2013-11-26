package zarkoix.velocityPlate.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;
import zarkoix.velocityPlate.VelocityPlate;
import zarkoix.velocityPlate.tileEntities.PlateTileEntity;

import com.mcf.davidee.guilib.basic.BasicScreen;
import com.mcf.davidee.guilib.basic.Label;
import com.mcf.davidee.guilib.core.Button;
import com.mcf.davidee.guilib.core.Button.ButtonHandler;
import com.mcf.davidee.guilib.core.Container;
import com.mcf.davidee.guilib.vanilla.ButtonVanilla;

import cpw.mods.fml.common.network.PacketDispatcher;

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
	}

	
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
				System.out.println("Sending Packet");
				PacketDispatcher.sendPacketToServer(this.createPacket(tileX, tileY, tileZ, selectedAbility));
				selectedLabel.setText(VelocityPlate.modules.get(selectedAbility).name() + " is selected");
			}else{
				System.out.println("tile is null");
			}
		}
	}



	private Packet250CustomPayload createPacket(int x, int y, int z, int s) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(16);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
		        outputStream.writeInt(x);
		        outputStream.writeInt(y);
		        outputStream.writeInt(z);
		        outputStream.writeInt(s);
		} catch (Exception ex) {
		        ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "VP:sync";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		
		return packet;
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