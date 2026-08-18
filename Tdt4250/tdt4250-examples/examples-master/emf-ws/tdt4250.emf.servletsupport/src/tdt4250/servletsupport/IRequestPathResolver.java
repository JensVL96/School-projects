package tdt4250.servletsupport;

import java.util.Collection;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface IRequestPathResolver {
	public Object getObjectForPath(Collection<? extends Object> rootObjects, String... segments);
}
