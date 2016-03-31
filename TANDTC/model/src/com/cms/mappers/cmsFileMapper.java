package com.cms.mappers;

import com.cms.models.CmsFile;

import com.cms.models.CmsFolder;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface cmsFileMapper{
    List<CmsFile> getFiles(Map<String,Object> map,RowBounds rowBounds);
    int countFiles(Map<String,Object> map);
    int deleteFile(int fileId);
    int deleteFolder(int folderId);
    void insertFile(CmsFile file);
    void insertFolder(CmsFolder folder);
    CmsFile getFile(int fileId);
    CmsFolder getFolder(int folderId);
    List<CmsFolder> getSubFolders(int folderId);
    int isFileInUsed(int fileId);
    List<CmsFolder> getFolderByName(CmsFolder folder);
}