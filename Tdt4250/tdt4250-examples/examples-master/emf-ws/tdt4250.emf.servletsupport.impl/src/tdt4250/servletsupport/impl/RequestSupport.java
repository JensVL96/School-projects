package tdt4250.servletsupport.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tdt4250.servletsupport.IReferenceResolver;

public class RequestSupport {

	private EList<? extends Object> objects;

	public RequestSupport(Collection<?> objects) {
		this.objects = new BasicEList<Object>(objects);
	}

	public EList<? extends Object> getObjects() {
		return objects;
	}

	private IReferenceResolver referenceResolver;
	
	public IReferenceResolver getReferenceResolver() {
		return referenceResolver;
	}

	public void setReferenceResolver(IReferenceResolver referenceResolver) {
		this.referenceResolver = referenceResolver;
	}

	//

	public Object nextStep(String step, Map<String, ?> parameters) {
		Integer num = null;
		// try element selection
		try {
			num = Integer.valueOf(step);
		} catch (NumberFormatException e) {
		}
		if (num != null) {
			if (num < 0 || num >= objects.size()) {
				throw new IllegalArgumentException("Cannot access element " + num + " of a list with " + objects.size() + " elements");
			}
			Object selected = selectElement(objects, num);
			return objects = (selected != null ? ECollections.singletonEList(selected) : ECollections.emptyEList());
		}
		// try value selection
		int pos = step.length() - 1;
		String ops = "<>=";
		while (pos >= 0) {
			if (ops.indexOf(step.charAt(pos)) >= 0) {
				break;
			}
			pos--;
		}
		if (pos >= 0) {
			boolean rel1 = ops.indexOf(step.charAt(pos - 1)) < 0;
			String rel = (rel1 ? step.substring(pos, pos + 1) : step.substring(pos - 1, pos + 1)); 
			return objects = selectRelation(objects, step.substring(0, pos - (rel1 ? 0 : 1)), step.substring(pos + 1), rel);
		}
		EList<Object> values = null;
		EObjectEList<?> eObjects = null;
		int count = 0;
		// try feature and operation mapping
		for (Object object : objects) {
			boolean hasValue = false;
			Object value = null;
			if (object instanceof EObject) {
				EObject eObject = (EObject) object;
				EStructuralFeature feature = findEStructuralFeature(eObject, step);
				if (feature != null) {
					hasValue = true;
					value = getFeatureValue(eObject, feature);
				} else {
					EOperation op = findEOperation(eObject, step, parameters);
					if (op != null) {
						hasValue = true;
						value = invokeOperation(eObject, op, parameters);							
					} else if (referenceResolver != null) {
						value = referenceResolver.resolveReference(step, eObject);
						hasValue |= (value != null);
					}
				}
			}
			if (hasValue) {
				count++;
				if (eObjects == null && values == null && value instanceof EObjectEList<?>) {
					eObjects = ((EObjectEList<?>) value);
				} else if (value != null) {
					if (values == null) {
						values = new BasicEList<Object>();
						if (eObjects != null) {
							values.addAll(eObjects);
							eObjects = null;
						}
					}
					if (value instanceof Collection<?>) {
						values.addAll((Collection<?>) value);
					} else {
						values.add(value);								
					}
				}
			}
		}
		if (count == 0) {
			EList<EObject> selected = null;
			if (Character.isUpperCase(step.charAt(0))) {
				selected = selectEClass(objects, step);
			}
			if (selected == null || selected.isEmpty()) {
				selected = selectKey(objects, step.split(","));					
			}
			return objects = selected;
		}
		if (values != null) {
			return objects = values;
		}
		if (eObjects != null) {
			return objects = eObjects;
		}
		return null;
	}

	public static final String REQUEST_SUPPORT_ANNOTATION_SOURCE = RequestSupport.class.getName();

	protected <T extends EStructuralFeature> T findEStructuralFeature(String featureName, EList<T> features) {
		for (T feature : features) {
			if (feature.getName().equals(featureName) && AnnotationUtil.includeTypedElement(feature, REQUEST_SUPPORT_ANNOTATION_SOURCE, true)) {
				return feature;
			}
		}
		return null;
	}
	
	protected EStructuralFeature findEStructuralFeature(EObject target, String featureName) {
		return findEStructuralFeature(featureName, target.eClass().getEAllStructuralFeatures());
	}
	
	protected Object getFeatureValue(EObject target, EStructuralFeature feature) {
		return target.eGet(feature);
	}

