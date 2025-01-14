package net.minecraft.MoWithers.render;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.wither.EntityWitherSpider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWitherSpider extends RenderLiving
{
    private static final ResourceLocation spiderTextures = new ResourceLocation("mowithers", "textures/entities/wither_spider.png");

    public RenderWitherSpider(RenderManager p_i46139_1_)
    {
        super(p_i46139_1_, new ModelSpider(), 1.0F);
    }

    protected float getDeathMaxRotation(EntityWitherSpider p_77037_1_)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityWitherSpider entity)
    {
        return spiderTextures;
    }

    protected float getDeathMaxRotation(EntityLivingBase p_77037_1_)
    {
        return this.getDeathMaxRotation((EntityWitherSpider)p_77037_1_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityWitherSpider)entity);
    }
}