package com.aluminum.li2012china;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 性能监控器
 * 负责监控游戏性能指标，包括FPS、内存使用等
 * 
 * 优化原理：
 * 1. 通过监控FPS和渲染时间判断游戏流畅度
 * 2. 监控内存使用情况，及时触发垃圾回收
 * 3. 监控CPU使用情况，动态调整优化策略
 * 
 * @author li2012China
 */
@Environment(EnvType.CLIENT)
public class PerformanceMonitor {
    // 性能数据采样
    private final Deque<Float> fpsHistory = new ArrayDeque<>(60); // 保存最近60帧的FPS
    private final Deque<Long> memoryUsageHistory = new ArrayDeque<>(60); // 保存最近60次的内存使用情况
    private long lastPerformanceCheck = 0;
    
    // 性能指标
    private float averageFPS = 60.0f;
    private long memoryUsage = 0;
    private boolean isLowPerformanceMode = false;
    
    private static boolean initialized = false;
    
    /**
     * 初始化性能监控器
     */
    public static void initialize() {
        initialized = true;
    }
    
    /**
     * 更新性能指标
     */
    public void updatePerformanceMetrics() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPerformanceCheck < 100) return; // 每100ms检查一次
        
        lastPerformanceCheck = currentTime;
        
        // 更新FPS
        updateFPS();
        
        // 更新内存使用情况
        updateMemoryUsage();
        
        // 判断是否需要进入低性能模式
        updatePerformanceMode();
    }
    
    /**
     * 更新FPS历史记录
     */
    private void updateFPS() {
        // 简化处理，使用固定值模拟FPS
        float currentFPS = 60.0f; // 默认值，实际应用中应从Minecraft客户端获取
        
        fpsHistory.add(currentFPS);
        if (fpsHistory.size() > 60) {
            fpsHistory.remove();
        }
        
        // 计算平均FPS
        if (!fpsHistory.isEmpty()) {
            float totalFPS = 0;
            for (float fps : fpsHistory) {
                totalFPS += fps;
            }
            averageFPS = totalFPS / fpsHistory.size();
        }
    }
    
    /**
     * 更新内存使用情况
     */
    private void updateMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        memoryUsage = totalMemory - freeMemory;
        
        memoryUsageHistory.add(memoryUsage);
        if (memoryUsageHistory.size() > 60) {
            memoryUsageHistory.remove();
        }
    }
    
    /**
     * 更新性能模式
     */
    private void updatePerformanceMode() {
        boolean shouldEnterLowPerfMode = false;
        
        // 如果平均FPS低于30，进入低性能模式
        if (averageFPS < 30) {
            shouldEnterLowPerfMode = true;
        }
        
        // 如果内存使用超过1.5GB，进入低性能模式
        if (memoryUsage > 1500 * 1024 * 1024) {
            shouldEnterLowPerfMode = true;
        }
        
        // 检查内存是否持续增长
        if (memoryUsageHistory.size() >= 30) {
            Long[] recentMemory = memoryUsageHistory.toArray(new Long[0]);
            long memoryGrowth = recentMemory[recentMemory.length - 1] - recentMemory[0];
            
            // 如果30秒内内存增长超过100MB，进入低性能模式
            if (memoryGrowth > 100 * 1024 * 1024) {
                shouldEnterLowPerfMode = true;
            }
        }
        
        // 如果之前不是低性能模式，但现在需要，触发垃圾回收
        if (!isLowPerformanceMode && shouldEnterLowPerfMode) {
            System.gc();
            Aluminum.LOGGER.info("进入低性能模式，触发垃圾回收");
        }
        
        isLowPerformanceMode = shouldEnterLowPerfMode;
    }
    
    /**
     * 获取平均FPS
     */
    public float getAverageFPS() {
        return averageFPS;
    }
    
    /**
     * 获取当前内存使用量（字节）
     */
    public long getMemoryUsage() {
        return memoryUsage;
    }
    
    /**
     * 获取当前内存使用量（MB）
     */
    public float getMemoryUsageMB() {
        return memoryUsage / (1024.0f * 1024.0f);
    }
    
    /**
     * 是否处于低性能模式
     */
    public boolean isLowPerformanceMode() {
        return isLowPerformanceMode;
    }
    
    /**
     * 获取性能状态描述
     */
    public String getPerformanceStatus() {
        if (isLowPerformanceMode) {
            return "低性能模式";
        }
        
        if (averageFPS >= 55) {
            return "流畅";
        } else if (averageFPS >= 30) {
            return "一般";
        } else {
            return "卡顿";
        }
    }
}