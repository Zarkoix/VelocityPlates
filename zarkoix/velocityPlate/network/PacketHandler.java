package zarkoix.velocityPlate.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import zarkoix.velocityPlate.VelocityPlate;
import zarkoix.velocityPlate.tileEntities.PlateTileEntity;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

        @Override
        public void onPacketData(INetworkManager manager,
                        Packet250CustomPayload packet, Player playerEntity) {
        	System.out.println("Packet recieved at handler " + packet.channel);
        	net.minecraft.entity.player.EntityPlayer player = (net.minecraft.entity.player.EntityPlayer) playerEntity;
        	
               if(packet.channel.equals("VP:setPAServer")){
            	   handleSetPlateServer(packet, player);
               }
               if(packet.channel.equals("VP:setPAClient")){
            	   handleSetPlateClient(packet, player);
               }
        }

		private void handleSetPlateServer(Packet250CustomPayload packet, net.minecraft.entity.player.EntityPlayer player) {
			System.out.println("Packet Recieved @ server, channel: VP:setPAServer");
			
			DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
            
            int xCoord;
            int yCoord;
            int zCoord;
            int ability;
            World world = player.worldObj;
            
            
            try {
                    xCoord = inputStream.readInt();
                    yCoord = inputStream.readInt();
                    zCoord = inputStream.readInt();
                    ability = inputStream.readInt();
                    PlateTileEntity tile = (PlateTileEntity) world.getBlockTileEntity(xCoord, yCoord, zCoord);
                    tile.setAbility(VelocityPlate.modules.get(ability));
            } catch (IOException e) {
                    e.printStackTrace();
                    return;
            }
            
            System.out.println("Forwarding packet");
            packet.channel = "VP:setPAClient";
            PacketDispatcher.sendPacketToPlayer(packet, (Player) player); 
			
		}

		private void handleSetPlateClient(Packet250CustomPayload packet, net.minecraft.entity.player.EntityPlayer player){
			DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
            
            int xCoord;
            int yCoord;
            int zCoord;
            int ability;
            World world = player.worldObj;
            
            
            try {
                    xCoord = inputStream.readInt();
                    yCoord = inputStream.readInt();
                    zCoord = inputStream.readInt();
                    ability = inputStream.readInt();
                    PlateTileEntity tile = (PlateTileEntity) world.getBlockTileEntity(xCoord, yCoord, zCoord);
                    tile.setAbility(VelocityPlate.modules.get(ability));
            } catch (IOException e) {
                    e.printStackTrace();
                    return;
            }
		}

}