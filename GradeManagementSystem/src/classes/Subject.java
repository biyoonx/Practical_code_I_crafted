package classes;

import interfaces.Reportable;

public class Subject implements Reportable {
	protected String subjectName;
	protected int subjectNum;
	private static int serialNum = 10001000;
	protected boolean isPassFail = false;
	
	public Subject(String subjectName) {
		this.subjectName = subjectName;
		this.subjectNum = ++serialNum;
	}
	
	public String subjectName() {
		return subjectName;
	}
	public void setIsPassFail(boolean tf) {
		this.isPassFail = tf;
	}
	
	@Override
	public String toString() {
		return subjectName + "(" + subjectNum + ")";
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Subject subject) {
			if (this.subjectNum == subject.subjectNum)
				return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return subjectNum;
	}
}
