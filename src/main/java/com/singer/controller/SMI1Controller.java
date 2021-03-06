package com.singer.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.singer.service.SMI1Service;
import com.singer.vo.SM01Vo;

@Controller("smi1Controller")
public class SMI1Controller {

	private final Log log = LogFactory.getLog(SMI1Controller.class);

	@Resource(name = "smi1Service")
	private SMI1Service smi1Service;

	@RequestMapping(value = "/smi1Page", method = RequestMethod.GET)
	public ModelAndView page() {
		ModelAndView model = new ModelAndView("smi1view");

		return model;
	}

	@ResponseBody
	@RequestMapping(value = "smi1", method = RequestMethod.POST)
	public ResponseEntity<SM01Vo> selectSMI1Vo(@RequestBody SM01Vo sm01Vo) throws Exception {
		log.debug("enter smi1 get");

		List<SM01Vo> list = smi1Service.selectSMI1Vo(sm01Vo);
		sm01Vo.setList(list);

		log.debug("exit smi1 get");
		return new ResponseEntity<SM01Vo>(sm01Vo, HttpStatus.OK);
	}
}
