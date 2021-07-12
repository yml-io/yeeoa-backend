package com.yeeoa.controller;

import com.yeeoa.bean.Asset;
import com.yeeoa.bean.Lesson;
import com.yeeoa.bean.Unit;
import com.yeeoa.bean.jsonbody.UnitAddProgramUnitReq;
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
import com.yeeoa.service.UnitService;
import com.yeeoa.wordcount.WordCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "Unit Interface")
@Controller("unit")
@RestController
@RequestMapping("/unit")
public class UnitController {
	private static final Logger logger = LoggerFactory.getLogger(UnitController.class);
	@Autowired
	private UnitService unitService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private AssetService assetService;
	@Autowired
	private WordCountService wordCountService;

    @ApiOperation(value = "unit 全部列表", notes="不含分頁功能")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public HttpResponse list(@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");

		List<Unit> unitList = this.unitService.queryAll();
		SuccessWithDataResp successResp = new SuccessWithDataResp();
		successResp.setData(unitList);
		return successResp;
	}

    @ApiOperation(value = "查詢特定 unit", notes="獲取unit詳細信息")
	@RequestMapping(value = "/query_by_id", method = RequestMethod.POST)
	public HttpResponse queryById(@ApiJsonObject(name = "unit_query_by_id", value = {
			@ApiJsonProperty(key = "id", example = "10", description = "Integer")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		Unit unit = this.unitService.queryById(sid);
		if (unit != null) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(unit);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "創建 unit", notes="可傳部分參數")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public HttpResponse create(@ApiJsonObject(name = "unit_create", value = {
			@ApiJsonProperty(key = "programID", example = "1", description = "所属 program ID"),
			@ApiJsonProperty(key = "unitID", example = "test@163.com", description = "文本"),
			@ApiJsonProperty(key = "label", example = "", description = "文本"),
			@ApiJsonProperty(key = "name", example = "", description = "文本"),
			@ApiJsonProperty(key = "estimatedHours", example = "male", description = "文本"),
			@ApiJsonProperty(key = "actualHours", example = "", description = "文本"),
			@ApiJsonProperty(key = "description", example = "", description = "文本"),
			@ApiJsonProperty(key = "targetedProblems", example = "", description = "文本"),
			@ApiJsonProperty(key = "createdProducts", example = "", description = "文本"),
			@ApiJsonProperty(key = "sharedProblem", example = "", description = "文本"),
			@ApiJsonProperty(key = "objectives", example = "", description = "Json 字符串"),
			@ApiJsonProperty(key = "overviews", example = "", description = "文本"),
			@ApiJsonProperty(key = "status", example = "processing", description = "文本")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		Unit unit = new Unit();
		unit.setProgramID((Integer)requestParams.getOrDefault("programID", ""));
		unit.setUnitID((String)requestParams.getOrDefault("unitID", ""));
		unit.setLabel((String)requestParams.getOrDefault("label", ""));
		unit.setName((String)requestParams.getOrDefault("name", ""));

		unit.setEstimatedHours((String)requestParams.getOrDefault("estimatedHours", ""));
		unit.setActualHours((String)requestParams.getOrDefault("actualHours", ""));
		unit.setDescription((String)requestParams.getOrDefault("description", ""));
		unit.setTargetedProblems((String)requestParams.getOrDefault("targetedProblems", ""));
		unit.setCreatedProducts((String)requestParams.getOrDefault("createdProducts", ""));

		unit.setSharedProblem((String)requestParams.getOrDefault("sharedProblem", ""));
		unit.setObjectives((String)requestParams.getOrDefault("objectives", ""));
		unit.setOverviews((String)requestParams.getOrDefault("overviews", ""));
		unit.setStatus((String)requestParams.getOrDefault("status", ""));

		int result = this.unitService.add(unit);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(unit);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "更新 unit", notes="可以更新一部分參數")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public HttpResponse update(@ApiJsonObject(name = "unit_update", value = {
			@ApiJsonProperty(key = "id", example = "5", description = "更新的 ID"),
			@ApiJsonProperty(key = "programID", example = "1", description = "所属 program ID"),
			@ApiJsonProperty(key = "unitID", example = "test@163.com", description = "文本"),
			@ApiJsonProperty(key = "label", example = "", description = "文本"),
			@ApiJsonProperty(key = "name", example = "", description = "文本"),
			@ApiJsonProperty(key = "estimatedHours", example = "male", description = "文本"),
			@ApiJsonProperty(key = "actualHours", example = "", description = "文本"),
			@ApiJsonProperty(key = "description", example = "", description = "文本"),
			@ApiJsonProperty(key = "targetedProblems", example = "", description = "文本"),
			@ApiJsonProperty(key = "createdProducts", example = "", description = "文本"),
			@ApiJsonProperty(key = "sharedProblem", example = "", description = "文本"),
			@ApiJsonProperty(key = "objectives", example = "", description = "Json 字符串"),
			@ApiJsonProperty(key = "overviews", example = "", description = "文本"),
			@ApiJsonProperty(key = "status", example = "processing", description = "文本")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		Unit unit = new Unit();
		unit.setId((Integer)requestParams.get("id"));
		unit.setProgramID((Integer)requestParams.getOrDefault("programID", null));
		unit.setUnitID((String)requestParams.getOrDefault("unitID", null));
		unit.setLabel((String)requestParams.getOrDefault("label", null));
		unit.setName((String)requestParams.getOrDefault("name", null));

		unit.setEstimatedHours((String)requestParams.getOrDefault("estimatedHours", null));
		unit.setActualHours((String)requestParams.getOrDefault("actualHours", null));
		unit.setDescription((String)requestParams.getOrDefault("description", null));
		unit.setTargetedProblems((String)requestParams.getOrDefault("targetedProblems", null));
		unit.setCreatedProducts((String)requestParams.getOrDefault("createdProducts", null));

		unit.setSharedProblem((String)requestParams.getOrDefault("sharedProblem", null));
		unit.setObjectives((String)requestParams.getOrDefault("objectives", null));
		unit.setOverviews((String)requestParams.getOrDefault("overviews", null));
		unit.setStatus((String)requestParams.getOrDefault("status", null));

		int result = this.unitService.update(unit);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(unit);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "刪除 unit", notes="真真正正的刪除unit")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public HttpResponse delete(@ApiJsonObject(name = "unit_delete", value = {
			@ApiJsonProperty(key = "id", example = "5", description = "更新的 ID")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		int result = this.unitService.deleteById(sid);
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
    @ApiOperation(value = "查詢program附屬unit", notes="帶分頁功能")
	@RequestMapping(value = "/get_program_unit_list", method = RequestMethod.POST)
	public HttpResponse getProgramUnitList(@ApiJsonObject(name = "get_program_unit_list", value = {
			@ApiJsonProperty(key = "programID", example = "5", description = "所属的 program"),
			@ApiJsonProperty(key = "offset", example = "0", description = ""),
			@ApiJsonProperty(key = "count", example = "10", description = "")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.get("sessionToken");
		int programID = (Integer)requestParams.get("programID");
		int offset = (Integer)requestParams.getOrDefault("offset", 0);
		int count = (Integer)requestParams.getOrDefault("count", 10);

		List<Unit> unitArray = this.unitService.queryByProgramID(programID, offset, count);
		class Info {
			public Object getUnitList() {
				return unitList;
			}

			public void setUnitList(Object unitList) {
				this.unitList = unitList;
			}

			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}

			Object unitList;
			int count;
		}
		Info info = new Info();
		info.setUnitList(unitArray);
		info.setCount(this.unitService.queryAndcount(" program_id=" + programID));
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(info);
		return successWithDataResp;
	}

    @ApiOperation(value = "不再使用", notes="不再使用")
	@RequestMapping(value = "/add_program_unit", method = RequestMethod.POST)
	public HttpResponse addProgramUnit(@RequestBody UnitAddProgramUnitReq unitAddProgramUnitReq) {
		Unit unit = new Unit();
		unit.setProgramID(unitAddProgramUnitReq.getProgramID());
		unit.setUnitID(unitAddProgramUnitReq.getUnitId());
		unit.setName(unitAddProgramUnitReq.getUnitName());
		unit.setSharedProblem(unitAddProgramUnitReq.getSharedProblem());
//		// convert threeobj to objective serialization json string
//		List<JSONObject> jsonObj = new ArrayList<JSONObject>();
//		for(Map<String, String> data : unitAddProgramUnitReq.getThreeObj()) {
//			JSONObject obj = new JSONObject(data);
//			jsonObj.add(obj);
//		}
//		JSONArray jsonArray = new JSONArray(jsonObj);

		unit.setObjectives(unitAddProgramUnitReq.getThreeObj());
		int addResult = this.unitService.add(unit);
		if (addResult == 1) {
			SuccessResp successResp = new SuccessResp();
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "獲取unit列表", notes="帶分頁功能")
	@RequestMapping(value = "/get_unit_list", method = RequestMethod.POST)
	public HttpResponse getUnitListWithProgramInfo(@ApiJsonObject(name = "get_unit_list", value = {
			@ApiJsonProperty(key = "offset", example = "0", description = ""),
			@ApiJsonProperty(key = "count", example = "10", description = "")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.get("sessionToken");
		int offset = (Integer)requestParams.getOrDefault("offset", 0);
		int count = (Integer)requestParams.getOrDefault("count", 10);

		List<Unit> unitArray = this.unitService.queryWithJoinProgram(offset, count);
		class Info {
			public Object getUnitList() {
				return unitList;
			}
			public void setUnitList(Object unitList) {
				this.unitList = unitList;
			}
			public int getCount() {
				return count;
			}
			public void setCount(int count) {
				this.count = count;
			}

			Object unitList;
			int count;
		}
		Info info = new Info();
		info.setUnitList(unitArray);
		info.setCount(this.unitService.queryAndcount(" 1=1 " ));
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(info);
		return successWithDataResp;
	}

    @ApiOperation(value = "詞頻統計", notes="unit 層級下全部的 lesson 文檔")
	@RequestMapping(value = "/word_count", method = RequestMethod.POST)
	public HttpResponse unitWordCount(@ApiJsonObject(name = "unit_word_count", value = {
			@ApiJsonProperty(key = "unitID", example = "0", description = "词频统计的 unit， integer"),
			@ApiJsonProperty(key = "lessonCount", example = "", description = "可选，最大下级 lesson 数量，默认全部"),
			@ApiJsonProperty(key = "assetCount", example = "9999", description = "可选，最大下级 asset 数量，默认全部")
	}) @RequestBody Map<String, Object> requestParams) {
		Map<String, List<WordDescriptionBlock>> dictionary = wordCountService.getWordCountDictionary();
		Map<String, WordStatisticsBlock> wordCountStatics = new HashMap<>();

		String sessionToken = (String)requestParams.get("sessionToken");
		int unitID = (Integer)requestParams.get("unitID");
		int lessonCount = (Integer)requestParams.getOrDefault("lessonCount", 9999);
		int assetCount = (Integer)requestParams.getOrDefault("assetCount", 9999);
		List<Lesson> lessonList = this.lessonService.queryByUnitID(unitID, 0, lessonCount);
		lessonList.forEach(lesson -> {
			List<Asset> assetList = this.assetService.queryByLessonID(lesson.getId(), 0, assetCount);
			// word count on every word content of asset
			assetList.forEach(asset -> {
				String parsedContent = asset.getParsedContent();
				if (parsedContent != null && ! parsedContent.isEmpty()) {
					List<String> cleanText = wordCountService.getCleanText(parsedContent);
					cleanText.forEach(cleanWord -> {
						// check if this word existed in dictionary
						if (dictionary.containsKey(cleanWord)) {
							if (! wordCountStatics.containsKey(cleanWord)){
								WordStatisticsBlock wordStatisticsBlock = new WordStatisticsBlock();
								wordStatisticsBlock.setWordDescriptionBlockList( dictionary.get(cleanWord) );
								wordStatisticsBlock.setWord(cleanWord);
								wordStatisticsBlock.setUnitID(unitID);
								List<Integer> lessonList1 = new ArrayList<>();
								wordStatisticsBlock.setLessonIDs(lessonList1);
								wordStatisticsBlock.setCount(0);
								wordCountStatics.put(cleanWord, wordStatisticsBlock);
							}
							// add word count
							WordStatisticsBlock wordStatisticsBlock = wordCountStatics.get(cleanWord);
							wordStatisticsBlock.setCount(wordStatisticsBlock.getCount() + 1);
							wordStatisticsBlock.getLessonIDs().add(lesson.getId());
							wordCountStatics.put(cleanWord, wordStatisticsBlock);
						}
					});
				}
			});
		});

		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(wordCountStatics);
		return successWithDataResp;
	}
}


