package com.yeeoa.bean.textprocess;

import com.yeeoa.bean.Lesson;
import com.yeeoa.bean.Unit;

import java.util.List;

public class WordStatisticsBlock {
    private List<WordDescriptionBlock> wordDescriptionBlockList;
    private String word;
    private int count;
    private List<Integer> lessonIDs;
    private Integer unitID;


    public List<WordDescriptionBlock> getWordDescriptionBlockList() {
        return wordDescriptionBlockList;
    }

    public void setWordDescriptionBlockList(List<WordDescriptionBlock> wordDescriptionBlockList) {
        this.wordDescriptionBlockList = wordDescriptionBlockList;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public List<Integer> getLessonIDs() {
        return lessonIDs;
    }

    public void setLessonIDs(List<Integer> lessonIDs) {
        this.lessonIDs = lessonIDs;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }



}
