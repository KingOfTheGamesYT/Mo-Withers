package net.minecraft.MoWithers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWitherSculpture
  extends ModelBase
{
  private ModelRenderer[] spine;
  private ModelRenderer[] heads;
  
  public ModelWitherSculpture(float p_i46302_1_)
  {
    this.textureWidth = 64;
    this.textureHeight = 64;
    this.spine = new ModelRenderer[3];
    this.spine[0] = new ModelRenderer(this, 0, 16);
    this.spine[0].addBox(-10.0F, 6.0F, -0.5F, 20, 3, 3, p_i46302_1_);
    this.spine[1] = new ModelRenderer(this).setTextureSize(this.textureWidth, this.textureHeight);
    this.spine[1].setRotationPoint(-2.0F, 8.0F, -0.5F);
    this.spine[1].setTextureOffset(0, 22).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, p_i46302_1_);
    this.spine[1].setTextureOffset(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11, 2, 2, p_i46302_1_);
    this.spine[1].setTextureOffset(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11, 2, 2, p_i46302_1_);
    this.spine[1].setTextureOffset(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11, 2, 2, p_i46302_1_);
    this.spine[2] = new ModelRenderer(this, 12, 22);
    this.spine[2].addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, p_i46302_1_);
    this.spine[2].setRotationPoint(-2.0F, 18.0F, -0.5F);
    this.heads = new ModelRenderer[3];
    this.heads[0] = new ModelRenderer(this, 0, 0);
    this.heads[0].addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, p_i46302_1_);
    this.heads[0].setRotationPoint(0.0F, 2.0F, 0.0F);
    this.heads[1] = new ModelRenderer(this, 32, 0);
    this.heads[1].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, p_i46302_1_);
    this.heads[1].setRotationPoint(-8.0F, 6.0F, 0.0F);
    this.heads[2] = new ModelRenderer(this, 32, 0);
    this.heads[2].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, p_i46302_1_);
    this.heads[2].setRotationPoint(10.0F, 6.0F, 0.0F);
  }
  
  public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
  {
    setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
    ModelRenderer[] amodelrenderer = this.heads;
    int i = amodelrenderer.length;
    for (int j = 0; j < i; j++)
    {
      ModelRenderer modelrenderer = amodelrenderer[j];
      modelrenderer.render(p_78088_7_);
    }
    amodelrenderer = this.spine;
    i = amodelrenderer.length;
    for (int j = 0; j < i; j++)
    {
      ModelRenderer modelrenderer = amodelrenderer[j];
      modelrenderer.render(p_78088_7_);
    }
  }
  
  public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {}
}
