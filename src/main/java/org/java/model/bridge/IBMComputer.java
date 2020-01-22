package org.java.model.bridge;

class IBMComputer extends AbstractComputer {

	public IBMComputer(CpuAbility cpuAbility) {
		super(cpuAbility);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkPcAbility() {
		System.out.println("IBM笔记本CPU性能" + super.cpuAbility.abilityCpu());
	}

}