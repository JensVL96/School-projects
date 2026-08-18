package tdt4250.dict3.api;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface Dict {
	String getDictName();
	DictSearchResult search(String searchKey);
}
