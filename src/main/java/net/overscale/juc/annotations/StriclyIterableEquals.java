package net.overscale.juc.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ java.lang.annotation.ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StriclyIterableEquals {
	
	boolean deepCopy();
	
	boolean preserveOrder();
}
