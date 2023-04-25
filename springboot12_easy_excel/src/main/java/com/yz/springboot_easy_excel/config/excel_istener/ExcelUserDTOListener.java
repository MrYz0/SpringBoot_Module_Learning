package com.yz.springboot_easy_excel.config.excel_istener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yz.springboot_easy_excel.mapper.UserMapper;
import com.yz.springboot_easy_excel.pojo.dto.UserDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class ExcelUserDTOListener extends AnalysisEventListener<UserDTO> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<UserDTO> list = new ArrayList<UserDTO>();

    private UserMapper userMapper;

    //传入mapper对象
    public ExcelUserDTOListener(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 遍历每一行记录
     *
     * @param data         用户dto
     * @param context 分析上下文
     */
    @Override
    public void invoke(UserDTO data, AnalysisContext context) {
        log.info("解析到一条记录：{}",data);
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，放置数据几万条数据在内存，容易OOM
        if (list.size() > BATCH_COUNT) {
            saveData();
            // 存储完毕清理 list
            list.clear();
        }
    }


    /**
     * 所有数据解析完成了 都来调用
     *
     * @param analysisContext 分析上下文
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }


    /**
     * 加入存储数据库
     */
    private void saveData() {

        log.info("{}条数据，开始存储数据库！",list.size());
        userMapper.insertBatch(list);//批量插入
        log.info("存储数据库成功！");

    }
}
