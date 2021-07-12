package com.yeeoa.controller;

import com.yeeoa.bean.Asset;
import com.yeeoa.bean.Lesson;
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
import com.yeeoa.wordcount.WordCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "Lesson Interface")
@Controller("lesson")
@RestController
@RequestMapping("/lesson")
public class LessonController {

	@Autowired
	private LessonService lessonService;
	@Autowired
	private AssetService assetService;
	@Autowired
	private WordCountService wordCountService;

    @ApiOperation(value = "獲取lesson列表", notes="不帶分頁功能")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public HttpResponse list(@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");

		List<Lesson> lessonList = this.lessonService.queryAll();
		SuccessWithDataResp successResp = new SuccessWithDataResp();
		successResp.setData(lessonList);
		return successResp;
	}

    @ApiOperation(value = "獲取lesson詳細信息", notes="獲取詳細信息")
	@RequestMapping(value = "/query_by_id", method = RequestMethod.POST)
	public HttpResponse queryById(@ApiJsonObject(name = "lesson_query_by_id", value = {
			@ApiJsonProperty(key = "id", example = "10", description = "Integer")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		Lesson lesson = this.lessonService.queryById(sid);
		if (lesson != null) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(lesson);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "創建lesson", notes="可傳遞部分數據")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public HttpResponse create(@ApiJsonObject(name = "lesson_create", value = {
			@ApiJsonProperty(key = "unitID", example = "22", description = "所属 unit， 可选"),
			@ApiJsonProperty(key = "userID", example = "30", description = "所属 user， 可选"),
			@ApiJsonProperty(key = "lessonNo", example = "Lesson Label", description = "文本"),
			@ApiJsonProperty(key = "title", example = "", description = "Lesson 标题，可选"),
			@ApiJsonProperty(key = "timelength", example = "", description = "文本"),
			@ApiJsonProperty(key = "focus", example = "", description = "文本"),
			@ApiJsonProperty(key = "imperatives", example = "", description = "文本"),
			@ApiJsonProperty(key = "description", example = "", description = "文本"),
			@ApiJsonProperty(key = "stage", example = "", description = "文本"),
			@ApiJsonProperty(key = "objectives", example = "", description = "Json 对象的文本"),
			@ApiJsonProperty(key = "status", example = "processing", description = "自定义状态")
	}) @RequestBody Map<String, Object> requestParams) {
		Lesson lesson = new Lesson();

		lesson.setUnitID((Integer) requestParams.get("unitID"));
		lesson.setUserID((Integer)requestParams.get("userID"));
		lesson.setLessonNo((String)requestParams.getOrDefault("lessonNo", ""));
		lesson.setTitle((String)requestParams.getOrDefault("title", ""));
		lesson.setTimelength((String)requestParams.getOrDefault("timelength", ""));
		lesson.setFocus((String)requestParams.getOrDefault("focus", ""));
		lesson.setImperatives((String)requestParams.getOrDefault("imperatives", ""));
		lesson.setDescription((String)requestParams.getOrDefault("description", ""));
		lesson.setStage((String)requestParams.getOrDefault("stage", ""));
		lesson.setObjectives((String)requestParams.getOrDefault("objectives", ""));
		lesson.setStage((String)requestParams.getOrDefault("status", ""));


		int result = this.lessonService.add(lesson);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(lesson);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "更新lesson", notes="可傳遞部分數據")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public HttpResponse update(@ApiJsonObject(name = "lesson_update", value = {
			@ApiJsonProperty(key = "id", example = "1", description = "Lesson 的 ID"),
			@ApiJsonProperty(key = "unitID", example = "22", description = "所属 unit， 可选"),
			@ApiJsonProperty(key = "userID", example = "30", description = "所属 user， 可选"),
			@ApiJsonProperty(key = "lessonNo", example = "Lesson Label", description = "文本"),
			@ApiJsonProperty(key = "title", example = "", description = "Lesson 标题，可选"),
			@ApiJsonProperty(key = "timelength", example = "", description = "文本"),
			@ApiJsonProperty(key = "focus", example = "", description = "文本"),
			@ApiJsonProperty(key = "imperatives", example = "", description = "文本"),
			@ApiJsonProperty(key = "description", example = "", description = "文本"),
			@ApiJsonProperty(key = "stage", example = "", description = "文本"),
			@ApiJsonProperty(key = "objectives", example = "", description = "Json 对象的文本"),
			@ApiJsonProperty(key = "status", example = "processing", description = "自定义状态")
	}) @RequestBody Map<String, Object> requestParams) {
		Lesson lesson = new Lesson();
		lesson.setId((Integer) requestParams.get("id"));
		lesson.setUnitID((Integer) requestParams.getOrDefault("unitID", null));
		lesson.setUserID((Integer)requestParams.getOrDefault("userID", null));
		lesson.setLessonNo((String)requestParams.getOrDefault("lessonNo", null));
		lesson.setTitle((String)requestParams.getOrDefault("title", null));
		lesson.setTimelength((String)requestParams.getOrDefault("timelength", null));
		lesson.setFocus((String)requestParams.getOrDefault("focus", null));
		lesson.setImperatives((String)requestParams.getOrDefault("imperatives", null));
		lesson.setDescription((String)requestParams.getOrDefault("description", null));
		lesson.setStage((String)requestParams.getOrDefault("stage", null));
		lesson.setObjectives((String)requestParams.getOrDefault("objectives", null));
		lesson.setStage((String)requestParams.getOrDefault("status", null));
//

		int result = this.lessonService.update(lesson);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(lesson);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "刪除lesson", notes="真真正正的刪除哦")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public HttpResponse delete(@ApiJsonObject(name = "lesson_delete", value = {
			@ApiJsonProperty(key = "id", example = "1", description = "Lesson 的 ID")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParams.get("id");

		int result = this.lessonService.deleteById(sid);
		if (result > 0) {
			SuccessResp successResp = new SuccessResp();
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "獲取unit層級下面的lesson列表", notes="帶分頁功能")
	@RequestMapping(value = "/get_lesson_by_unit_id", method = RequestMethod.POST)
	public HttpResponse getLessonByUnitID(@ApiJsonObject(name = "get_lesson_by_unit_id", value = {
			@ApiJsonProperty(key = "id", example = "1", description = "Unit 的 ID"),
			@ApiJsonProperty(key = "offset", example = "0", description = ""),
			@ApiJsonProperty(key = "count", example = "10", description = "")
	}) @RequestBody Map<String, Object> requestParam) {
		String sessionToken = (String)requestParam.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParam.get("id");
		int offset = (Integer)requestParam.get("offset");
		int limit = (Integer)requestParam.get("count");

		List<Lesson> result = this.lessonService.queryByUnitID(sid, offset, limit);
		class Info {
			public Object getLessonList() {
				return lessonList;
			}

			public void setLessonList(Object lessonList) {
				this.lessonList = lessonList;
			}

			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}

			Object lessonList;
			int count;
		}
		Info info = new Info();
		info.setLessonList(result);
		info.setCount(this.lessonService.queryAndcount(" unit_id=" + sid));
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(info);
		return successWithDataResp;
	}
	/*
	*  Custom controller methods
	*
	* */
    @ApiOperation(value = "獲取lesson列表", notes="帶分頁功能")
	@RequestMapping(value = "/get_lesson_list", method = RequestMethod.POST)
	public HttpResponse getLessonListWithUnitProgram(@ApiJsonObject(name = "get_lesson_list", value = {
			@ApiJsonProperty(key = "offset", example = "0", description = ""),
			@ApiJsonProperty(key = "count", example = "10", description = "")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.get("sessionToken");
		int offset = (Integer)requestParams.getOrDefault("offset", 0);
		int count = (Integer)requestParams.getOrDefault("count", 10);

		List<Lesson> lessonList = this.lessonService.queryWithJoinUnit(offset, count);
		class Info {
			public Object getLessonList() {
				return lessonList;
			}

			public void setLessonList(Object lessonList) {
				this.lessonList = lessonList;
			}

			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}

			Object lessonList;
			int count;
		}
		Info info = new Info();
		info.setLessonList(lessonList);
		info.setCount(this.lessonService.queryAndcount(" 1=1 " ));
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(info);
		return successWithDataResp;
	}


    @ApiOperation(value = "詞頻統計", notes="lesson層次下面的資源文件")
	@RequestMapping(value = "/word_count", method = RequestMethod.POST)
	public HttpResponse lessonWordCount(@ApiJsonObject(name = "lesson_word_count", value = {
			@ApiJsonProperty(key = "lessonID", example = "4", description = "Lesson Id 的所属 asset"),
			@ApiJsonProperty(key = "assetCount", example = "20", description = "可选")
	}) @RequestBody Map<String, Object> requestParams) {
		Map<String, List<WordDescriptionBlock>> dictionary = wordCountService.getWordCountDictionary();
		Map<String, WordStatisticsBlock> wordCountStatics = new HashMap<>();

		String sessionToken = (String)requestParams.get("sessionToken");
		int lessonID = (Integer)requestParams.get("lessonID");
		int assetCount = (Integer)requestParams.getOrDefault("assetCount", 9999);
		Lesson lesson = this.lessonService.queryById(lessonID);
		List<Asset> assetList = this.assetService.queryByLessonID(lesson.getId(), 0, assetCount);
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
							wordStatisticsBlock.setUnitID(null);
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


//		Info info = new Info();
//		info.setUnitList(unitArray);
//		info.setCount(this.unitService.queryAndcount(" 1=1 " ));
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(wordCountStatics);
		return successWithDataResp;
	}

}


