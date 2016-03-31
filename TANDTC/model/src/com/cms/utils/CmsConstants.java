package com.cms.utils;

import java.util.ResourceBundle;

public class CmsConstants {
    public final static int PAGE_SIZE=10;
    public final static int TITLE_ID=-1;
    public final static int CONTENT_ID=-2;
    public final static int PERSPECTIVE_ID=-3;
    public final static int CATEGORY_ID=-4;
    public final static int PUBLISHDATE_ID=-5;
    public final static String TOKEN_ID="TOKEN_ID";
    public final static String TOKEN_VALUE="TOKEN_VALUE";
    public final static String VIETNAMESE="vi";
    public final static String ENGLISH="en";
    
    public final static String TEXT_AREA="textarea";
    public final static String SIMPLE_DATE="simpledate";
    public final static String FULL_DATE="fulldate";
    public final static String TEXT = "text";
    public final static String TEXT_EDITOR="editortext";
    public final static String IMAGE="image";
    public final static String FILE = "file";
    public final static String SELECT_BOX="selectbox";
    public final static String CHECK_BOX="checkbox";
    
    public final static String IMG_BROWSER="/browser/view.do?type=Images";
    public final static String IMG_SOURCE_LINK="/browser/browser.do?method=image&id="; 
    public final static String FILE_DOWNLOAD_LINK="/browser/upload?fileId="; 
    
    public final static String DRAFT="draft";
    public final static String UNPUBLIC="unpublic";
    public final static String PUBLIC="public";
    private final static String[] ITEM_STATUSES = {DRAFT,UNPUBLIC,PUBLIC};
    
    private final static String editor="editor";
    private final static String admin="admin";
    public final static String itemStatusValue(String status){
        ResourceBundle rs = ResourceBundle.getBundle("com.cms.application");
        String[] itemStatusValues = rs.getString("ITEM_STATUS_VALUES").split(",");
        for(int i=0;i<ITEM_STATUSES.length;i++){
            if(ITEM_STATUSES[i].equalsIgnoreCase(status)){
                return itemStatusValues[i];
            }
        }
        return "";
    }
    public final static String getLabel(String name){
        ResourceBundle rs = ResourceBundle.getBundle("com.cms.application");
        return rs.getString(name);
    }
}
