package org.chengying.com.list.company;

import android.util.Log;

import org.chengying.com.list.company.entity.Express;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/12/16.
 */

public class GeneratorDatas {

    private static String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static String mName = "测试公司";
    private static final int ITEM_SIZE = 1000;

    public static ArrayList<Express> getAllExpresses() {
        ArrayList<Express> expressArrayList = new ArrayList<>();
        for (int i = 0; i < mLetters.length; ++i) {
            String letter = mLetters[i];
            for (int j = 0; j < ITEM_SIZE; ++j) {
                Express express = new Express(mName, letter);
                expressArrayList.add(express);
            }
        }
        return expressArrayList;
    }

    public static ArrayList<Express> getHotExpresses() {
        ArrayList<Express> expressArrayList = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            expressArrayList.add(new Express("测试公司", "无效"));
        }
        return expressArrayList;
    }

    public static HashMap<String, Integer> getValueFirstIndex() {
        int pos = 1;
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String letter: mLetters) {
            hashMap.put(letter, pos);
            pos += ITEM_SIZE;
        }

        return hashMap;
    }
}
