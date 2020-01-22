package org.java.model.pay.callback;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public abstract class AbstractPayCallbackTemplate {

	/**
	 * 验证参数、签名
	 * @param req
	 * @param resp
	 * @return
	 */
	public abstract Map<String, String> verifySignature(HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 异步回调执行业务逻辑
	 * 
	 * @param verifySignature
	 */
	public abstract String asyncService(Map<String, String> verifySignature);

	public abstract String failResult();

	public abstract String successResult();


	/**
	 * 异步回调业务处理
	 * @param req
	 * @param resp
	 * @return
	 */
	public String asyncCallBack(HttpServletRequest req, HttpServletResponse resp) {
		// 验证报文参数
		Map<String, String> verifySignature = verifySignature(req, resp);
		// 将日志根据支付id存放到数据库中
		String paymentId = verifySignature.get("payId");
		if (StringUtils.isEmpty(paymentId)) {
			return failResult();
		}
		// 采用异步形式写入日志到数据库中
		payLog(paymentId, verifySignature);
		// 执行的异步回调业务逻辑
		return asyncService(verifySignature);
	}

	/**
	 *
	 * 
	 * 采用多线程技术或者MQ形式进行存放到数据库中
	 * 
	 * @param paymentId
	 * @param verifySignature
	 */
	private void payLog(String paymentId, Map<String, String> verifySignature) {
		log.info(">>paymentId:{paymentId},verifySignature:{}", verifySignature);
	}

}
