package com.aluminum.li2012china;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 实体优化器
 * 负责优化实体渲染和处理，减少不必要的实体更新
 * 
 * 优化原理：
 * 1. 实体距离剔除：只渲染和处理玩家附近一定距离内的实体
 * 2. 实体数量限制：限制同时渲染的实体数量
 * 3. 实体更新频率控制：降低远距离实体的更新频率
 * 4. 非关键实体跳帧：跳过非关键实体的一些渲染帧
 * 
 * @author li2012China
 */
@Environment(EnvType.CLIENT)
public class EntityOptimizer {
    private static int frameCounter = 0;
    
    // 实体缓存和追踪
    private static final ConcurrentHashMap<String, Integer> entityRenderFrames = new ConcurrentHashMap<>();
    
    public static boolean initialized = false;
    
    /**
     * 初始化实体优化器
     */
    public static void initialize() {
        initialized = true;
        Aluminum.LOGGER.info("实体优化器已初始化");
    }
    
    /**
     * 更新优化设置
     */
    public static void updateSettings() {
        // 清除缓存的实体数据，强制重新应用新的设置
        entityRenderFrames.clear();
        Aluminum.LOGGER.info("实体优化器设置已更新");
    }
    
    /**
     * 检查实体是否应该被渲染
     * 简化版本，返回true表示总是渲染，实际应用中应根据距离和数量限制
     */
    public static boolean shouldRenderEntity(Object entity) {
        ConfigManager.ModConfig config = ConfigManager.getConfig();
        if (!config.enableEntityCulling) {
            return true;
        }
        
        // 在实际应用中，这里应该检查实体距离和数量限制
        // 现在简化实现，总是返回true
        return true;
    }
    
    /**
     * 检查实体是否应该被更新
     * 简化版本，返回true表示总是更新
     */
    public static boolean shouldUpdateEntity(Object entity) {
        ConfigManager.ModConfig config = ConfigManager.getConfig();
        if (!config.enableEntityCulling) {
            return true;
        }
        
        // 在实际应用中，这里应该检查实体距离和更新频率
        // 现在简化实现，总是返回true
        return true;
    }
    
    /**
     * 清理旧的实体数据，防止内存泄漏
     */
    public static void cleanup() {
        frameCounter++;
        
        // 每100帧清理一次数据
        if (frameCounter % 100 == 0) {
            entityRenderFrames.clear();
            Aluminum.LOGGER.debug("清理实体缓存数据");
        }
    }
}