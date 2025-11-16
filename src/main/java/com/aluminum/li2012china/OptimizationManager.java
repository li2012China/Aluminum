package com.aluminum.li2012china;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * 优化管理器
 * 负责协调各个优化模块的工作，根据性能状况动态调整优化级别
 * 
 * 优化原理：
 * 1. 根据性能监控数据动态调整优化级别
 * 2. 协调各个优化模块的工作
 * 3. 提供统一的优化控制接口
 * 
 * @author li2012China
 */
@Environment(EnvType.CLIENT)
public class OptimizationManager {
    private int performanceCheckCounter = 0;
    private ConfigManager.ModConfig.OptimizationLevel currentOptimizationLevel;
    
    public OptimizationManager() {
        this.currentOptimizationLevel = ConfigManager.getConfig().optimizationLevel;
    }
    
    /**
     * 根据性能状况调整优化级别
     */
    public void adjustOptimizationLevel() {
        if (!ConfigManager.getConfig().enableDynamicOptimization) {
            return;
        }
        
        performanceCheckCounter++;
        if (performanceCheckCounter < ConfigManager.getConfig().performanceCheckInterval) {
            return;
        }
        
        performanceCheckCounter = 0;
        
        PerformanceMonitor monitor = null; // 简化实现
        if (monitor == null) return;
        
        ConfigManager.ModConfig.OptimizationLevel newLevel = determineOptimizationLevel(monitor);
        
        if (newLevel != currentOptimizationLevel) {
            ConfigManager.ModConfig.OptimizationLevel oldLevel = currentOptimizationLevel;
            currentOptimizationLevel = newLevel;
            
            // 应用新的优化级别
            applyOptimizationLevel(newLevel);
            
            Aluminum.LOGGER.info("优化级别已调整: {} -> {}", oldLevel, newLevel);
        }
    }
    
    /**
     * 根据性能监控数据确定合适的优化级别
     */
    private ConfigManager.ModConfig.OptimizationLevel determineOptimizationLevel(PerformanceMonitor monitor) {
        float averageFPS = monitor.getAverageFPS();
        float memoryUsageMB = monitor.getMemoryUsageMB();
        boolean isLowPerfMode = monitor.isLowPerformanceMode();
        
        // 如果已经处于低性能模式，使用极致优化
        if (isLowPerfMode) {
            return ConfigManager.ModConfig.OptimizationLevel.EXTREME;
        }
        
        // 如果内存使用超过1GB，使用高优化
        if (memoryUsageMB > 1000) {
            return ConfigManager.ModConfig.OptimizationLevel.HIGH;
        }
        
        // 如果FPS低于45，使用高优化
        if (averageFPS < 45) {
            return ConfigManager.ModConfig.OptimizationLevel.HIGH;
        }
        
        // 如果FPS低于55，使用中等优化
        if (averageFPS < 55) {
            return ConfigManager.ModConfig.OptimizationLevel.MEDIUM;
        }
        
        // 如果FPS高于目标值，可以降低优化级别
        if (averageFPS > ConfigManager.getConfig().targetFPS + 10) {
            return ConfigManager.ModConfig.OptimizationLevel.LOW;
        }
        
        // 默认使用中等优化
        return ConfigManager.ModConfig.OptimizationLevel.MEDIUM;
    }
    
    /**
     * 应用指定的优化级别
     */
    private void applyOptimizationLevel(ConfigManager.ModConfig.OptimizationLevel level) {
        switch (level) {
            case LOW:
                applyLowOptimization();
                break;
            case MEDIUM:
                applyMediumOptimization();
                break;
            case HIGH:
                applyHighOptimization();
                break;
            case EXTREME:
                applyExtremeOptimization();
                break;
        }
    }
    
    /**
     * 应用低级别优化（更注重画质）
     */
    private void applyLowOptimization() {
        ConfigManager.ModConfig config = ConfigManager.getConfig();
        config.maxEntityRenderDistance = 96;
        config.maxEntitiesToRender = 150;
        config.maxParticleCount = 200;
        config.particleRenderDistanceMultiplier = 1.0f;
        config.maxChunkRenderDistance = 12;
        
        if (EntityOptimizer.initialized) {
            EntityOptimizer.updateSettings();
        }
        if (ParticleOptimizer.initialized) {
            ParticleOptimizer.updateSettings();
        }
    }
    
    /**
     * 应用中等优化（平衡画质和性能）
     */
    private void applyMediumOptimization() {
        ConfigManager.ModConfig config = ConfigManager.getConfig();
        config.maxEntityRenderDistance = 64;
        config.maxEntitiesToRender = 100;
        config.maxParticleCount = 100;
        config.particleRenderDistanceMultiplier = 0.8f;
        config.maxChunkRenderDistance = 8;
        
        if (EntityOptimizer.initialized) {
            EntityOptimizer.updateSettings();
        }
        if (ParticleOptimizer.initialized) {
            ParticleOptimizer.updateSettings();
        }
    }
    
    /**
     * 应用高级别优化（更注重性能）
     */
    private void applyHighOptimization() {
        ConfigManager.ModConfig config = ConfigManager.getConfig();
        config.maxEntityRenderDistance = 48;
        config.maxEntitiesToRender = 75;
        config.maxParticleCount = 50;
        config.particleRenderDistanceMultiplier = 0.6f;
        config.maxChunkRenderDistance = 6;
        
        if (EntityOptimizer.initialized) {
            EntityOptimizer.updateSettings();
        }
        if (ParticleOptimizer.initialized) {
            ParticleOptimizer.updateSettings();
        }
    }
    
    /**
     * 应用极致优化（最大程度提升性能）
     */
    private void applyExtremeOptimization() {
        ConfigManager.ModConfig config = ConfigManager.getConfig();
        config.maxEntityRenderDistance = 32;
        config.maxEntitiesToRender = 50;
        config.maxParticleCount = 25;
        config.particleRenderDistanceMultiplier = 0.4f;
        config.maxChunkRenderDistance = 4;
        
        if (EntityOptimizer.initialized) {
            EntityOptimizer.updateSettings();
        }
        if (ParticleOptimizer.initialized) {
            ParticleOptimizer.updateSettings();
        }
    }
    
    /**
     * 获取当前优化级别
     */
    public ConfigManager.ModConfig.OptimizationLevel getCurrentOptimizationLevel() {
        return currentOptimizationLevel;
    }
}