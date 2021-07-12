package com.yeeoa.wordcount;

import com.yeeoa.bean.Asset;
import com.yeeoa.bean.settings.AppProperties;
import com.yeeoa.bean.textprocess.WordDescriptionBlock;
import com.yeeoa.service.AssetService;
import com.yeeoa.bean.textprocess.ExcelSheetPO;
import com.yeeoa.wordcount.utils.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/*
* build a object to count word on text
* */
@Service("wordCountService")
public class WordCountService {
    private static final Logger logger = LoggerFactory.getLogger(WordCountService.class);
    private final String dictionaryTagAsset = "dictionary";
    private final Integer maxReadRowOFExcel = 5000;
    private final Integer maxReadColumnOFExcel = 100;

    @Autowired
    private AssetService assetService;
//    @Autowired
//    private ExcelUtil excelUtil;
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private TextPreProcessService textPreProcessService;

    public Map<String, List<WordDescriptionBlock>> getWordCountDictionary() {
        return wordCountDictionary;
    }

    public void setWordCountDictionary(Map<String, List<WordDescriptionBlock>> wordCountDictionary) {
        this.wordCountDictionary = wordCountDictionary;
    }

    private Map<String, List<WordDescriptionBlock>> wordCountDictionary;

    // 需要每次系统启动或者 在执行上传字典，增删改查字典的时候调用初始化
    @PostConstruct
    private void initDictionary(){
        wordCountDictionary = new HashMap<>();

        // get all activated asset which type is dictionary file
        List<Asset> fileList = this.assetService.queryByType(dictionaryTagAsset, 0, 999);
        List<Asset> activatedFileList = fileList.stream().filter((Asset asset) ->
            asset.getEnabled() != 0
        ).collect(Collectors.toList());
        // main dictionary data structure
//        Map<String, WordDescriptionBlock> wordCountDictionary = new LinkedHashMap<>();
        // iterate over dictionary file and completed this structure
        activatedFileList.forEach(dictionaryFile -> {

            List<ExcelSheetPO> excelSheetPOList = null;
            try {
               excelSheetPOList = ExcelUtil.readExcel(
                        new File(dictionaryFile.getUrl()),
                        maxReadRowOFExcel, maxReadColumnOFExcel);
            }catch(FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            }catch (IOException ioe) {
                ioe.printStackTrace();
            }
                // for every sheet of this excel file
            if (excelSheetPOList != null) {
                logger.info("Loading Dictionary File" + dictionaryFile.getUrl());
                excelSheetPOList.forEach(excelSheetPO -> {
                    //get filg1 from file name + sheet name
                    String fileNameSheetName = dictionaryFile.getUrl() + "|" + excelSheetPO.getSheetName();
                    String flag1 = getFlagLevel1(fileNameSheetName);
                    String flag2 = getFlagLevel2(fileNameSheetName);
                    // table data
                    List<List<Object>> sheetData = excelSheetPO.getDataList();
                    sheetData.forEach(row -> {
//                        logger.info(row.toString());
                        String word = removeSpecialChars((String)row.get(0));
                        WordDescriptionBlock wordDescriptionBlock = new WordDescriptionBlock();
                        wordDescriptionBlock.setDictionaryName(fileNameSheetName);
                        wordDescriptionBlock.setFlag1(flag1);
                        wordDescriptionBlock.setFlag2(flag2);
                        wordDescriptionBlock.setWord(word);
                        wordDescriptionBlock.setPron((String)row.get(1));
                        wordDescriptionBlock.setProperty((String)row.get(2));
                        wordDescriptionBlock.setTranslation((String)row.get(3));
                        wordDescriptionBlock.setComment((String)row.get(4));
                        if (! wordCountDictionary.containsKey(word)) {
                            wordCountDictionary.put(word, new ArrayList<>());
                        }
                        wordCountDictionary.get(word).add(wordDescriptionBlock);
                    });
                });
            }
        });

    }

    private String removeSpecialChars(String text){
        return text == null? null: text.replaceAll("\\.", "")
                .replaceAll(",", "")
                .replaceAll("&", "")
                .replaceAll("!", "")
                .replaceAll("@", "")
                .replaceAll("#", "")
                .replaceAll("\\$", "")
                .replaceAll("%", "")
                .replaceAll("\\^", "")
                .replaceAll("&", "")
                .replaceAll("\\*", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll("\\-", "")
                .replaceAll("▲", "");
    }


    private String getFlagLevel1(String str) {
        Map<String, String> flagMap = new HashMap<String, String>(){{
            put("话题","R");
            put("考","K");
            put("常用","R");

        }};
        String result =  "K";
        for(String key : flagMap.keySet()) {
            if (str.indexOf(key) != -1) {
                result = flagMap.get(key);
                break;
            }
        }
        return result;
    }

    private String getFlagLevel2(String str) {
        Map<String, String> flagMap = new HashMap<String, String>(){{
            put("中考","L1");
            put("高考","L2");
            put("常用5000","L3");

        }};
        String result =  "K";
        for(String key : flagMap.keySet()) {
            if (str.indexOf(key) != -1) {
                result = flagMap.get(key);
                break;
            }
        }
        return result;
    }

    public List<String> getCleanText(String text) {
        return textPreProcessService.getlema(text);
    }
}
