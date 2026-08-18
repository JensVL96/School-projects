package tdt4250.servletsupport;

import org.eclipse.emf.ecore.EObject;
import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface IReferenceResolver {
	public EObject resolveReference(String reference, EObject context);
}
