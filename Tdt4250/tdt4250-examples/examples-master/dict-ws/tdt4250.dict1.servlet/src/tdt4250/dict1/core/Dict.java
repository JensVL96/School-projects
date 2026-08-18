package tdt4250.dict1.core;

public interface Dict {
	String getDictName();
	DictSearchResult search(String searchKey);
}
