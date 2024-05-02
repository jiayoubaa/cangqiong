package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);


    /**
     *  新增员工
     * @param dto 员工信息
     */
    void saveEmp(EmployeeDTO dto);


    /**
     * 分页查询
     * @param dto 条件
     * @return
     */
    PageResult pageEmp(EmployeePageQueryDTO dto);


    /**
     * 更新员工状态
     * @param status 状态
     * @param id 员工id
     */
    void updateEmpStatus(Integer status, Long id);


    Employee findEmpById(Long id);


    void updateEmp(EmployeeDTO dto);
}
