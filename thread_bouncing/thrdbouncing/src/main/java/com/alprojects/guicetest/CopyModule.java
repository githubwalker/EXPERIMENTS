package com.alprojects.guicetest;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class CopyModule extends AbstractModule {

	@Override
	protected void configure() {
		this.bind(IReader.class)
			.to(SimpleReader.class)
			.in(Scopes.SINGLETON);
		
		this.bind(IWriter.class)
			.to(SimpleWriter.class)
			.in(Scopes.SINGLETON);
		
		this.bind(ICopier.class)
			.to(SimpleCopier.class)
			.in(Scopes.SINGLETON);
	}

}
