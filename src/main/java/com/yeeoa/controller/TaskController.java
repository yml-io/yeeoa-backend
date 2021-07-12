package com.yeeoa.controller;

import com.yeeoa.bean.Asset;
import com.yeeoa.bean.Lesson;
import com.yeeoa.bean.Task;
import com.yeeoa.bean.responsebody.FailedResp;
import com.yeeoa.bean.responsebody.HttpResponse;
import com.yeeoa.bean.responsebody.SuccessResp;
import com.yeeoa.bean.responsebody.SuccessWithDataResp;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonObject;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonProperty;
import com.yeeoa.bean.textprocess.WordDescriptionBlock;
import com.yeeoa.bean.textprocess.WordStatisticsBlock;
import com.yeeoa.service.AssetService;
import com.yeeoa.service.LessonService;
import com.yeeoa.service.TaskService;
import com.yeeoa.wordcount.WordCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "Task Interface")
@Controller("task")
@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

    @ApiOperation(value = "task 列表", notes="不帶分頁")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public HttpResponse list(@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");

		List<Task> taskList = this.taskService.queryAll();
		SuccessWithDataResp successResp = new SuccessWithDataResp();
		successResp.setData(taskList);
		return successResp;
	}

    @ApiOperation(value = "獲取task 詳細信息", notes="獲取詳細信息")
	@RequestMapping(value = "/query_by_id", method = RequestMethod.POST)
	public HttpResponse queryById(@ApiJsonObject(name = "task_query_by_id", value = {
			@ApiJsonProperty(key = "id", example = "2", description = "查询的 ID")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		Task task = this.taskService.queryById(sid);
		if (task != null) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(task);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "創建 task", notes="可指定部分參數")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public HttpResponse create(@ApiJsonObject(name = "task_create", value = {
			@ApiJsonProperty(key = "lessonID", example = "7", description = "所属的 lesson ID， 可选"),
			@ApiJsonProperty(key = "taskID", example = "", description = "文本"),
			@ApiJsonProperty(key = "taskTitle", example = "" , description = "文本"),
			@ApiJsonProperty(key = "objectives", example = "", description = "Json 字符串"),
			@ApiJsonProperty(key = "status", example = "processing", description = "文本")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		Task task = new Task();

		task.setLessonID((Integer)requestParams.get("lessonID"));
		task.setTaskID((String)requestParams.getOrDefault("taskID", ""));
		task.setTaskTitle((String)requestParams.getOrDefault("taskTitle", ""));
		task.setObjectives((String)requestParams.getOrDefault("objectives", ""));
		task.setStatus((String)requestParams.getOrDefault("status", ""));

		int result = this.taskService.add(task);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(task);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "更新 task", notes="可更新部分信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public HttpResponse update(@ApiJsonObject(name = "task_update", value = {
			@ApiJsonProperty(key = "id", example = "4", description = "更新的 ID"),
			@ApiJsonProperty(key = "lessonID", example = "7", description = "所属的 lesson ID， 可选"),
			@ApiJsonProperty(key = "taskID", example = "", description = "文本"),
			@ApiJsonProperty(key = "taskTitle", example = "" , description = "文本"),
			@ApiJsonProperty(key = "objectives", example = "", description = "Json 字符串"),
			@ApiJsonProperty(key = "status", example = "processing", description = "文本")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		Task task = new Task();

		task.setId((Integer) requestParams.get("id"));

		task.setLessonID((Integer)requestParams.getOrDefault("lessonID", null));
		task.setTaskID((String)requestParams.getOrDefault("taskID", null));
		task.setTaskTitle((String)requestParams.getOrDefault("taskTitle", null));
		task.setObjectives((String)requestParams.getOrDefault("objectives", null));
		task.setStatus((String)requestParams.getOrDefault("status", null));

		int result = this.taskService.update(task);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(task);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "刪除 task", notes="真真正正的刪除task")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public HttpResponse delete(@ApiJsonObject(name = "task_delete", value = {
			@ApiJsonProperty(key = "id", example = "4", description = "更新的 ID")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		int result = this.taskService.deleteById(sid);
		if (result > 0) {
			SuccessResp successResp = new SuccessResp();
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "獲取 lesson 層級下全部 task", notes="帶分頁功能")
	@RequestMapping(value = "/get_task_by_lesson_id", method = RequestMethod.POST)
	public HttpResponse getTaskByLessonID(@ApiJsonObject(name = "get_task_by_lesson_id", value = {
			@ApiJsonProperty(key = "id", example = "4", description = "更新的 ID"),
			@ApiJsonProperty(key = "offset", example = "0", description = ""),
			@ApiJsonProperty(key = "count", example = "10", description = "")
	}) @RequestBody Map<String, Object> requestParam) {
		String sessionToken = (String)requestParam.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParam.get("id");
		int offset = (Integer)requestParam.get("offset");
		int limit = (Integer)requestParam.get("count");

		List<Task> result = this.taskService.queryByLessonID(sid, offset, limit);
		class Info {
			public Object getTaskList() {
				return taskList;
			}

			public void setTaskList(Object taskList) {
				this.taskList = taskList;
			}

			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}

			Object taskList;
			int count;
		}
		Info info = new Info();
		info.setTaskList(result);
		info.setCount(this.taskService.queryAndcount(" lesson_id=" + sid));
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(info);
		return successWithDataResp;
	}
	/*
	*  Custom controller methods
	*
	* */
}


