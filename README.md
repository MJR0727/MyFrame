# MyFrame 快速开发脚手架

## d2023/01/03
mapstruct 使用setter的方式进行属性注入，比起BeanUtils复制实例的方式效率更高。

## d2023/02/03
redis缓存预热 利用SpringApplicationContext对缓存类进行初始化预热（各缓存类继承同一个抽象缓存类）。

分布式锁(redistemple、redissson);

## d2023/02/04
修复swagger、redis模块无法扫描的bug