package com.alprojects.guicetest;

// http://www.ibm.com/developerworks/ru/library/j-guice/
// http://www.infoq.com/news/2011/04/guice_30
// http://www.journaldev.com/2403/google-guice-dependency-injection-example-tutorial
// https://github.com/google/guice/wiki/BindingAnnotations
// https://luisfsgoncalves.wordpress.com/2010/09/08/generic-bindings-with-guice/

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;

public class GuiceTest {
	public static void testGuice() {
		Injector ij = Guice.createInjector(new CopyModule());
		ICopier svc = ij.getInstance(ICopier.class);

		svc.copy();
		return;
	}
	
	public static void testGuice2() {
		Injector inj = Guice.createInjector( new CopyModule2() );
		// ICopier cp = inj.getInstance(ICopier.class);
		ICopier cp = inj.getInstance(ICopier.class);
		
		// ICopier cp = inj.getInstance( Key.get(ICopier.class, SpecialReader.class) );
		// cp.copy();
		// Class cls = Key.class;
		// ICopier cp = inj.getInstance(Key.get(ICopier.class, SpecialReader.class));
		// IReader rd = inj.getInstance( Key.get(IReader.class, SpecialReader.class) );
		
		cp.copy();
		return;
	}
	
	public static void performTests() {
		testGuice2();
	}
}
