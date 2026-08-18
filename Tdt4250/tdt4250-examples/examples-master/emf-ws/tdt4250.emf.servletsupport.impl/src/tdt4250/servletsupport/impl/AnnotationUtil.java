package tdt4250.servletsupport.impl;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class AnnotationUtil {

	public static boolean includeElement(EModelElement element, String annotationKey, boolean defaultInclude) {
		String include = EcoreUtil.getAnnotation(element, annotationKey, "include"), exclude = EcoreUtil.getAnnotation(element, annotationKey, "exclude");
		if (defaultInclude) {
			return (include == null || Boolean.valueOf(include)) && (exclude == null || (! Boolean.valueOf(exclude)));
		} else {
			return (include != null && Boolean.valueOf(include)) || (exclude != null && (! Boolean.valueOf(exclude)));			
		}
	}
	
	public static boolean excludeElement(EModelElement element, String annotationKey) {
		return AnnotationUtil.includeElement(element, annotationKey, false);
	}

	public static boolean includeElement(EModelElement element, String annotationKey) {
		return AnnotationUtil.includeElement(element, annotationKey, true);
	}
	
	public static <T extends ETypedElement> boolean includeTypedElement(T element, String annotationKey, boolean defaultInclude) {
		boolean explicitInclude = AnnotationUtil.includeElement(element, annotationKey, defaultInclude);
		if (explicitInclude != defaultInclude) {
			return explicitInclude;
		}
		EClassifier type = element.getEType();
		if (type != null) {
			explicitInclude = AnnotationUtil.includeElement(type, annotationKey, defaultInclude);
			if (explicitInclude != defaultInclude) {
				return explicitInclude;
			}
			EPackage ePackage = type.getEPackage();
			explicitInclude = AnnotationUtil.includeElement(ePackage, annotationKey, defaultInclude);
			if (explicitInclude != defaultInclude) {
				return explicitInclude;
			}
		}
		return defaultInclude;
	}
}
