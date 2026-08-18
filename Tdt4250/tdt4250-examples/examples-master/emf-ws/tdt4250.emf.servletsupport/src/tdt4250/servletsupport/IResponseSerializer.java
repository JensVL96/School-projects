package tdt4250.servletsupport;

import java.io.Writer;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface IResponseSerializer {

	public void serialize(Object object, Writer writer) throws Exception;
}
