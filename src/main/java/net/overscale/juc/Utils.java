package net.overscale.juc;

public class Utils {
	
	public static boolean fuzzyEquals(double a, double b, double tolerance) {
		if (!(tolerance >= 0)) {
			throw new IllegalArgumentException(String.format(
					"Tolerance (%f) must be >= 0", tolerance));
		}

		return Math.copySign(a - b, 1.0) <= tolerance 
				|| (a == b);
	}

	public static boolean areStringsNullsOrEquals(Object o1, Object o2) {
		return o1 == null ? o2 == null : 
			String.valueOf(o1).equals(String.valueOf(o2));
	}

}
