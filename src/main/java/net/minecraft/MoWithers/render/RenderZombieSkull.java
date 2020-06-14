package net.minecraft.MoWithers.render;

import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.witherskulls.EntityWitherSkullZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZombieSkull
  extends RenderWitherSkull
{
  private static final ResourceLocation invulnerableWitherTextures = new ResourceLocation("mowithers", "textures/entities/zombie_wither_invul.png");
  private static final ResourceLocation witherTextures = new ResourceLocation("mowithers", "textures/entities/zombie_wither.png");
  
  public RenderZombieSkull(RenderManager p_i46129_1_)
  {
    super(p_i46129_1_);
  }
  
  protected ResourceLocation func_180564_a(EntityWitherSkullZombie p_180564_1_)
  {
    return p_180564_1_.isInvulnerable() ? invulnerableWitherTextures : witherTextures;
  }
  
  protected ResourceLocation getEntityTexture(Entity entity)
  {
    return func_180564_a((EntityWitherSkullZombie)entity);
  }
}