package com.dmu.user.service.content;

import com.dmu.user.data.content.dao.ContentDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.ListIterator;

@Service("contentService")
public class ContentService implements IContentService {
    @Resource
    ContentDao contentDao;

    private static final Logger logger = LogManager.getLogger(ContentService.class);
    @Override
    public String getPath(String fileName) {
        List<String> pathList =contentDao.getPath(fileName);

        ListIterator<String> pathItr = pathList.listIterator(pathList.size());

        StringBuffer stringBuffer = new StringBuffer(1 << 6);

        while(pathItr.hasPrevious()){
            String tmp = pathItr.previous();
            stringBuffer.append("/");
            stringBuffer.append(tmp);
        }

        return stringBuffer.toString();
    }

    @Override
    public void writePath(String dirPath, String newFileName, String originFileName, int layer) {
        StringBuffer stringBuffer = new StringBuffer(1 << 6);

        logger.info(layer);
        contentDao.writePath(dirPath, newFileName, originFileName, layer);
    }


}
