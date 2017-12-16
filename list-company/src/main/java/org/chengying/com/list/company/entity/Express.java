package org.chengying.com.list.company.entity;

/**
 * Created by yuanyuan on 17-12-15.
 */

public class Express {

    private String mTitle;
    private String mSectionId;


    public Express(String title, String sectionId) {
        this.mTitle = title;
        this.mSectionId = sectionId;
    }

    public Express() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getSectionId() {
        return mSectionId;
    }

    public void setSectionId(String mSectionId) {
        this.mSectionId = mSectionId;
    }
}
