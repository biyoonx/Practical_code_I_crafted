package classes;

import interfaces.Reportable;

public class Major implements Reportable {
	protected String majorName;
	protected int majorNum;
	private static int serialNum = 10000;
	protected Subject requiredSub;
	
	public Major(String majorName, Subject subject) {
		this.majorName = majorName;
		this.majorNum = ++serialNum;
		this.requiredSub = subject;
	}
	
	public String getMajorName() {
		return majorName;
	}
	public int getMajorNum() {
		return majorNum;
	}
	public Subject getRequiredSub() {
		return requiredSub;
	}

	@Override
	public String toString() {
		return majorName + "(" + majorNum + "): 필수 과목 - " + requiredSub;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Major major) {
			if (this.majorNum == major.majorNum) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		return majorNum;
	}
}
