package tdt4250.servletsupport;

import java.util.Map;

public interface IPostHandler {
	public Object handlePostBody(Object root, String postBody, Map<String, Object> params) throws RuntimeException;
}
