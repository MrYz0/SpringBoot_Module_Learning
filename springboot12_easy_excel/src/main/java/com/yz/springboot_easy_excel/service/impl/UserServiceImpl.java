package com.yz.springboot_easy_excel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.springboot_easy_excel.config.excel_istener.ExcelUserDTOListener;
import com.yz.springboot_easy_excel.mapper.UserMapper;
import com.yz.springboot_easy_excel.pojo.User;
import com.yz.springboot_easy_excel.pojo.dto.UserDTO;
import com.yz.springboot_easy_excel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
* @author yangzhou
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-25 15:36:10
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    /**
     * 导入数据
     *
     * @param inputStream 输入流
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void importData(InputStream inputStream) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, UserDTO.class, new ExcelUserDTOListener(baseMapper)).sheet().doRead();
        log.info("importData finished");
    }

    /**
     * 用户列表dtodata
     *
     * @return {@link List}<{@link UserDTO}>
     */
    @Override
    public List<UserDTO> listUserDTOData() {

        List<User> list = baseMapper.selectList(null);
        //创建ExcelDictDTO列表，将Dict列表转换成ExcelDictDTO列表
        ArrayList<UserDTO> excelUserDTOArrayList = new ArrayList<>(list.size());
        list.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);
            excelUserDTOArrayList.add(userDTO);

        });

        return excelUserDTOArrayList;
    }
}




