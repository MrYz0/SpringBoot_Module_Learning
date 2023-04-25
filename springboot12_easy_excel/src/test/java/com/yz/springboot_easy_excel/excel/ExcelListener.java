package com.yz.springboot_easy_excel.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yz.springboot_easy_excel.pojo.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class ExcelListener extends AnalysisEventListener<UserDTO> {

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(UserDTO userDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", userDTO);
    }

    // 读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头" + headMap);
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }
}
