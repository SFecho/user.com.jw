package com.dmu.user.service.content;

import com.dmu.user.data.content.dao.ContentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.ListIterator;

@Service("contentService")
public class ContentService implements IContentService {
    @Resource
    ContentDao contentDao;

    @Override
    public String getPath(String params) {
        List<String> pathList =contentDao.getPath(params);

        ListIterator<String> pathItr = pathList.listIterator(pathList.size());

        StringBuffer stringBuffer = new StringBuffer(1 << 6);

        while(pathItr.hasPrevious()){
            String tmp = pathItr.previous();
            stringBuffer.append("/");
            stringBuffer.append(tmp);
        }

        return stringBuffer.toString();
    }
}
