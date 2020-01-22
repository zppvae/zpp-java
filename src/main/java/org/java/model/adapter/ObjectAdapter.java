package org.java.model.adapter;

/**
 * 对象适配器模式
	适配器类关联已有的Adaptee类，并且实现标准接口，这样做的好处是不再需要继承
 * @author zpp
 *
 */
public class ObjectAdapter implements Target {

	private Adaptee adaptee;

	public ObjectAdapter (Adaptee adaptee) {
         this.adaptee= adaptee;
	 }

	@Override
	public void request() {
		this.adaptee.specificRequest();
	}

}