	protected EOperation findEOperation(EObject target, String opName, Map<String, ?> parameters) {
		EOperation bestOp = null;
		int bestCount = -1;
		nextOp: for (EOperation op : target.eClass().getEAllOperations()) {
			if (opName.equals(op.getName()) && AnnotationUtil.includeTypedElement(op, REQUEST_SUPPORT_ANNOTATION_SOURCE, true)) {
				nextParam: for (EParameter param : op.getEParameters()) {
					if (parameters != null && parameters.containsKey(param.getName())) {
						continue nextParam;
					}
					continue nextOp;
				}
				int count = op.getEParameters().size();
				if (bestOp == null || (count > bestCount)) {
					bestOp = op;
					bestCount = count;
				}
			}
		}
		return bestOp;
	}

	protected Object invokeOperation(EObject target, EOperation op, Map<String, ?> parameters) {
		EList<Object> args = new BasicEList<Object>();
		for (EParameter param : op.getEParameters()) {
			Object paramValue = null, arg = null;
			paramValue = parameters.get(param.getName());
			Throwable t = null;
			EClassifier type = param.getEType();
			if (type.isInstance(paramValue)) {
				arg = paramValue;
			} else if (type instanceof EDataType) {
				try {
					arg = EcoreUtil.createFromString((EDataType) type, String.valueOf(paramValue));
				} catch (Exception e) {
					t = e;
				}
			} else if (getReferenceResolver() != null) {
				arg = getReferenceResolver().resolveReference(String.valueOf(paramValue), target);
			}
			if (arg != null && type.isInstance(arg)) {
				if (param.isMany()) {
					arg = ECollections.singletonEList(arg);
				}
				args.add(arg);
			} else {
				throw new IllegalArgumentException("Couldn't convert " + paramValue + " to " + type, t);
			}
		}
		try {
			return target.eInvoke(op, args);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException("Couldn't invoke " + op.getName() + " on " + args);
		}
	}
	
	protected Object selectElement(List<?> objects, int num) {
		int size = objects.size();
		if (num < 0) {
			num = size + num;
		}
		return (num >= 0 && num < size ? objects.get(num) : null);
	}
	
	protected interface EObjectFilter {
		public boolean accept(EObject eObject);
	}
	
	protected EList<EObject> selectEObjects(Collection<?> objects, EObjectFilter filter) {
		EList<EObject> filtered = new BasicEList<EObject>();
		for (Object object : objects) {
			if (object instanceof EObject && filter.accept((EObject) object)) {
				filtered.add((EObject) object);
			}
		}
		return filtered;
	}

	protected EList<EObject> selectRelation(Collection<?> objects, final String featureName, final String featureValueString, final String rel) {
		return selectEObjects(objects, eObject -> {
			EAttribute attr = findEStructuralFeature(featureName, eObject.eClass().getEAllAttributes());
			if (attr != null) {
				Object featureValue1 = eObject.eGet(attr);
				Object featureValue2 = EcoreUtil.createFromString(attr.getEAttributeType(), featureValueString);
				if (rel == null || rel.equals("=")) {
					if (featureValue1 == featureValue2 || (featureValue1 != null && featureValue1.equals(featureValue2))) {
						return true;
					}
				} else if (featureValue1 instanceof Comparable<?>) {
					try {
						int comp = ((Comparable) featureValue1).compareTo(featureValue2);
						return (rel.equals("==") && comp == 0)
								|| (rel.equals(">") && comp > 0)
								|| ((rel.equals(">=") || rel.equals("=>")) && comp >= 0)
								|| ((rel.equals("<=") || rel.equals("=<")) && comp <= 0)
								|| (rel.equals("<") && comp < 0)
								|| ((rel.equals("<>") || rel.equals("><")) && comp != 0);
					} catch (RuntimeException e) {
					}
				}
			}
			return false;
		});
	}

	protected EList<EObject> selectKey(Collection<?> objects, final String[] keyValueStrings) {
		return selectEObjects(objects, eObject -> {
			EStructuralFeature containingFeature = eObject.eContainingFeature();
			if (containingFeature instanceof EReference) {
				EList<EAttribute> keyAttributes = ((EReference) containingFeature).getEKeys();
				if (keyAttributes.size() == keyValueStrings.length) {
					for (int i = 0; i < keyAttributes.size(); i++) {
						EAttribute attr = keyAttributes.get(i);
						Object value = EcoreUtil.createFromString(attr.getEAttributeType(), keyValueStrings[i]);
						if (value == null || (! value.equals(eObject.eGet(attr)))) {
							return false;
						}
					}
					return true;
				}
			}
			return false;
		});
	}

	private EList<EObject> selectEClass(Collection<?> objects, final String step) {
		return selectEObjects(objects, new EObjectFilter() {
			private boolean accept(EClass eClass) {
				return eClass.getName().equals(step);
			}
			@Override
			public boolean accept(EObject eObject) {
				if (accept(eObject.eClass())) {
					return true;
				}
				for (EClass eClass : eObject.eClass().getEAllSuperTypes()) {
					if (accept(eClass)) {
						return true;
					}
				}
				return false;
			}
		});
	}
}
