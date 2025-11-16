package com.aluminum.li2012china.mixin;

import org.spongepowered.asm.mixin.Mixin;

/**
 * 粒子管理器Mixin
 * 用于优化粒子效果渲染
 * 
 * @author li2012China
 */
@Mixin(targets = "net.minecraft.client.particle.ParticleManager")
public class ParticleManagerMixin {
    
    /**
     * 简单的Mixin类，目前不执行任何操作
     * 用于粒子效果优化
     */
}