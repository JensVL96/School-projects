package tdt4250.servletsupport.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import tdt4250.servletsupport.IReferenceResolver;
import tdt4250.servletsupport.IRequestPathResolver;
import tdt4250.servletsupport.IRequestQueryExecutor;

@Component(
	service={IRequestPathResolver.class, IRequestQueryExecutor.class}
)
public class RequestHandler implements IRequestPathResolver, IRequestQueryExecutor, IReferenceResolver {

	protected Collection<IReferenceResolver> referenceResolvers;

	@Reference(
		cardinality=ReferenceCardinality.MULTIPLE,
		policy=ReferencePolicy.DYNAMIC,
		unbind="removeReferenceResolver"
	)
	public void addReferenceResolver(IReferenceResolver referenceResolver) {
		if (referenceResolvers == null) {
			referenceResolvers = new ArrayList<IReferenceResolver>();
		}
		referenceResolvers.add(referenceResolver);
	}

	public void removeReferenceResolver(IReferenceResolver referenceResolver) {
		if (referenceResolvers != null) {
			referenceResolvers.remove(referenceResolver);
		}
	}

	@Override
	public EObject resolveReference(String reference, EObject context) {
		for (IReferenceResolver referenceResolver : referenceResolvers) {
			EObject resolved = referenceResolver.resolveReference(reference, context);
			if (resolved != null) {
				return resolved;
			}
		}
		return null;
	}

	@Override
	public Object getObjectForPath(Collection<? extends Object> rootObjects, String... segments) {
		RequestSupport requestSupport = new RequestSupport(rootObjects);
		requestSupport.setReferenceResolver(this);
		for (int i = 0; i < segments.length; i++) {
			String segment = segments[i];
			requestSupport.nextStep(segment, null);
		}
		return requestSupport.getObjects();
	}

	@Override
	public Object getRequestQueryResult(Collection<?> target, String op, Map<String, ?> parameters) {
		RequestSupport requestSupport = new RequestSupport(new BasicEList<Object>(target));
		requestSupport.setReferenceResolver(this);
		return requestSupport.nextStep(op, parameters);
	}
}
