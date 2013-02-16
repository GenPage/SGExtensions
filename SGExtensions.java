package sgextensions;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import gcewing.sg.SGCraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid="teamautomod_SGExtensions",name="SG Extensions",version = "pre1")
@NetworkMod(clientSideRequired = true,serverSideRequired = true)
public class SGExtensions
{
	@SidedProxy(clientSide="sgextensions.ClientProxy",serverSide = "sgextensions.CommonProxy")
	public static CommonProxy proxy;
	public static Block diallerBlock;

	@Mod.PreInit()
	public void preInit(FMLPreInitializationEvent e){
		ConfigHandler.loadConfig(e);
		diallerBlock = new DiallerBlock(ConfigHandler.blockDiallerID,0).setBlockName("diallerblock");
	}
	@Mod.Init
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderThings();
		GameRegistry.registerBlock(diallerBlock, "blockDialler");
		GameRegistry.registerTileEntity(TileDialler.class, "tileDialler");
		GameRegistry.addShapedRecipe(new ItemStack(diallerBlock,1),"III","RDR","III",'I', Item.ingotIron,'R',Item.redstone,'D', SGCraft.sgControllerCrystal);
		LanguageRegistry.addName(diallerBlock,"Dialling Computer");
	}
}
