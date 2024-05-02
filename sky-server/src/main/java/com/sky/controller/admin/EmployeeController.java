package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Api(tags = "员工相关接口")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;


    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }


    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "员工退出")
    public Result<String> logout() {
        return Result.success();
    }


    /**
     * 新增员工
     *
     * @param dto 员工信息
     *
     * @return Result
     */
    @PostMapping
    @ApiOperation(value = "新增员工")
    public Result saveEmp(@RequestBody EmployeeDTO dto) {

        employeeService.saveEmp(dto);
        return Result.success();

    }


    /**
     * 分页查询员工
     * @param dto 分页查询条件
     * @return Result
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询员工")
    public Result<PageResult> pageEmp(EmployeePageQueryDTO dto) {

        PageResult result = employeeService.pageEmp(dto);
        return Result.success(result);

    }


    /**
     * 修改员工账号状态
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "修改员工账号状态")
    public Result updateEmpStatus(@PathVariable Integer status, Long id) {

        employeeService.updateEmpStatus(status, id);
        return Result.success();

    }

    @GetMapping(path = "/{id}")
    public Result<Employee> findEmpById(@PathVariable Long id) {

        Employee employee = employeeService.findEmpById(id);
        return Result.success(employee);

    }


    @PutMapping
    public Result updateEmp(@RequestBody EmployeeDTO dto) {

        employeeService.updateEmp(dto);
        return Result.success();

    }

}
