package com.yeeoa.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yeeoa.bean.Program;
import com.yeeoa.bean.jsonbody.*;
import com.yeeoa.bean.responsebody.FailedResp;
import com.yeeoa.bean.responsebody.HttpResponse;
import com.yeeoa.bean.responsebody.SuccessResp;
import com.yeeoa.bean.responsebody.SuccessWithDataResp;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonObject;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonProperty;
import com.yeeoa.service.ProgramService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(description = "Program Interface")
@Controller("program")
@RestController
@RequestMapping("/program")
public class ProgramController {

	@Autowired
	private ProgramService programService;

    @ApiOperation(value = "獲取program列表", notes="不帶分頁")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public HttpResponse list(@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");

		List<Program> programList = this.programService.queryAll();
		SuccessWithDataResp successResp = new SuccessWithDataResp();
		successResp.setData(programList);
		return successResp;
	}

    @ApiOperation(value = "獲取詳細信息", notes="獲取詳細信息")
	@RequestMapping(value = "/query_by_id", method = RequestMethod.POST)
	public HttpResponse queryById(@ApiJsonObject(name = "program_query_by_id", value = {
			@ApiJsonProperty(key = "id", example = "1", description = "指定的 ID")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		Program program = this.programService.queryById(sid);
		if (program != null) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(program);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "創建program", notes="可傳遞部分參數")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public HttpResponse create(@ApiJsonObject(name = "program_create", value = {
			@ApiJsonProperty(key = "programID", example = "", description = "文本"),
			@ApiJsonProperty(key = "title", example = "", description = "文本"),
			@ApiJsonProperty(key = "brief", example = "", description = "文本"),
			@ApiJsonProperty(key = "summary", example = "", description = "文本"),
			@ApiJsonProperty(key = "name", example = "", description = "文本"),
			@ApiJsonProperty(key = "owner", example = "", description = "文本，存储id还是其他待定"),
			@ApiJsonProperty(key = "imgSrc", example = "", description = "文本"),
			@ApiJsonProperty(key = "grade", example = "", description = "文本"),
			@ApiJsonProperty(key = "semester", example = "", description = "文本"),
			@ApiJsonProperty(key = "objectives", example = "{}", description = "Json 字符串"),
			@ApiJsonProperty(key = "status", example = "", description = "文本"),
			@ApiJsonProperty(key = "feature", example = "", description = "文本"),
			@ApiJsonProperty(key = "agelevel", example = "", description = "文本"),
			@ApiJsonProperty(key = "overview", example = "", description = "文本"),
			@ApiJsonProperty(key = "school", example = "", description = "文本")
	}) @RequestBody Map<String, Object> requestParams) {
        String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
        Program program = new Program();
        program.setProgramID((String) requestParams.getOrDefault("programID", null));
        program.setTitle((String) requestParams.getOrDefault("title", null));
        program.setBrief((String) requestParams.getOrDefault("brief", null));
        program.setSummary((String) requestParams.getOrDefault("summary", null));
        program.setName((String) requestParams.getOrDefault("name", null));
        program.setOwner((String) requestParams.getOrDefault("owner", null));
        program.setImgSrc((String) requestParams.getOrDefault("imgSrc", null));
        program.setGrade((String) requestParams.getOrDefault("grade", null));
        program.setSemester((String) requestParams.getOrDefault("semester", null));
        program.setObjectives((String) requestParams.getOrDefault("objectives", null));
        program.setStatus((String) requestParams.getOrDefault("status", null));
        program.setFeature((String) requestParams.getOrDefault("feature", null));
        program.setAgelevel((String) requestParams.getOrDefault("agelevel", null));
        program.setOverview((String) requestParams.getOrDefault("overview", null));
        program.setSchool((String) requestParams.getOrDefault("school", null));

		int result = this.programService.add(program);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(program);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "更新program", notes="可傳遞部分參數")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public HttpResponse update(@ApiJsonObject(name = "program_update", value = {
			@ApiJsonProperty(key = "id", example = "1", description = "更新的 ID"),
			@ApiJsonProperty(key = "programID", example = "", description = "文本"),
			@ApiJsonProperty(key = "title", example = "", description = "文本"),
			@ApiJsonProperty(key = "brief", example = "", description = "文本"),
			@ApiJsonProperty(key = "summary", example = "", description = "文本"),
			@ApiJsonProperty(key = "name", example = "", description = "文本"),
			@ApiJsonProperty(key = "owner", example = "", description = "文本，存储id还是其他待定"),
			@ApiJsonProperty(key = "imgSrc", example = "", description = "文本"),
			@ApiJsonProperty(key = "grade", example = "", description = "文本"),
			@ApiJsonProperty(key = "semester", example = "", description = "文本"),
			@ApiJsonProperty(key = "objectives", example = "{}", description = "Json 字符串"),
			@ApiJsonProperty(key = "status", example = "", description = "文本"),
			@ApiJsonProperty(key = "feature", example = "", description = "文本"),
			@ApiJsonProperty(key = "agelevel", example = "", description = "文本"),
			@ApiJsonProperty(key = "overview", example = "", description = "文本"),
			@ApiJsonProperty(key = "school", example = "", description = "文本")
	}) @RequestBody Map<String, Object> requestParams) {
        String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		Program program = new Program();
		program.setId((Integer)requestParams.get("id"));

        program.setProgramID((String) requestParams.getOrDefault("programID", null));
        program.setTitle((String) requestParams.getOrDefault("title", null));
        program.setBrief((String) requestParams.getOrDefault("brief", null));
        program.setSummary((String) requestParams.getOrDefault("summary", null));
        program.setName((String) requestParams.getOrDefault("name", null));
        program.setOwner((String) requestParams.getOrDefault("owner", null));
        program.setImgSrc((String) requestParams.getOrDefault("imgSrc", null));
        program.setGrade((String) requestParams.getOrDefault("grade", null));
        program.setSemester((String) requestParams.getOrDefault("semester", null));
        program.setObjectives((String) requestParams.getOrDefault("objectives", null));
        program.setStatus((String) requestParams.getOrDefault("status", null));
        program.setFeature((String) requestParams.getOrDefault("feature", null));
        program.setAgelevel((String) requestParams.getOrDefault("agelevel", null));
        program.setOverview((String) requestParams.getOrDefault("overview", null));
        program.setSchool((String) requestParams.getOrDefault("school", null));

		int result = this.programService.update(program);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(program);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "刪除program", notes="真真正正的刪除program")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public HttpResponse delete(@ApiJsonObject(name = "program_delete", value = {
			@ApiJsonProperty(key = "id", example = "1", description = "更新的 ID")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		int result = this.programService.deleteById(sid);
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
	 *	@RequestBody
	 * */
    @ApiOperation(value = "獲取program列表", notes="帶分頁功能")
	@RequestMapping(value = "/get_program_list", method = RequestMethod.POST)
	public HttpResponse getProgramList(@ApiJsonObject(name = "get_program_list", value = {
			@ApiJsonProperty(key = "offset", example = "0", description = ""),
			@ApiJsonProperty(key = "count", example = "10", description = "")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		List<Program> programArray = this.programService.queryAllWithPaging(
				(Integer)(requestParams.getOrDefault("offset", 0)),
				(Integer)(requestParams.getOrDefault("count", 10))
		);
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		class DataWithCount {
			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}

			public Object getProgramList() {
				return programList;
			}

			public void setProgramList(Object programList) {
				this.programList = programList;
			}

			Object programList;
			int count;
		}
		DataWithCount dataWithCount = new DataWithCount();
		dataWithCount.setProgramList(programArray);
		dataWithCount.setCount(this.programService.queryAndcount(" 1=1 "));
		successWithDataResp.setData(dataWithCount);
		return successWithDataResp;
	}

    @ApiOperation(value = "不再使用", notes="不再使用")
	@RequestMapping(value = "/upinsert_program_base_info", method = RequestMethod.POST)
	public HttpResponse upinsertProgramBaseInfo(@RequestBody UpinsertProgramBaseInfoReq upinsertProgramBaseInfoReq) {
		Program program = new Program();
		program.setProgramID(upinsertProgramBaseInfoReq.getProgramID());
		program.setName(upinsertProgramBaseInfoReq.getProgramName());
		program.setGrade(upinsertProgramBaseInfoReq.getGrade());
		program.setOwner(upinsertProgramBaseInfoReq.getProgramOwner());
		program.setSemester(upinsertProgramBaseInfoReq.getSemester());
		program.setFeature(upinsertProgramBaseInfoReq.getProgramFeature());
		program.setAgelevel(upinsertProgramBaseInfoReq.getAgeLevel());
		program.setSchool(upinsertProgramBaseInfoReq.getSchool());
//		// convert threeobj to objective serialization json string
//		List<JSONObject> jsonObj = new ArrayList<JSONObject>();
//		for(Map<String, String> data : upinsertProgramBaseInfoReq.getThreeObj()) {
//			JSONObject obj = new JSONObject(data);
//			jsonObj.add(obj);
//		}
//		JSONArray jsonArray = new JSONArray(jsonObj);

		program.setObjectives(upinsertProgramBaseInfoReq.getThreeObj());
		program.setOverview(upinsertProgramBaseInfoReq.getProgramOverview());

		int programId = this.programService.add(program);
		System.out.println(program.getId());
		if (programId > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(program.getId());
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "獲取program前端綁定數據", notes="新增的選擇數據")
	@RequestMapping(value = "/get_program_id_info", method = RequestMethod.POST)
	public HttpResponse getProgramIdInfo(@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");

		List<Map<String, String>> programType = this.programService.getProgramType();
		List<Map<String, String>> country = this.programService.getCountry();
		List<Map<String, String>> schoolCode = this.programService.getSchoolCode();
		List<Map<String, String>> grade = this.programService.getGrade();
		class Info {
			public List<Map<String, String>> getProgramType() {
				return programType;
			}

			public void setProgramType(List<Map<String, String>> programType) {
				this.programType = programType;
			}

			public List<Map<String, String>> getCountry() {
				return country;
			}

			public void setCountry(List<Map<String, String>> country) {
				this.country = country;
			}

			public List<Map<String, String>> getSchoolCode() {
				return schoolCode;
			}

			public void setSchoolCode(List<Map<String, String>> schoolCode) {
				this.schoolCode = schoolCode;
			}

			public List<Map<String, String>> getGrade() {
				return grade;
			}

			public void setGrade(List<Map<String, String>> grade) {
				this.grade = grade;
			}

			List<Map<String, String>> programType ;
			List<Map<String, String>> country ;
			List<Map<String, String>> schoolCode;
			List<Map<String, String>> grade;

		}
		Info info = new Info();
		info.setProgramType(programType);
		info.setCountry(country);
		info.setSchoolCode(schoolCode);

		info.setGrade(grade);
		SuccessWithDataResp successResp = new SuccessWithDataResp();
		successResp.setData(info);
		return successResp;
	}

    @ApiOperation(value = "獲取semester數據", notes="新增的選擇數據")
	@RequestMapping(value = "/get_semester_by_country", method = RequestMethod.POST)
	public HttpResponse getSemesterByProgramId(@ApiJsonObject(name = "get_semester_by_country", value = {
			@ApiJsonProperty(key = "country", example = "AU", description = "AU,US,CN")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		String country = (String)requestParams.get("country");

		List<Map<String, String>> semestersMap = this.programService.getSemesterMap();

		for(int i = 0; i < semestersMap.size(); i++) {
			System.out.println(semestersMap.get(i));
			String semesters = semestersMap.get(i).get("code");
			if (semesters.equals(country)) {
				// decode semesters list from value of map
				String [] semestStr = semestersMap.get(i).get("value").split("\\|");
				SuccessWithDataResp successResp = new SuccessWithDataResp();
				successResp.setData(semestStr);
				return successResp;
			}
		}
		FailedResp failedResp = new FailedResp();
		return failedResp;
	}


}
