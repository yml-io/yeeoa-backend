package com.yeeoa.controller;

import com.yeeoa.bean.settings.AppProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "About Interface")
@Controller("about")
@RestController
public class AboutController {
	@Autowired
	private AppProperties blogProperties;

    @ApiOperation(value = "获取相关信息", notes="获取相关信息")
	@RequestMapping("/about")
	String index() {
		return blogProperties.getName()+"，"+blogProperties.getVersion();
	}
}
