package com.alprojects.guicetest;

import com.google.inject.Inject;

public class SimpleCopier implements ICopier {
	
	private IReader m_reader;
	private IWriter m_writer;
	
	@Inject
	public SimpleCopier( IReader reader, IWriter writer )
	{
		m_reader = reader;
		m_writer = writer;
	}

	public void copy() {
		m_writer.write(m_reader.read());
		
	}

}
