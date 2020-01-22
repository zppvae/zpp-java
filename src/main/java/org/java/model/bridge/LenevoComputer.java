package org.java.model.bridge;

public class LenevoComputer extends AbstractComputer {

	public LenevoComputer(CpuAbility cpuAbility) {
		super(cpuAbility);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkPcAbility() {
		System.out.println("华硕笔记本CPU性能" + super.cpuAbility.abilityCpu());
	}

}