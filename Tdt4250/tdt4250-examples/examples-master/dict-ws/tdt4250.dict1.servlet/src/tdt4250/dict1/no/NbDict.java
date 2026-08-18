package tdt4250.dict1.no;

import tdt4250.dict1.core.Dict;
import tdt4250.dict1.core.DictSearchResult;

public class NbDict implements Dict {

	private NSFWords words = NSFWords.create("nb");
	
	@Override
	public String getDictName() {
		return "nb";
	}

	@Override
	public DictSearchResult search(String searchKey) {
		if (words.hasWord(searchKey)) {
			return new DictSearchResult(true, "Yes, " + searchKey + " was found!", null);
		} else {
			return new DictSearchResult(false, "No, " + searchKey + " wasn't found...", null);
		}
	}
}
