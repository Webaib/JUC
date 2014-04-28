package net.overscale.juc;

import static net.overscale.juc.Utils.areStringsNullsOrEquals;
import static net.overscale.juc.Utils.fuzzyEquals;

import java.util.Arrays;

import net.overscale.juc.annotations.StriclyArrayEquals;
import net.overscale.juc.annotations.FuzzyEquals;
import net.overscale.juc.annotations.StrictlyEquals;

public class SimpleObj {

	private static final double TOLERANCE = 0.01;

	@StrictlyEquals(deepCopy = false)
	private int intF;

	@StrictlyEquals(deepCopy = false)
	private String stringF;

	@FuzzyEquals(tolerance = TOLERANCE)
	private double doubleF;

	@StriclyArrayEquals(deepCopy = false)
	private String[] stringAF;

	@StriclyArrayEquals(deepCopy = false)
	private double[] doubleAF;

	public SimpleObj(int a, String b, double c, String[] d, double[] e) {
		this.intF = a;
		this.stringF = b;
		this.doubleF = c;
		this.stringAF = d;
		this.doubleAF = e;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("TestAnno=[");

		sb.append("intF=").append(intF).append(",");
		sb.append("stringF=").append(stringF).append(",");
		sb.append("doubleF=").append(doubleF).append(",");
		sb.append("stringAF={").append(Arrays.toString(stringAF)).append("},");
		sb.append("doubleAF={").append(Arrays.toString(doubleAF)).append("}");

		sb.append("]");

		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || !(obj instanceof SimpleObj)) {
			return false;
		}
		
		SimpleObj t = (SimpleObj) obj;
		return intF == t.intF
				&& areStringsNullsOrEquals(stringF, t.stringF)
				&& fuzzyEquals(doubleF, t.doubleF, TOLERANCE)
				&& Arrays.equals(stringAF, t.stringAF)
				&& Arrays.equals(doubleAF, t.doubleAF);
	}
	
	@Override
	public int hashCode() {
		throw new IllegalStateException("HashCode not implemented yet.");
	}
}
