package tdt4250.dict2.core;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface Dict {
	String getDictName();
	DictSearchResult search(String searchKey);
}
