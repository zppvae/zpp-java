package org.java.model.bridge;

abstract class AbstractComputer {

	CpuAbility cpuAbility;

	public AbstractComputer(CpuAbility cpuAbility) {
		this.cpuAbility = cpuAbility;

	}

	public abstract void checkPcAbility();

}