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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.FMLCommonHandler;

public class PacketHandler implements IPacketHandler {

        @Override
        public void onPacketData(INetworkManager manager,
                        Packet250CustomPayload packet, Player playerEntity) {
        	System.out.println("Packet recieved at handler " + packet.channel);
        	net.minecraft.entity.player.EntityPlayer player = (net.minecraft.entity.player.EntityPlayer) playerEntity;
        	Side side = FMLCommonHandler.instance().getEffectiveSide();
               if(side == Side.SERVER){
            	   handleSetPlateServer(packet, player);
               }
               if(side == Side.CLIENT){
            	   handleSetPlateClient(packet, player);
               }
        }

		private void handleSetPlateServer(Packet250CustomPayload packet, net.minecraft.entity.player.EntityPlayer player) {
			System.out.println("Packet Recieved @ server");
			
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
            packet.channel = "VP:sync";
            PacketDispatcher.sendPacketToAllPlayers(packet);
			
		}

		private void handleSetPlateClient(Packet250CustomPayload packet, net.minecraft.entity.player.EntityPlayer player){
			System.out.println("Packet Recieved @ client");
			
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