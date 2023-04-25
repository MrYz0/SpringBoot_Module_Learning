package com.yz.springboot_easy_excel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yz.springboot_easy_excel.pojo.User;
import com.yz.springboot_easy_excel.pojo.dto.UserDTO;

import java.io.InputStream;
import java.util.List;

/**
* @author yangzhou
* @description 针对表【user】的数据库操作Service
* @createDate 2023-04-25 15:36:10
*/
public interface UserService extends IService<User> {
    /**
     * 导入数据
     *
     * @param inputStream 输入流
     */
    void importData(InputStream inputStream);

    /**
     * 导出数据
     *
     * @return {@link List}<{@link UserDTO}>
     */
    List<UserDTO> listUserDTOData();
}
