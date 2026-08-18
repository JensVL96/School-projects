package tdt4250.servletsupport.impl;

import org.eclipse.emf.ecore.EObject;

import tdt4250.servletsupport.IReferenceResolver;

public abstract class AbstractReferenceResolver implements IReferenceResolver {
	
	private final int containerCount;
	
	public AbstractReferenceResolver(int containerCount) {
		this.containerCount = containerCount;
	}
	public AbstractReferenceResolver() {
		this(0);
	}

	@Override
	public EObject resolveReference(String reference, EObject context) {
		int count = this.containerCount;
		do {
			for (EObject contained : context.eContents()) {
				if (isReferenced(reference, contained)) {
					return contained;
				}
			}
			count--;
			context = context.eContainer();
		} while (context != null && count != -1);
		return null;
	}

	protected abstract boolean isReferenced(String reference, EObject eObject);
}
