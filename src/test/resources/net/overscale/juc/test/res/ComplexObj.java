package net.overscale.juc.test.res;

import static net.overscale.juc.Utils.areStringsNullsOrEquals;
import static net.overscale.juc.Utils.fuzzyEquals;

import java.util.Arrays;
import java.util.List;

import net.overscale.juc.annotations.FuzzyEquals;
import net.overscale.juc.annotations.StriclyIterableEquals;
import net.overscale.juc.annotations.StrictlyEquals;

public class ComplexObj {

	private static final double TOLERANCE = 0.05;

	@StrictlyEquals(deepCopy = false)
	private String anotherStringF;

	@FuzzyEquals(tolerance = TOLERANCE)
	private double anotherDoubleF;

	@StriclyIterableEquals(deepCopy = false, preserveOrder = true)
	private SimpleObj[] complObjAF;

	@StriclyIterableEquals(deepCopy = false, preserveOrder = true)
	private List<SimpleObj> complObjColF;

	public ComplexObj(String b, double c, SimpleObj[] d, List<SimpleObj> e) {
		this.anotherStringF = b;
		this.anotherDoubleF = c;
		this.complObjAF 	= d;
		this.complObjColF	= e;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("TestAnno=[");

		sb.append("anotherStringF=").append(anotherStringF).append(",");
		sb.append("anotherDoubleF=").append(anotherDoubleF).append(",");
		sb.append("complObjAF={").append(Arrays.toString(complObjAF))
				.append("},");
		sb.append("complObjColF={")
				.append(Arrays.toString(complObjColF.toArray(new SimpleObj[0])))
				.append("}");

		sb.append("]");

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || !(obj instanceof ComplexObj)) {
			return false;
		}

		ComplexObj t = (ComplexObj) obj;
		return areStringsNullsOrEquals(anotherStringF, t.anotherStringF)
				&& fuzzyEquals(anotherDoubleF, t.anotherDoubleF, TOLERANCE)
				&& Arrays.equals(complObjAF, t.complObjAF)
				&& Arrays.equals(complObjColF.toArray(new SimpleObj[0]),
						t.complObjColF.toArray(new SimpleObj[0]));
	}

	@Override
	public int hashCode() {
		throw new IllegalStateException("HashCode not implemented yet.");
	}

}
