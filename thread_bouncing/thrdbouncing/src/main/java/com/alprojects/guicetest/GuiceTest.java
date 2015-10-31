package com.alprojects.guicetest;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceTest {
	public static void testGuice() {
		Injector ij = Guice.createInjector(new CopyModule());
		ICopier svc = ij.getInstance(ICopier.class);

		svc.copy();
		return;
	}
}
