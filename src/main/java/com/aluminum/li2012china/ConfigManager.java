package com.aluminum.li2012china;

import net.fabricmc.loader.api.FabricLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 配置管理器
 * 负责模组配置的加载、保存和管理
 * 
 * @author li2012China
 */
public class ConfigManager {
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "aluminum.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    private static ModConfig config;
    
    /**
     * 初始化配置管理器
     */
    public static void initialize() {
        loadConfig();
    }
    
    /**
     * 加载配置文件
     */
    public static void loadConfig() {
        if (!CONFIG_FILE.exists()) {
            config = new ModConfig();
            saveConfig();
            Aluminum.LOGGER.info("创建了新的Aluminum配置文件");
            return;
        }
        
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            config = GSON.fromJson(reader, ModConfig.class);
            Aluminum.LOGGER.info("成功加载Aluminum配置文件");
        } catch (IOException e) {
            Aluminum.LOGGER.error("无法加载配置文件，使用默认配置", e);
            config = new ModConfig();
        }
    }
    
    /**
     * 保存配置文件
     */
    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(config, writer);
        } catch (IOException e) {
            Aluminum.LOGGER.error("无法保存配置文件", e);
        }
    }
    
    /**
     * 获取配置实例
     */
    public static ModConfig getConfig() {
        if (config == null) {
            config = new ModConfig();
        }
        return config;
    }
    
    /**
     * 模组配置类
     * 包含所有可配置的优化选项
     */
    public static class ModConfig {
        // 实体优化设置
        public int maxEntityRenderDistance = 64;
        public boolean enableEntityCulling = true;
        public int maxEntitiesToRender = 100;
        
        // 粒子效果优化设置
        public int maxParticleCount = 100;
        public boolean enableParticleOptimization = true;
        public float particleRenderDistanceMultiplier = 0.8f;
        
        // 渲染优化设置
        public boolean enableRenderOptimization = true;
        public boolean disableFancyGraphics = false;
        public int maxChunkRenderDistance = 8;
        
        // 网络优化设置
        public boolean enableNetworkOptimization = true;
        public int maxNetworkPacketsPerTick = 50;
        
        // 自动优化设置
        public boolean enableDynamicOptimization = true;
        public int performanceCheckInterval = 100; // ticks
        public float targetFPS = 60.0f;
        
        // 优化级别设置
        public OptimizationLevel optimizationLevel = OptimizationLevel.MEDIUM;
        
        /**
         * 优化级别枚举
         */
        public enum OptimizationLevel {
            LOW,      // 低优化，更注重画质
            MEDIUM,   // 中等优化，平衡画质和性能
            HIGH,     // 高优化，更注重性能
            EXTREME   // 极致优化，最大程度提升性能
        }
    }
}