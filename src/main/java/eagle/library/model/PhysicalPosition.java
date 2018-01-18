package eagle.library.model;

public enum PhysicalPosition {

	ON_SHELF, ON_LOAN;

	public static PhysicalPosition getPhysicalPosition(String position) {
		for (PhysicalPosition physicalPosition : values()) {
			if (physicalPosition.name().equalsIgnoreCase(position)) {
				return physicalPosition;
			}
		}
		return ON_SHELF;
	}
	
}
