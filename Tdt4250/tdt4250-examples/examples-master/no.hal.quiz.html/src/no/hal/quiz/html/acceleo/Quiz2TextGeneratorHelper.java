package no.hal.quiz.html.acceleo;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

public class Quiz2TextGeneratorHelper {

	public EObject ancestor(EObject eObject, EClass eClass) {
   		EObject e = eObject;
		while (e != null) {
			if (eClass.isInstance(e)) {
				return e;
			}
			e = e.eContainer();
   		}
   		return null;
   	}
	
	public String name(EObject o, String prefix) {
		return prefix + o.eContainer().eContents().indexOf(o);
	}

	public String toFirstUpper(String s) {
		if (s == null || s.length() < 1 || Character.isUpperCase(s.charAt(0))) {
			return s;
		}
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}
	
	public String relativeName(EObject eObject, EClass eClass) {
		String name = "";
   		EObject e = eObject;
		while (e != null) {
			EObject child = e;
			e = e.eContainer();
			String childName = null;
			EStructuralFeature nameFeature = child.eClass().getEStructuralFeature("name");
			if (nameFeature != null && nameFeature.getEType() == EcorePackage.eINSTANCE.getEString()) {
				childName = (String) child.eGet(nameFeature);
			}
			if (childName == null) {
				EStructuralFeature feature = child.eContainingFeature();
				if (feature != null && e != null) {
					childName = feature.getName();
					if (feature.isMany()) {
						childName = childName + ((EList<?>) e.eGet(feature)).indexOf(child);
					}
				}
			}
			if (childName != null) {
				name = "_" + childName + name;
			}
			if (eClass != null && eClass.isInstance(child)) {
				return name;
			}
   		}
		return (eClass == null ? name : null);
	}
}
