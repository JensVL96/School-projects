package tdt4250.servletsupport.impl;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;

import com.fasterxml.jackson.core.JsonGenerator;

public interface JsonEObjectSerializer {
	public boolean accept(EObject eObject);
	public void serialize(EObject eObject, JsonGenerator generator) throws IOException;
}
