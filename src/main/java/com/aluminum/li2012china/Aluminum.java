package com.aluminum.li2012china;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aluminum 主模组类
 * 主要功能：通过各种优化手段减少游戏卡顿和降低CPU/GPU占用率
 * 
 * 优化原理：
 * 1. 实体渲染优化：减少不必要的实体渲染和更新
 * 2. 粒子效果限制：限制粒子效果数量以减轻GPU负担
 * 3. 资源加载优化：优化资源加载机制减少内存占用
 * 4. 网络数据包处理优化：优化数据包处理减少CPU占用
 * 5. 动态性能调整：根据系统性能动态调整优化级别
 * 
 * @author li2012China
 */
public class Aluminum implements ModInitializer {
    public static final String MOD_ID = "aluminum";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Aluminum Mod 正在初始化...");
        
        // 初始化配置管理器
        ConfigManager.initialize();
        
        // 简化初始化，延迟到客户端初始化
        // 这样可以避免服务端加载客户端特有的代码
        
        LOGGER.info("Aluminum Mod 初始化完成！");
        LOGGER.info("优化模块已加载: 实体渲染优化、粒子效果限制、资源加载优化");
    }
}