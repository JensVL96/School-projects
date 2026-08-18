package tdt4250.servletsupport;

import java.util.Collection;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface IRequestQueryExecutor {
	public Object getRequestQueryResult(Collection<?> target, String op, Map<String, ?> parameters);
}
