package it.teorema.workabilia.mapper;

import it.teorema.workabilia.model.Instruction;
import it.teorema.workabilia.model.Students;

public class InstructionStudent {
	private Students student;
	private Instruction instruction;
	
	public Students getStudent() {
		return student;
	}
	public void setStudent(Students student) {
		this.student = student;
	}
	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
}