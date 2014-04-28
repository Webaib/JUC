package net.overscale.juc;

import java.util.Arrays;

import net.overscale.juc.annotations.ArrayEquals;
import net.overscale.juc.annotations.FuzzyEquals;
import net.overscale.juc.annotations.StrictlyEquals;

public class MainTest {
	@StrictlyEquals
	private int intF;
	@StrictlyEquals
	private String stringF;
	@FuzzyEquals(tollerance = 0.01D)
	private double doubleF;
	@ArrayEquals(ignoreDuplicate = false)
	private String[] stringAF;
	private double[] doubleAF;

	public MainTest(int a, String b, double c, String[] d, double[] e) {
		this.intF = a;
		this.stringF = b;
		this.doubleF = c;
		this.stringAF = d;
		this.doubleAF = e;
	}

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException {
		MainTest anno1 = new MainTest(12, "qqq", 1.2D, new String[] { "a",
				"ar", "asd" }, new double[] { 1.0D, 1.1D });
		MainTest anno2 = new MainTest(11, "qqqa", 1.21D, new String[] { "a",
				"as", "asd" }, new double[] { 1.0D, 1.2D });

		System.out.println(anno1);
		System.out.println(anno2);

		Main main = new Main();
		main.compareAndSync(anno1, anno2, true);

		System.out.println("");
		System.out.println(anno1);
		System.out.println(anno2);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("TestAnno=[");

		sb.append("intF=").append(this.intF).append(",");
		sb.append("stringF=").append(this.stringF).append(",");
		sb.append("doubleF=").append(this.doubleF).append(",");
		sb.append("stringAF={").append(Arrays.toString(this.stringAF))
				.append("}");

		sb.append("]");

		return sb.toString();
	}
}
