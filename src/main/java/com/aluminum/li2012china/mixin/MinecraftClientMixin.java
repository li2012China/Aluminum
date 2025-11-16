package com.aluminum.li2012china.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.aluminum.li2012china.Aluminum;

/**
 * Minecraft客户端Mixin
 * 用于优化核心游戏循环和性能
 * 
 * @author li2012China
 */
@Mixin(targets = "net.minecraft.client.MinecraftClient")
public class MinecraftClientMixin {
    
    /**
     * 拦截游戏运行方法，记录MOD初始化
     */
    @Inject(at = @At("HEAD"), method = "run", cancellable = false)
    private void onRun(CallbackInfo ci) {
        // 记录MOD初始化
        Aluminum.LOGGER.info("Aluminum mod initialized");
    }
}