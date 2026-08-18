package tdt4250.servletsupport.impl;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import tdt4250.servletsupport.IResourceProvider;

@Component(
		configurationPid = ResourceProvider.FACTORY_PID,
		configurationPolicy = ConfigurationPolicy.REQUIRE
		)
public class ResourceProvider implements IResourceProvider {

	public static final String FACTORY_PID = "tdt4250.servletsupport.impl.ResourceProvider";

	public static final String RESOURCE_NAME_PROP = "resourceName";
	public static final String RESOURCE_URI_PROP = "resourceUri";

	private URI uri;
	private Resource resource;
	
	public ResourceProvider() {
	}
	
	public ResourceProvider(URI uri) {
		this.uri = uri;
	}
	public void setUri(URI uri) {
		this.uri = uri;
	}
	
	public @interface ResourceProviderConfig {
		String name();
		String resourceUri();
	}

	@Activate
	public void activate(BundleContext bc, ResourceProviderConfig config) {
		update(bc, config);
	}

	@Modified
	public void modify(BundleContext bc, ResourceProviderConfig config) {
		update(bc, config);		
	}

	protected void update(BundleContext bc, ResourceProviderConfig config) {
		this.resource = null;
		this.uri = URI.createURI(config.resourceUri());
	}

	public ResourceProvider(Resource resource) {
		this.resource = resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	public Resource getResource() {
		if (resource == null && uri != null) {
			ResourceSet resourceSet = new ResourceSetImpl();
			configureResourceSet(resourceSet);
			Resource resource = resourceSet.createResource(uri);
			try {
				resource.load(null);
				this.resource = resource;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return this.resource;
	}

	protected void configureResourceSet(ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
	}

	private String name;
	private EClass rootObjectClass;
	
	@Override
	public String getName() {
		if (name != null) {
			return name;
		}
		if (resource != null) {
			return defaultName(resource.getURI());
		} else if (uri != null) {
			return defaultName(uri);			
		}
		return null;
	}
	
	public void setRootObjectClass(EClass rootObjectClass) {
		this.rootObjectClass = rootObjectClass;
	}

	public static String defaultName(URI uri) {
		return uri.trimFileExtension().lastSegment().replace('.', '/');
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Collection<? extends Object> getRootObjects() {
		Collection<? extends Object> objects = getResource().getContents();
		if (rootObjectClass != null) {
			objects = EcoreUtil.getObjectsByType(objects, rootObjectClass);
		}
		return objects;
	}
}
