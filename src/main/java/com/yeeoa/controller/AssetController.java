package com.yeeoa.controller;

import com.yeeoa.bean.Asset;
import com.yeeoa.bean.responsebody.FailedResp;
import com.yeeoa.bean.responsebody.HttpResponse;
import com.yeeoa.bean.responsebody.SuccessResp;
import com.yeeoa.bean.responsebody.SuccessWithDataResp;
import com.yeeoa.bean.settings.AppProperties;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonObject;
import com.yeeoa.bean.settings.swaggerutil.ApiJsonProperty;
import com.yeeoa.extract.ExtractBase;
import com.yeeoa.extract.ExtractSladeText;
import com.yeeoa.service.AssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(description = "Asset Interface")
@Controller("asset")
@RestController
@RequestMapping("/asset")
public class AssetController {
	private static final Logger logger = LoggerFactory.getLogger(AssetController.class);
	@Autowired
	private AssetService assetService;
	@Autowired
	private AppProperties appProperties;

    @ApiOperation(value = "獲取asset列表", notes="不带分页")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public HttpResponse list(@RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");

		List<Asset> assetList = this.assetService.queryAll();
		SuccessWithDataResp successResp = new SuccessWithDataResp();
		successResp.setData(assetList);
		return successResp;
	}

    @ApiOperation(value = "获取指定asset信息", notes="获取详细信息")
	@RequestMapping(value = "/query_by_id", method = RequestMethod.POST)
	public HttpResponse queryById(@ApiJsonObject(name = "asset_query_by_id", value = {
			@ApiJsonProperty(key = "id", example = "10", description = "Integer")
	}) @RequestBody Map<String, Object> requestParam) {
		String sessionToken = (String)requestParam.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParam.get("id");
		Asset asset = this.assetService.queryById(sid);
		if (asset != null) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(asset);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "创建asset资源描述", notes="支持多种文件资源的建立，非实体文件情况下调用")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public HttpResponse create(@ApiJsonObject(name = "asset_create", value = {
			@ApiJsonProperty(key = "lessonID", example = "10", description = "资源所属lesson"),
			@ApiJsonProperty(key = "type", example = "dictionary", description = "区分不同种类asset资源的字段，例如dictionary就是分词字典资源"),
			@ApiJsonProperty(key = "comment", example = "This is comment", description = "资源描述"),
			@ApiJsonProperty(key = "name", example = "资源描述名称", description = "前端应该传递文件名字或者对前端自定义"),
			@ApiJsonProperty(key = "hashid", example = "", description = "如果不传递会自动生成随机id"),
			@ApiJsonProperty(key = "url", example = "可以自定义的相对资源根存储的位置", description = "如果不传会自动确定"),
			@ApiJsonProperty(key = "parsedContent", example = "", description = "可以自定义上传文本提取的内容，可选")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		Asset asset = new Asset();

		asset.setLessonID((Integer)requestParams.get("lessonID"));
		asset.setType((String)requestParams.getOrDefault("type", ""));
		asset.setComment((String)requestParams.getOrDefault("comment", ""));
		asset.setName((String)requestParams.getOrDefault("name", ""));
		asset.setHashid((String)requestParams.getOrDefault("hashid", UUID.randomUUID().toString()));
		asset.setUrl((String)requestParams.getOrDefault("url", ""));
		asset.setParsedContent((String)requestParams.getOrDefault("parsedContent", ""));

		int result = this.assetService.add(asset);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(asset);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

    @ApiOperation(value = "更新asset資源信息", notes="當完成文件上傳之後，必須調用填寫必要數據")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public HttpResponse update(@ApiJsonObject(name = "asset_update", value = {
			@ApiJsonProperty(key = "id", example = "23", description = "更新的asset的id"),
			@ApiJsonProperty(key = "lessonID", example = "10", description = "资源所属lesson"),
			@ApiJsonProperty(key = "type", example = "dictionary", description = "区分不同种类asset资源的字段，例如dictionary就是分词字典资源"),
			@ApiJsonProperty(key = "comment", example = "This is comment", description = "资源描述"),
			@ApiJsonProperty(key = "name", example = "资源描述名称", description = "前端应该传递文件名字或者对前端自定义"),
			@ApiJsonProperty(key = "hashid", example = "", description = "可选"),
			@ApiJsonProperty(key = "url", example = "可以自定义的相对资源根存储的位置", description = "如果不传会自动确定"),
			@ApiJsonProperty(key = "parsedContent", example = "", description = "可以自定义上传文本提取的内容，可选")
	}) @RequestBody Map<String, Object> requestParams) {
		String sessionToken = (String)requestParams.getOrDefault("sessionToken", "");
		Asset asset = new Asset();

		asset.setId((Integer)requestParams.get("id"));
		asset.setLessonID((Integer)requestParams.getOrDefault("lessonID", null));
		asset.setType((String)requestParams.getOrDefault("type", null));
		asset.setComment((String)requestParams.getOrDefault("comment", null));
		asset.setName((String)requestParams.getOrDefault("name", null));
		asset.setHashid((String)requestParams.getOrDefault("hashid", null));
		asset.setUrl((String)requestParams.getOrDefault("url", null));
		asset.setParsedContent((String)requestParams.getOrDefault("parsedContent", null));

		int result = this.assetService.update(asset);
		if (result > 0) {
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			successResp.setData(asset);
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

	@ApiOperation(value = "删除asset", notes="这可是真真正正的删除哦")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public HttpResponse delete(@ApiJsonObject(name = "asset_delete", value = {
			@ApiJsonProperty(key = "id", example = "23", description = "更新的asset的id"),
	}) @RequestBody Map<String, Object> requestParam) {
		String sessionToken = (String)requestParam.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParam.get("id");
		int result = this.assetService.deleteById(sid);
		if (result > 0) {
			SuccessResp successResp = new SuccessResp();
			return successResp;
		} else {
			FailedResp failedResp = new FailedResp();
			return failedResp;
		}
	}

	@ApiOperation(value = "获取lesson层级下面全部asset", notes="带分页功能")
	@RequestMapping(value = "/get_asset_by_lesson_id", method = RequestMethod.POST)
	public HttpResponse getAssetByLessonID(@ApiJsonObject(name = "get_asset_by_lesson_id", value = {
			@ApiJsonProperty(key = "id", example = "23", description = "Integer，查询的lesson ID"),
			@ApiJsonProperty(key = "offset", example = "0", description = "Integer"),
			@ApiJsonProperty(key = "limit", example = "20", description = "Integer")
	}) @RequestBody Map<String, Object> requestParam) {
		String sessionToken = (String)requestParam.getOrDefault("sessionToken", "");
		int sid = (Integer)requestParam.get("id");
		int offset = (Integer)requestParam.get("offset");
		int limit = (Integer)requestParam.get("limit");
		List<Asset> result = this.assetService.queryByLessonID(sid, offset, limit);
		class Info {
			public Object getAssetList() {
				return assetList;
			}

			public void setAssetList(Object assetList) {
				this.assetList = assetList;
			}

			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}

			Object assetList;
			int count;
		}
		Info info = new Info();
		info.setAssetList(result);
		info.setCount(this.assetService.queryAndcount(" lesson_id=" + sid));
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(info);
		return successWithDataResp;
	}
	/*
	*  Custom controller methods
	*
	* */
	@ApiOperation(value = "文件上传接口", notes="支持多种类型的文件，返回的hashid是进一步操作的token")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public HttpResponse uploadAsset(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			FailedResp failedResp = new FailedResp();
			failedResp.setMessage("File can not be empty");
			return failedResp;
		}
		String fileName = file.getOriginalFilename();
		logger.info("upload file:: " + fileName);
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// upload file location
		String savedDir = appProperties.getUploadDir();

		String hashedFileName = UUID.randomUUID().toString();

		String fileSavePath = savedDir + hashedFileName;
		File dest = new File(fileSavePath);
		// create file when file not existed
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			SuccessWithDataResp successResp = new SuccessWithDataResp();
			Asset asset = new Asset();
			asset.setName(fileName);
			asset.setHashid(hashedFileName);
			asset.setUrl(fileSavePath);
			// create this asset
			this.assetService.add(asset);
			// hidden pure path and return
//			asset.setUrl("");
			successResp.setData(asset);
			return successResp;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FailedResp failedResp = new FailedResp();
		failedResp.setMessage("failed when upload file");
		return failedResp;
	}

	@ApiOperation(value = "获取asset", notes="外部调用")
	@RequestMapping(value = "/get_asset_by_hashid", method = RequestMethod.POST)
	public HttpResponse getAssetByHashID(@ApiJsonObject(name = "get_asset_by_hashid", value = {
			@ApiJsonProperty(key = "hashid", example = "", description = "asset 的 hashid")
	}) @RequestBody Map<String, Object> requestParam) {
		String sessionToken = (String)requestParam.getOrDefault("sessionToken", "");
		String hashid = (String)requestParam.get("hashid");
		Asset result = this.assetService.queryByHashID(hashid);
		// hidden pure path
		SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
		successWithDataResp.setData(result);
		return successWithDataResp;
	}

	@ApiOperation(value = "对指定文件开始提取文本", notes="目前只是支持 ppt/pptx 格式，注意，需要先执行 提取文本，本api只是对 asset的parsedContent操作 ")
	@RequestMapping(value = "/extract_asset_by_hashid", method = RequestMethod.POST)
	public HttpResponse extractAssetByHashID(@ApiJsonObject(name = "extract_asset_by_hashid", value = {
			@ApiJsonProperty(key = "hashid", example = "", description = "asset 的 hashid")
	}) @RequestBody Map<String, Object> requestParam) {
		String sessionToken = (String)requestParam.getOrDefault("sessionToken", "");
		String hashid = (String)requestParam.get("hashid");
		Asset asset = this.assetService.queryByHashID(hashid);

		ExtractBase extractor;
		Object extractResult;

		if (asset != null) {
			// according file extend name to choose extractor
			String fileName = asset.getName();
			int extIndex = fileName.lastIndexOf(".");
			if (fileName != null && !fileName.isEmpty() && extIndex > -1) {
				String suffixName = fileName.substring(extIndex);
				switch(suffixName) {
					case ".ppt":
					case ".pptx":
						extractor = new ExtractSladeText();
						extractor.setContent(asset);
						break;
					default:
						FailedResp failedResp = new FailedResp();
						failedResp.setMessage("unknown file type for extraction");
						return failedResp;
				}
				try {
					extractResult = extractor.doProcess();
				}catch (IOException ioe) {
					FailedResp failedResp = new FailedResp();
					failedResp.setMessage("read asset file failed");
					return failedResp;
				}
				asset.setParsedContent((String) extractResult);

				this.assetService.update(asset);
				// process this file according to associated asset type
				SuccessWithDataResp successWithDataResp = new SuccessWithDataResp();
				successWithDataResp.setData(asset);
				return successWithDataResp;
			}
		}
		FailedResp failedResp = new FailedResp();
		failedResp.setMessage("can not found asset which has this hashid");
		return failedResp;
	}

}


