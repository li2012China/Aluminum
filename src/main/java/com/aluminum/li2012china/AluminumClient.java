package com.aluminum.li2012china;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aluminum客户端模组类
 * 主要负责客户端相关的优化功能
 * 
 * @author li2012China
 */
public class AluminumClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("aluminum-client");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Aluminum客户端模组正在初始化...");
        
        // 注册配置屏幕
        registerConfigScreen();
        
        // 注册性能HUD
        registerPerformanceHud();
        
        LOGGER.info("Aluminum客户端模组初始化完成！");
    }
    
    /**
     * 注册配置屏幕
     */
    private void registerConfigScreen() {
        // 配置屏幕将在ModMenu集成中处理
        LOGGER.info("配置屏幕已注册");
    }
    
    /**
     * 注册性能HUD显示
     */
    private void registerPerformanceHud() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            // 这里可以添加性能监控信息的显示
            // 暂时不实现，保持简单
        });
    }
}