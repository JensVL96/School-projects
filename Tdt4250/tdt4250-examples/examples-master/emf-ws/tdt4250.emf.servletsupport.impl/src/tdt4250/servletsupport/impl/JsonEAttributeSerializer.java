package tdt4250.servletsupport.impl;

import java.io.IOException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import com.fasterxml.jackson.core.JsonGenerator;

public interface JsonEAttributeSerializer {
	public boolean accept(EObject eObject, EAttribute attr, Object value);
	public void serialize(EObject eObject, EAttribute attr, Object value, String altFieldName, JsonGenerator generator) throws IOException;
}
