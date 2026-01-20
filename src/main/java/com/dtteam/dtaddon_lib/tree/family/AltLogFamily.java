package com.dtteam.dtaddon_lib.tree.family;

import com.dtteam.dynamictrees.api.lazyvalue.MutableLazyValue;
import com.dtteam.dynamictrees.api.registry.RegistryHandler;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.branch.BasicBranchBlock;
import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.block.branch.ThickBranchBlock;
import com.dtteam.dynamictrees.data.DTDataProvider;
import com.dtteam.dynamictrees.data.generator.BranchStateGenerator;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.utility.Optionals;
import com.dtteam.dynamictrees.utility.ResourceLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

// From DTBWG
public class AltLogFamily extends Family {

    public static final TypedRegistry.EntryType<Family> TYPE = TypedRegistry.newType(AltLogFamily::new);

    protected Supplier<BranchBlock> altBranch;
    protected Block primitiveAltLog;
    protected final MutableLazyValue<altBranchStateGenerator> altBranchStateGenerator;

    public AltLogFamily(ResourceLocation name) {
        super(name);
        altBranchStateGenerator = MutableLazyValue.supplied(altBranchStateGenerator::new);
    }

    @Override
    public void setupBlocks() {
        super.setupBlocks();

        this.altBranch = setupBranch(createAltBranch(getBranchName("alt_")), false);
    }

    protected BranchBlock createAltBranchBlock(ResourceLocation name) {
        BasicBranchBlock branch = new ThickBranchBlock(name, this.getProperties()){
            @Override
            public Optional<Block> getPrimitiveLog() {
                if (getFamily() instanceof AltLogFamily altLogFamily)
                    return altLogFamily.getPrimitiveAltLog();
                return super.getPrimitiveLog();
            }
        };
        if (this.isFireProof()) {
            branch.setFireSpreadSpeed(0).setFlammability(0);
        }

        return branch;
    }

    protected Supplier<BranchBlock> createAltBranch(ResourceLocation name) {
        return RegistryHandler.addBlock(ResourceLocationUtils.suffix(name, this.getBranchNameSuffix()), () -> this.createAltBranchBlock(name));
    }

    public Family setPrimitiveAltLog(Block primitiveLog) {
        this.primitiveAltLog = primitiveLog;
        altBranch.get().setPrimitiveLogDrops(new ItemStack(primitiveLog));
        return this;
    }

    public Optional<BranchBlock> getAltBranch() {
        return Optionals.ofBlock(altBranch.get());
    }

    public Optional<Block> getPrimitiveAltLog() {
        return Optionals.ofBlock(primitiveAltLog);
    }

    public void generateStateData(DTDataProvider.BlockState provider) {
        super.generateStateData(provider);
        (this.altBranchStateGenerator.get()).generate(provider, this);
    }

    public void addBranchTextures(BiConsumer<String, ResourceLocation> textureConsumer, ResourceLocation primitiveLogLocation, Block sourceBlock) {
        Optional<Block> primAlt = getPrimitiveAltLog();
        if (primAlt.isPresent() && primAlt.get() == sourceBlock){
            ResourceLocation bark = primitiveLogLocation;
            ResourceLocation rings = ResourceLocationUtils.suffix(primitiveLogLocation, "_top");
            if (this.textureOverrides.containsKey("alt_branch")) {
                bark = this.textureOverrides.get("alt_branch");
            }

            if (this.textureOverrides.containsKey("alt_branch_top")) {
                rings = this.textureOverrides.get("alt_branch_top");
            }
            textureConsumer.accept("bark", bark);
            textureConsumer.accept("rings", rings);
            return;
        }
        super.addBranchTextures(textureConsumer, primitiveLogLocation, sourceBlock);
    }

    public static class altBranchStateGenerator extends BranchStateGenerator{
        public @NotNull Dependencies gatherDependencies(@NotNull Family input) {
            if (input instanceof AltLogFamily castedInput)
                return (new Dependencies()).append(BRANCH, castedInput.getAltBranch()).append(PRIMITIVE_LOG, castedInput.getPrimitiveAltLog());
            return super.gatherDependencies(input);
        }
    }

}
