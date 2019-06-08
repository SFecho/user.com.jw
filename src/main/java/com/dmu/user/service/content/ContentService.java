package com.dmu.user.service.content;

import com.dmu.user.data.content.dao.ContentDao;
import com.dmu.user.data.content.model.Content;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.JSONParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Service("contentService")
public class ContentService implements IContentService {
    @Resource
    private ContentDao contentDao;
    private static final int ROOT_PARENT_ID = 0;
    private static final Logger logger = LogManager.getLogger(ContentService.class);
    @Override
    public Map<String, String> getPath(String fileName) {
        List<String> pathList =contentDao.getPath(fileName);
        Map<String, String> map = new HashMap<>();

        String filepath = pathList.remove(0);
        map.put("filepath", filepath);

        ListIterator<String> pathItr = pathList.listIterator(pathList.size());

        StringBuffer stringBuffer = new StringBuffer(1 << 6);

        while(pathItr.hasPrevious()){
            String tmp = pathItr.previous();
            stringBuffer.append("/");
            stringBuffer.append(tmp);
        }
        map.put("dirpath", stringBuffer.toString());
        return map;
    }

    @Override
    public int writePath(String dirPath, String newFileName, String originFileName, int layer, long size) {
        logger.info(layer);
        return contentDao.writePath(dirPath, newFileName, originFileName, layer, size);
    }

    @Override
    public List<Content> getRootDirInfo() {
        return contentDao.getContentList(ROOT_PARENT_ID);
    }

    @Override
    public List<Content> getContentList(JSONObject object) {
        int id = (Integer)object.get("id");
        return contentDao.getContentList(id);
    }


}
