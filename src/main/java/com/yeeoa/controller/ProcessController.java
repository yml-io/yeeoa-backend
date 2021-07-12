package com.yeeoa.controller;

import com.yeeoa.bean.Process;
import com.yeeoa.bean.responsebody.FailedResp;
import com.yeeoa.bean.responsebody.HttpResponse;
import com.yeeoa.bean.responsebody.SuccessResp;
import com.yeeoa.bean.responsebody.SuccessWithDataResp;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonObject;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonProperty;
import com.yeeoa.service.ProcessService;
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

@Api(description = "Process Interface")
@Controller("process")
@RestController
@RequestMapping("/process")
public class ProcessController {

	@Autowired
	private ProcessService processService;

    @ApiOperation(value = "獲取process列表", notes="不帶分頁功能")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public HttpResponse list(@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");

		List<Process> processList = this.processService.queryAll();
		SuccessWithDataResp successResp = new SuccessWithDataResp();
		successResp.setData(processList);
		return successResp;
	}

    @ApiOperation(value = "獲取process詳細信息", notes="獲取詳細信息")
	@RequestMapping(value = "/query_by_id", method = RequestMethod.POST)
	public HttpResponse queryById(@ApiJsonObject(name = "process_query_by_id", value = {
			@ApiJsonProperty(key = "id", example = "5", description = "process 的 ID")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		Process process = this.processService.queryById(sid);
		if (process != null) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(process);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "創建process", notes="可傳遞部分數據")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public HttpResponse create(@ApiJsonObject(name = "process_create", value = {
			@ApiJsonProperty(key = "unitID", example = "6", description = "所属 unit id"),
			@ApiJsonProperty(key = "name", example = "", description = "文本"),
			@ApiJsonProperty(key = "objectives", example = "", description = "文本"),
			@ApiJsonProperty(key = "status", example = "", description = "自定义 process 状态")
	}) @RequestBody Map<String, Object> requestParams) {
		com.yeeoa.bean.Process process = new com.yeeoa.bean.Process();
		process.setUnitID((Integer)requestParams.get("unitID"));
		process.setName((String)requestParams.getOrDefault("name", ""));
		process.setObjectives((String)requestParams.getOrDefault("objectives", ""));
		process.setStatus((String)requestParams.getOrDefault("status", ""));

		int result = this.processService.add(process);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(process);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "更新process信息", notes="可傳遞部分數據")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public HttpResponse update(@ApiJsonObject(name = "process_update", value = {
			@ApiJsonProperty(key = "id", example = "2", description = "更新的 process id"),
			@ApiJsonProperty(key = "unitID", example = "6", description = "所属 unit id"),
			@ApiJsonProperty(key = "name", example = "", description = "文本"),
			@ApiJsonProperty(key = "objectives", example = "", description = "文本"),
			@ApiJsonProperty(key = "status", example = "", description = "自定义 process 状态")
	}) @RequestBody Map<String, Object> requestParams) {
		com.yeeoa.bean.Process process = new com.yeeoa.bean.Process();
		process.setId((Integer)requestParams.get("id"));
		process.setUnitID((Integer)requestParams.getOrDefault("unitID", null));
		process.setName((String)requestParams.getOrDefault("name", null));
		process.setObjectives((String)requestParams.getOrDefault("objectives", null));
		process.setStatus((String)requestParams.getOrDefault("status", null));

		int result = this.processService.update(process);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(process);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "刪除process數據", notes="真真正正的刪除process")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public HttpResponse delete(@ApiJsonObject(name = "process_delete", value = {
			@ApiJsonProperty(key = "id", example = "2", description = "更新的 process id")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		int result = this.processService.deleteById(sid);
		if (result > 0) {
			SuccessResp successResp = new SuccessResp();
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

	/*
	*  Custom controller methods
	*
	* */
    @ApiOperation(value = "獲取unit層級的process列表", notes="帶分頁功能")
	@RequestMapping(value = "/get_process_list_by_unitid", method = RequestMethod.POST)
	public HttpResponse getProcessListByUnitID(@ApiJsonObject(name = "get_process_list_by_unitid", value = {
			@ApiJsonProperty(key = "unitID", example = "3", description = "所属 Unit id"),
			@ApiJsonProperty(key = "offset", example = "0", description = ""),
			@ApiJsonProperty(key = "count", example = "10", description = "")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.get("sessionToken");
		int unitID = (Integer)requestParams.get("unitID");
		int offset = (Integer)requestParams.getOrDefault("offset", 0);
		int count = (Integer)requestParams.getOrDefault("count", 10);

		List<Process> processArray = this.processService.queryByUnitID(unitID, offset, count);
		class Info {
			Object processList;
			int count;

			public Object getProcessList() {
				return processList;
			}

			public void setProcessList(Object processList) {
				this.processList = processList;
			}

			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}

		}
		Info info = new Info();
		info.setProcessList(processArray);
		info.setCount(this.processService.queryAndcount(" unit_id=" + unitID));
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(info);
		return successWithDataResp;
	}

    @ApiOperation(value = "獲取process列表", notes="帶分頁功能")
	@RequestMapping(value = "/get_process_list", method = RequestMethod.POST)
	public HttpResponse getProcessListWithUnitInfo(@ApiJsonObject(name = "get_process_list", value = {
			@ApiJsonProperty(key = "offset", example = "0", description = ""),
			@ApiJsonProperty(key = "count", example = "10", description = "")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.get("sessionToken");
		int offset = (Integer)requestParams.getOrDefault("offset", 0);
		int count = (Integer)requestParams.getOrDefault("count", 10);

		List<Process> processArray = this.processService.queryWithJoinUnit(offset, count);
		class Info {
			public Object getProcessList() {
				return processList;
			}

			public void setProcessList(Object processList) {
				this.processList = processList;
			}

			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}

			Object processList;
			int count;
		}
		Info info = new Info();
		info.setProcessList(processArray);
		info.setCount(this.processService.queryAndcount(" 1=1 " ));
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(info);
		return successWithDataResp;
	}
}


