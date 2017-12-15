package org.chengying.com.list.company.entity;

/**
 * Created by yuanyuan on 17-12-15.
 */

public class Express {

    private String mTitle;
    private String mPinyin;

    public Express(String title, String pinyin) {
        this.mTitle = title;
        this.mPinyin = pinyin;
    }

    public Express() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getPinyin() {
        return mPinyin;
    }

    public void setPinyin(String mPinyin) {
        this.mPinyin = mPinyin;
    }
}
