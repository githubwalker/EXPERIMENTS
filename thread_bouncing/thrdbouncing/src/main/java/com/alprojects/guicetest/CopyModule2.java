package com.alprojects.guicetest;

// http://samolisov.blogspot.ru/2008/04/guice-ioc-google.html

import com.google.inject.AbstractModule;

public class CopyModule2 extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		this.bind(IReader.class).to(SimpleReader.class);
		this.bind(IWriter.class).to(SimpleWriter.class);
		this.bind(ICopier.class).to(SimpleCopier.class);
		this.bind(IReader.class).annotatedWith(SpecialReader.class).to(SimpleSpecialReader.class);
	}

}
