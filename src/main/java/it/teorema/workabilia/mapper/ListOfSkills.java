package it.teorema.workabilia.mapper;

import java.util.List;

import it.teorema.workabilia.model.Skills1;
import it.teorema.workabilia.model.Skills2;
import it.teorema.workabilia.model.Skills3;
import it.teorema.workabilia.model.Skills4;

public class ListOfSkills {
	private List<Skills1> skill1;
	private List<Skills2> skill2;
	private List<Skills3> skill3;
	private List<Skills4> skill4;
	
	public ListOfSkills(List<Skills1> skill1, List<Skills2> skill2, List<Skills3> skill3, List<Skills4> skill4) {
		super();
		this.skill1 = skill1;
		this.skill2 = skill2;
		this.skill3 = skill3;
		this.skill4 = skill4;
	}
	
	public List<Skills1> getSkill1() {
		return skill1;
	}
	public void setSkill1(List<Skills1> skill1) {
		this.skill1 = skill1;
	}
	public List<Skills2> getSkill2() {
		return skill2;
	}
	public void setSkill2(List<Skills2> skill2) {
		this.skill2 = skill2;
	}
	public List<Skills3> getSkill3() {
		return skill3;
	}
	public void setSkill3(List<Skills3> skill3) {
		this.skill3 = skill3;
	}
	public List<Skills4> getSkill4() {
		return skill4;
	}
	public void setSkill4(List<Skills4> skill4) {
		this.skill4 = skill4;
	}
}