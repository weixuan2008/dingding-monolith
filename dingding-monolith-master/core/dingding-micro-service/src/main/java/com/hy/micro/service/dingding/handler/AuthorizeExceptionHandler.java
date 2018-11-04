package com.hy.micro.service.dingding.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;
import com.hy.micro.service.dingding.config.ProjectUrlConfig;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.exception.UserAuthorizeException;
import com.hy.micro.service.dingding.utils.ResultUtils;
import com.hy.micro.service.dingding.vo.ResultVO;

/**
 * The unified exception handler for authorizing user login. 
 * @Author: Eddie.Wei   
 * @Date:   2018-10-11 
 * @github: https://github.com/weixuan2008
 */

@ControllerAdvice
public class AuthorizeExceptionHandler {

	@Autowired
	private ProjectUrlConfig projectUrlConfig;
	
	@ExceptionHandler(value = UserAuthorizeException.class)
	public ModelAndView handlerAuthorize() {
		// 1. jump to login page
		return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getOpenAuthorizeUrl())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=").concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
	}
	
	@ExceptionHandler(BloomingException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResultVO<Object> handlerSellException(BloomingException e) {
        return ResultUtils.error(e.getCode() , e.getMessage());
    }
}
