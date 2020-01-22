package org.java.model.adapter;

/**
 * 适配器(Adaper)角色：适配器类是适配器模式的核心。
 * 适配器把源接口转换成目标接口。显然，这一角色不可以是接口，而必须是具体类
 * @author zpp
 *
 */
public class ClazzAdapter extends Adaptee implements Target {
	public void request() {
		super.specificRequest();
	}
}