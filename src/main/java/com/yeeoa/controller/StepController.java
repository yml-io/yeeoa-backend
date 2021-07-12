package com.yeeoa.controller;

import com.yeeoa.bean.Step;
import com.yeeoa.bean.Task;
import com.yeeoa.bean.responsebody.FailedResp;
import com.yeeoa.bean.responsebody.HttpResponse;
import com.yeeoa.bean.responsebody.SuccessResp;
import com.yeeoa.bean.responsebody.SuccessWithDataResp;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonObject;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonProperty;
import com.yeeoa.service.StepService;
import com.yeeoa.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(description = "Step Interface")
@Controller("step")
@RestController
@RequestMapping("/step")
public class StepController {

	@Autowired
	private StepService stepService;

    @ApiOperation(value = "step 列表", notes="不帶分頁")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public HttpResponse list(@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");

		List<Step> stepList = this.stepService.queryAll();
		SuccessWithDataResp successResp = new SuccessWithDataResp();
		successResp.setData(stepList);
		return successResp;
	}

    @ApiOperation(value = "獲取 step詳細信息", notes="獲取詳細信息")
	@RequestMapping(value = "/query_by_id", method = RequestMethod.POST)
	public HttpResponse queryById(@ApiJsonObject(name = "step_query_by_id", value = {
			@ApiJsonProperty(key = "id", example = "2", description = "查询的 ID")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		Step step = this.stepService.queryById(sid);
		if (step != null) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(step);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "創建 step", notes="可傳遞部分參數")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public HttpResponse create(@ApiJsonObject(name = "step_create", value = {
			@ApiJsonProperty(key = "taskID", example = "2", description = "所属 task ID"),
			@ApiJsonProperty(key = "stepID", example = "", description = "文本"),
			@ApiJsonProperty(key = "estimatedTimes", example = "", description = "文本"),
			@ApiJsonProperty(key = "stepActions", example = "", description = "文本"),
			@ApiJsonProperty(key = "stepDetails", example = "", description = "性别"),
			@ApiJsonProperty(key = "displayPage", example = "", description = "文本"),
			@ApiJsonProperty(key = "page", example = "", description = "文本"),
			@ApiJsonProperty(key = "tableResource", example = "", description = "文本"),
			@ApiJsonProperty(key = "status", example = "", description = "文本")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		Step step = new Step();

		step.setTaskID((Integer)requestParams.get("taskID"));
		step.setStepID((String)requestParams.getOrDefault("stepID", ""));
		step.setEstimatedTimes((String)requestParams.getOrDefault("estimatedTimes", ""));
		step.setStepActions((String)requestParams.getOrDefault("stepActions", ""));
		step.setStepDetails((String)requestParams.getOrDefault("stepDetails", ""));
		step.setDisplayPage((String)requestParams.getOrDefault("displayPage", ""));
		step.setPage((String)requestParams.getOrDefault("page", ""));
		step.setTableResource((String)requestParams.getOrDefault("tableResource", ""));
		step.setStatus((String)requestParams.getOrDefault("status", ""));

		int result = this.stepService.add(step);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(step);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "更新 step", notes="可部分更新")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public HttpResponse update(@ApiJsonObject(name = "step_update", value = {
			@ApiJsonProperty(key = "id", example = "1", description = "更新的 ID"),
			@ApiJsonProperty(key = "taskID", example = "2", description = "所属 task ID"),
			@ApiJsonProperty(key = "stepID", example = "", description = "文本"),
			@ApiJsonProperty(key = "estimatedTimes", example = "", description = "文本"),
			@ApiJsonProperty(key = "stepActions", example = "", description = "文本"),
			@ApiJsonProperty(key = "stepDetails", example = "", description = "性别"),
			@ApiJsonProperty(key = "displayPage", example = "", description = "文本"),
			@ApiJsonProperty(key = "page", example = "", description = "文本"),
			@ApiJsonProperty(key = "tableResource", example = "", description = "文本"),
			@ApiJsonProperty(key = "status", example = "", description = "文本")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		Step step = new Step();

		step.setId((Integer) requestParams.get("id"));

		step.setTaskID((Integer)requestParams.getOrDefault("taskID", null));
		step.setStepID((String)requestParams.getOrDefault("stepID", null));
		step.setEstimatedTimes((String)requestParams.getOrDefault("estimatedTimes", null));
		step.setStepActions((String)requestParams.getOrDefault("stepActions", null));
		step.setStepDetails((String)requestParams.getOrDefault("stepDetails", null));
		step.setDisplayPage((String)requestParams.getOrDefault("displayPage", null));
		step.setPage((String)requestParams.getOrDefault("page", null));
		step.setTableResource((String)requestParams.getOrDefault("tableResource", null));
		step.setStatus((String)requestParams.getOrDefault("status", null));

		int result = this.stepService.update(step);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(step);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "刪除 step", notes="真真正正的刪除 step")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public HttpResponse delete(@ApiJsonObject(name = "step_delete", value = {
			@ApiJsonProperty(key = "id", example = "1", description = "更新的 ID")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		int result = this.stepService.deleteById(sid);
		if (result > 0) {
			SuccessResp successResp = new SuccessResp();
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "獲取 task 層級下面全部 step", notes="帶分頁功能")
	@RequestMapping(value = "/get_step_by_task_id", method = RequestMethod.POST)
	public HttpResponse getStepByTaskID(@ApiJsonObject(name = "get_step_by_task_id", value = {
			@ApiJsonProperty(key = "id", example = "1", description = "task 的 ID"),
			@ApiJsonProperty(key = "offset", example = "3", description = ""),
			@ApiJsonProperty(key = "count", example = "5", description = "")
	}) @RequestBody Map<String, Object> requestParam) {
		String sessionToken = (String)requestParam.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParam.get("id");
		int offset = (Integer)requestParam.get("offset");
		int limit = (Integer)requestParam.get("count");

		List<Step> result = this.stepService.queryByTaskID(sid, offset, limit);
		class Info {
			public Object getStepList() {
				return stepList;
			}

			public void setStepList(Object stepList) {
				this.stepList = stepList;
			}

			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}

			Object stepList;
			int count;
		}
		Info info = new Info();
		info.setStepList(result);
		info.setCount(this.stepService.queryAndcount(" task_id=" + sid));
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(info);
		return successWithDataResp;
	}
	/*
	*  Custom controller methods
	*
	* */
}


