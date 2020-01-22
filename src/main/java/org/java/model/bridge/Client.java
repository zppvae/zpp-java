package org.java.model.bridge;

/**
 * 桥接模式 (Bridge)将抽象部分与实现部分分离，使它们都可以独立的变化
 * Description:
 * @author zpp
 * @date   2018年3月28日
 */
public class Client {

	public static void main(String[] args) {
		CpuAbility ability = new IntelCpu();
		AbstractComputer computer = new LenevoComputer(ability);
		computer.checkPcAbility();
		ability = new AdmCpu();// 华硕笔记本CPU性能系能比较牛
		computer = new IBMComputer(ability);
		computer.checkPcAbility();// IBM笔记本CPU性能系能一般
	}
}
