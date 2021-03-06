package emasher.modules;

import cpw.mods.fml.common.registry.GameRegistry;
import emasher.api.SideConfig;
import emasher.api.SocketModule;
import emasher.api.SocketTileAccess;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class ModPiezo extends SocketModule {

	public ModPiezo( int id ) {
		super( id, "eng_toolbox:piezoElectric" );
	}

	@Override
	public String getLocalizedName() {
		return "Piezo Electric Tile";
	}
	
	@Override
	public void getToolTip( List l ) {
		l.add( "Generates power when steppend on by entities" );
		l.add( "Has a 10 tick cooldown time (~0.5 seconds)" );
		l.add( "Randomly damages entities that walk on it" );
	}
	
	@Override
	public void getIndicatorKey( List l ) {
		l.add( emasher.util.Config.PREF_AQUA() + "Generates 80 RF/step" );
		l.add( "Can only be placed on the top of a socket" );
	}
	
	@Override
	public void addRecipe() {
		GameRegistry.addShapedRecipe( new ItemStack( emasher.items.Items.module(), 1, moduleID ), "eie", "pbp", Character.valueOf( 'i' ), Blocks.stone_pressure_plate, Character.valueOf( 'p' ), emasher.items.Items.psu(),
				Character.valueOf( 'b' ), emasher.items.Items.blankSide(), 'e', new ItemStack( emasher.items.Items.gem(), 1, 0 ) );
	}
	
	@Override
	public boolean canBeInstalled( SocketTileAccess ts, ForgeDirection side ) {
		if( side != ForgeDirection.UP ) return false;
		return true;
	}
	
	@Override
	public void updateSide( SideConfig config, SocketTileAccess ts, ForgeDirection side ) {
		if( config.meta > 0 ) config.meta--;
	}
	
	@Override
	public void onEntityWalkOn( SocketTileAccess ts, SideConfig config, ForgeDirection side, Entity entity ) {
		if( config.meta <= 0 && side == ForgeDirection.UP && ts.getMaxEnergyStored() - ts.getEnergyStored() >= 80 && entity != null && entity instanceof EntityLiving ) {
			EntityLiving el = ( EntityLiving ) entity;
			ts.addEnergy( 80, false );
			config.meta = 10;
			if( ts.getWorldObj().rand.nextInt( 20 ) == 0 ) el.attackEntityFrom( DamageSource.inFire, 4 );
		}
	}
	
}
