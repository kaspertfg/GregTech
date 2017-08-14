package gregtech.loaders.oreprocessing;

import gregtech.api.GT_Values;
import gregtech.api.unification.OreDictionaryUnifier;
import gregtech.api.unification.ore.IOreRegistrationHandler;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.SimpleItemStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingRecycling implements IOreRegistrationHandler {
    public ProcessingRecycling() {
        for (OrePrefix tPrefix : OrePrefix.values())
            if ((tPrefix.mIsMaterialBased) && (tPrefix.mMaterialAmount > 0L) && (tPrefix.mIsContainer))
                tPrefix.add(this);
    }
    
    public void registerOre(UnificationEntry uEntry, String modName, SimpleItemStack simpleStack) {
        ItemStack stack = simpleStack.asItemStack();
        if ((uEntry.material != null) && (GT_Utility.getFluidForFilledItem(stack, true) == null))
            GT_Values.RA.addCannerRecipe(stack, null, GT_Utility.getContainerItem(stack, true), OreDictionaryUnifier.get(OrePrefix.dust, uEntry.material, uEntry.orePrefix.mMaterialAmount / 3628800L), (int) Math.max(uEntry.material.getMass() / 2L, 1L), 2);
    }
}
