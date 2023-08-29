package it.teorema.workabilia.mapper;

import java.util.HashMap;
import java.util.List;

import it.teorema.workabilia.model.PositionsSkills;

public class ResponseImport {
	List<PositionsSkills> listpositionSkill;
	List<SummarySkills> listSummarySkills;
	List<Integer> arrayIdPositions;
	
	public ResponseImport() {}
	
	public ResponseImport(List<PositionsSkills> listpositionSkill, List<SummarySkills> listSummarySkills, 
			List<Integer> arrayIdPositions) {
		this.listpositionSkill = listpositionSkill;
		this.listSummarySkills = listSummarySkills;
		this.arrayIdPositions = arrayIdPositions;
	}

	public List<PositionsSkills> getListpositionSkill() {
		return listpositionSkill;
	}

	public void setListpositionSkill(List<PositionsSkills> listpositionSkill) {
		this.listpositionSkill = listpositionSkill;
	}

	public List<SummarySkills> getListSummarySkills() {
		return listSummarySkills;
	}

	public void setListSummarySkills(List<SummarySkills> listSummarySkills) {
		this.listSummarySkills = listSummarySkills;
	}

	public List<Integer> getArrayIdPositions() {
		return arrayIdPositions;
	}

	public void setArrayIdPositions(List<Integer> arrayIdPositions) {
		this.arrayIdPositions = arrayIdPositions;
	}
	
	
}
