package org.java.model.pay.callback;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class UnionPayCallbackTemplate extends AbstractPayCallbackTemplate {

	@Override
	public Map<String, String> verifySignature(HttpServletRequest req, HttpServletResponse resp) {
		//验证业务
		return null;
	}

	@Override
	public String asyncService(Map<String, String> verifySignature) {
		return successResult();
	}

	@Override
	public String failResult() {
		return "fail";
	}

	@Override
	public String successResult() {
		return "success";
	}


}
