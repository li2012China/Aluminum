package com.aluminum.li2012china.mixin;

import org.spongepowered.asm.mixin.Mixin;

/**
 * 世界渲染器Mixin
 * 用于优化世界渲染过程
 * 
 * @author li2012China
 */
@Mixin(targets = "net.minecraft.client.render.WorldRenderer")
public class WorldRendererMixin {
    
    /**
     * 简单的Mixin类，目前不执行任何操作
     * 用于世界渲染优化
     */
}