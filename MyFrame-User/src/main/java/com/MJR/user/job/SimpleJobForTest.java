package com.MJR.user.job;

import com.xxl.job.core.context.XxlJobContext;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Mjr
 * @version 1.0
 * @description: 测试xxl-job
 * @date 2023/4/6 0:03
 */
@Component
public class SimpleJobForTest {

    private  static Logger logger = LoggerFactory.getLogger(SimpleJobForTest.class);

    @XxlJob("SimpleJob")
    public void SimpleJob(){
        XxlJobHelper.log("nihao");
    }

}
