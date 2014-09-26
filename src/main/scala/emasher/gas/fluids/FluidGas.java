package emasher.gas.fluids;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class FluidGas extends Fluid
{
	public int internalID;
	
	public static final String[] NAMES = new String[]{"Natural Gas", "Propellent", "Hydrogen", "Smoke", "Toxic Gas", "Deadly Neurotoxin", "Corossive Gas", "Nether Plasma"};
	
	public FluidGas(String fluidName, Block theBlock, int iID)
	{
		super(fluidName);
		internalID = iID;
		this.setBlock(theBlock);
		this.setStillIcon(theBlock.getBlockTextureFromSide(0));
		this.setFlowingIcon(theBlock.getBlockTextureFromSide(0));
		this.setGaseous(true);
		this.setViscosity(100);
		this.setDensity(-1000);
		this.setUnlocalizedName(fluidName);
		
	}
	
	@Override
	public String getLocalizedName()
	{
		return NAMES[internalID];
	}
	
	@Override
	public IIcon getStillIcon()
    {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        {
            return this.getBlock().getBlockTextureFromSide(0);
        }
        else
        {
            return null;
        }
    }
	
	@Override
    public IIcon getFlowingIcon()
    {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        {
            return this.getBlock().getBlockTextureFromSide(0);
        }
        else
        {
            return null;
        }
    }

}
