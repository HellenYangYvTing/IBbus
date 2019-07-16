/**  
 * @Title: TabClickListener.java
 * @Package com.bus.view.listener
 * @author zhangjingming
 * @date 2017年12月7日
 * @version V1.0  
 */
package com.bus.view.listener;

import com.bus.enums.ModuleEnum;

/**
 * @ClassName: TabClickListener
 * @Description: 选项卡点击监听
 * @author zhangjingming
 * @date 2017年12月7日
 * @since JDK 1.8
 */
public interface TabClickListener {
	/**
	 * @Title: onTabClick
	 * @Description: 选项卡点击回调
	 * @param tabEnum 模块枚举
	 */
	void onTabClick(ModuleEnum tabEnum);
}
