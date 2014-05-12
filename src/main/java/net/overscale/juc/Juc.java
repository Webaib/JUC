package net.overscale.juc;

import static net.overscale.juc.Utils.*;

import java.lang.reflect.Field;
import java.util.Arrays;

import net.overscale.juc.annotations.FuzzyIterableEquals;
import net.overscale.juc.annotations.StriclyIterableEquals;
import net.overscale.juc.annotations.FuzzyEquals;
import net.overscale.juc.annotations.StrictlyEquals;

public class Juc {

	public static boolean compareAndSync(Object src, Object trg, boolean sync)
			throws IllegalArgumentException, IllegalAccessException {
		boolean res = true;

		Field[] fields = src.getClass().getDeclaredFields();
		for (Field f : fields) {
			res &= processField(src, trg, sync, f);
		}

		return res;
	}

	private static boolean processField(Object src, Object trg, boolean sync,
			Field f) throws IllegalAccessException {
		f.setAccessible(true);
		
		if (f.getAnnotation(StrictlyEquals.class) != null) {
			return checkStrictlyEquals(src, trg, f, sync);
			
		} else if (f.getAnnotation(FuzzyEquals.class) != null) {
			return checkFuzzyEquals(src, trg, f, sync);
			
		} else if (f.getAnnotation(StriclyIterableEquals.class) != null) {
			return checkStriclyIterableEquals(src, trg, f, sync);
		} else if (f.getAnnotation(FuzzyIterableEquals.class) != null) {
			return checkFuzzyStriclyIterableEquals(src, trg, f, sync);
		}
		
		throw new IllegalStateException("Not yet implemented!");
	}

	private static boolean checkFuzzyStriclyIterableEquals(Object src,
			Object trg, Field f, boolean sync) {
		// TODO Auto-generated method stub
		return false;
	}

	// TODO split in two meth. (order preserved iterable and not)
	private static boolean checkStriclyIterableEquals(Object src, Object trg, Field f,
			boolean sync) throws IllegalAccessException {
		if (f.get(src) instanceof double[]) {
			double[] srcVal = (double[]) f.get(src);
			double[] trgVal = (double[]) f.get(trg);

			return checkArrayEquals(src, trg, srcVal, trgVal, f, sync);
		} else {
			Object[] srcVal = (Object[]) f.get(src);
			Object[] trgVal = (Object[]) f.get(trg);

			return checkArrayEquals(src, trg, srcVal, trgVal, f, sync);
		}
	}

	// TODO fuzzy double/float arrays eq
	private static boolean checkArrayEquals(Object src, Object trg,
			double[] srcVal, double[] trgVal, Field f, boolean sync)
			throws IllegalArgumentException, IllegalAccessException {
		StriclyIterableEquals ae = (StriclyIterableEquals) f
				.getAnnotation(StriclyIterableEquals.class);
		if (!Arrays.equals(srcVal, trgVal)) {
			if (sync) {
				if (ae.deepCopy()) {
					throw new IllegalStateException(
							"Deep copy not yet implemented.");
				} else {
					f.set(trg, srcVal);
				}

				System.out.println(String.format(
						"%s was updated. Old value: %s, new value: %s",
						new Object[] { f, Arrays.toString(trgVal),
								Arrays.toString(srcVal) }));
			}

			return false;
		}

		return true;
	}

	private static boolean checkArrayEquals(Object src, Object trg,
			Object[] srcVal, Object[] trgVal, Field f, boolean sync)
			throws IllegalAccessException {
		StriclyIterableEquals ae = (StriclyIterableEquals) f
				.getAnnotation(StriclyIterableEquals.class);
		if (!Arrays.equals(srcVal, trgVal)) {
			if (sync) {
				if (ae.deepCopy()) {
					throw new IllegalStateException(
							"Deep copy not yet implemented.");
				} else {
					f.set(trg, srcVal);
				}

				System.out.println(String.format(
						"%s was updated. Old value: %s, new value: %s",
						new Object[] { f, Arrays.toString(trgVal),
								Arrays.toString(srcVal) }));
			}

			return false;
		}

		return true;
	}

	private static boolean checkStrictlyEquals(Object src, Object trg, Field f,
			boolean sync) throws IllegalAccessException {
		Object srcFieldVal = f.get(src);
		Object trgFieldVal = f.get(trg);

		StrictlyEquals se = (StrictlyEquals) f
				.getAnnotation(StrictlyEquals.class);
		if (!areStriclyEquals(srcFieldVal, trgFieldVal)) {
			if (sync) {
				if (se.deepCopy()) {
					throw new IllegalStateException(
							"Deep copy not yet implemented.");
				} else {
					f.set(trg, srcFieldVal);
				}

				System.out.println(String.format(
						"%s was updated. Old value: %s, new value: %s",
						new Object[] { f, trgFieldVal, srcFieldVal }));
			}

			return false;
		}

		return true;
	}

	private static boolean areStriclyEquals(Object srcFieldVal,
			Object trgFieldVal) {
		if ((srcFieldVal instanceof String)) {
			return ((String) srcFieldVal).equals(trgFieldVal);
		}
		if ((srcFieldVal instanceof Integer)) {
			return ((Integer) srcFieldVal).equals(trgFieldVal);
		}

		throw new IllegalStateException("Not yet implemented!");
	}

	private static boolean checkFuzzyEquals(Object src, Object trg, Field f,
			boolean sync) throws IllegalAccessException {
		Object srcFieldVal = f.get(src);
		Object trgFieldVal = f.get(trg);

		FuzzyEquals fe = (FuzzyEquals) f.getAnnotation(FuzzyEquals.class);
		if (!areFuzzyEquals(srcFieldVal, trgFieldVal, fe.tolerance())) {
			if (sync) {
				f.set(trg, srcFieldVal);
				System.out.println(String.format(
						"%s was updated. Old value: %s, new value: %s",
						new Object[] { f, trgFieldVal, srcFieldVal }));
			}
			
			return false;
		}
		
		return true;
	}

	private static boolean areFuzzyEquals(Object srcFieldVal,
			Object trgFieldVal, double tolerance) {
		if ((srcFieldVal instanceof Double)) {
			double srcDoub = ((Double) srcFieldVal).doubleValue();
			double trgDoub = ((Double) trgFieldVal).doubleValue();

			return fuzzyEquals(srcDoub, trgDoub, tolerance);
		}

		throw new IllegalStateException("Not yet implemented!");
	}

}
