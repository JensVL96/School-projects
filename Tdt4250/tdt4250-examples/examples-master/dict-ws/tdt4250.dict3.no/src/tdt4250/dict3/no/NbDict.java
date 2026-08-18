package tdt4250.dict3.no;

import org.osgi.service.component.annotations.Component;

import tdt4250.dict3.api.Dict;
import tdt4250.dict3.util.WordsDict;

@Component(
		property = {
				WordsDict.DICT_NAME_PROP + "=nb",
				WordsDict.DICT_RESOURCE_PROP + "=tdt4250.dict3.no#/tdt4250/dict3/no/nb.txt"}
		)
public class NbDict extends WordsDict implements Dict {
}
