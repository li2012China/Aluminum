# Aluminum

A Minecraft Fabric client mod focused on reducing game lag and lowering CPU/GPU usage through optimization.

This project made with AI because I DON'T KNOW HOW TO USE FABRIC but my [friend](https://modrinth.com/user/tiebai) need this.

别骂了，这个作品确实是AI做的，首先我不会Fabric，其次我的[朋友](https://modrinth.com/user/tiebai)非要这个，话说他咋不自己让AI去写呢...

## Main Features

- **Entity Rendering Optimization**: Reduce unnecessary entity rendering and updates
- **Particle Effect Optimization**: Limit the number of particle effects to reduce GPU load
- **Resource Loading Optimization**: Optimize resource loading mechanisms to reduce memory usage
- **Dynamic Performance Adjustment**: Dynamically adjust optimization levels based on system performance

## Optimization Principles

### Entity Optimization
1. Entity Distance Culling: Only render and process entities within a certain distance of the player
2. Entity Count Limit: Limit the number of entities rendered at the same time
3. Entity Update Frequency Control: Reduce the update frequency of distant entities
4. Skip Frames for Non-Critical Entities: Skip some rendering frames for non-critical entities

### Particle Effect Optimization
1. Particle Count Limit: Limit the number of particles rendered at the same time
2. Distance Culling: Only render particles within a certain distance of the player
3. Particle Type Priority: Prioritize displaying important particle effects
4. Particle Lifetime Management: Clean up unnecessary particles in advance

### Rendering Optimization
1. Dynamic View Distance Adjustment: Adjust rendering distance dynamically based on performance
2. Image Quality Reduction: Automatically lower image quality when performance is insufficient
3. Rendering Pipeline Optimization: Optimize key steps in the rendering pipeline
4. Memory and GPU Resource Management: Release unused rendering resources in a timely manner

## Performance Monitoring

The mod includes a performance monitoring system that can track in real-time:
- FPS (Frames Per Second)
- Memory usage
- Number of entities
- Number of particles

## Configuration File

The configuration file is located at `config/anti-lag.json` and includes the following options:

- Optimization level (Low/Medium/High/Extreme)
- Maximum entity render distance
- Entity culling toggle
- Maximum number of entities rendered
- Maximum number of particles
- Particle optimization toggle
- Particle render distance multiplier
- Rendering optimization toggle
- Advanced graphics toggle
- Maximum chunk render distance
- Dynamic optimization toggle
- Target FPS

## Compatibility

- Minecraft Version: 1.21 to 1.21.10
- Fabric Loader Version: 0.17.3
- Java Version: 21

## Installation

1. Place the `anti-lag-1.0.0.jar` file into the `.minecraft/mods` directory
2. Launch the game

## Usage

The mod will automatically apply optimization settings upon launch. If you want to customize settings, you can edit the `config/anti-lag.json` file.

## Author

li2012China

## License

CC-BY-NC-ND 4.0

## Changelog

### v1.0.0
- Initial release
- Implement basic optimization features
- Add performance monitoring
- Add configuration file support
