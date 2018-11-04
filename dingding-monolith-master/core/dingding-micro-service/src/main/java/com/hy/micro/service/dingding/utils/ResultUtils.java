package com.hy.micro.service.dingding.utils;

import com.hy.micro.service.dingding.enums.ResultCode;
import com.hy.micro.service.dingding.vo.ResultVO;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */
public class ResultUtils {
	public static ResultVO<Object> success(Object data) {
		ResultVO<Object> resultVO = new ResultVO<Object>();

		resultVO.setCode(ResultCode.RESULT_SUCESS.getCode());
		resultVO.setData(data);
		resultVO.setMsg("success");

		return resultVO;
	}

	public static ResultVO<Object> success() {
		return success(null);
	}

	public static ResultVO<Object> error(Integer code, String msg) {
		ResultVO<Object> resultVO = new ResultVO<Object>();
		resultVO.setMsg(msg);
		resultVO.setCode(code);

		return resultVO;
	}

	public static ResultVO<Object> error(ResultCode resultCode) {
		ResultVO<Object> resultVO = new ResultVO<Object>();

		resultVO.setMsg(resultCode.getMsg());
		resultVO.setCode(resultCode.getCode());

		return resultVO;
	}
}
