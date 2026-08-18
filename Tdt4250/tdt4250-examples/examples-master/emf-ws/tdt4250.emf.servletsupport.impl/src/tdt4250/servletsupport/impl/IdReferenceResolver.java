package tdt4250.servletsupport.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osgi.service.component.annotations.Component;

import tdt4250.servletsupport.IReferenceResolver;

@Component
public class IdReferenceResolver extends AbstractReferenceResolver implements IReferenceResolver {

	@Override
	protected boolean isReferenced(String reference, EObject eObject) {
		for (EAttribute attr : eObject.eClass().getEAllAttributes()) {
			if (attr.isID() && eObject.eIsSet(attr) && reference.equals(EcoreUtil.convertToString(attr.getEAttributeType(), eObject.eGet(attr)))) {
				return true;
			}
		}
		return false;
	}
}
