package com.xu.wemall.controller.backend;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.wemall.commons.ResultObject;
import com.xu.wemall.controller.common.BaseController;
import com.xu.wemall.entry.Lover;
import com.xu.wemall.service.ILoverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RonnieXu
 * @since 2019-12-19
 */

@Slf4j
@Transactional(rollbackFor = Exception.class)
@Api(value = "用户接口集合", tags = "用户接口集合")
@RestController
@RequestMapping("/backend/user")
public class UserBackendController extends BaseController {

    @Autowired
    private ILoverService iLoverService;

    /**
     * 用户分页列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "用户分页列表", notes = "获取用户分页列表")
    @RequestMapping(value = "/paging", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "keyword", value = "关键字", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页长", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = false)
    }
    )
    public ResultObject paging(String keyword, Integer pageNum, Integer pageSize) {

        ResultObject resultObject = ResultObject.failure();

        try {
            QueryWrapper<Lover> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.lambda().orderByDesc(Lover::getCreateTime);

            if (!StringUtils.isEmpty(keyword)) {
                userQueryWrapper.lambda().like(Lover::getLoverName, keyword);
            }
//
//            if (userId != null) {
//                UserQueryWrapper.lambda().eq(User::getUserName, userId);
//            }
//
//            if (!StringUtils.isEmpty(operationType)) {
//                UserQueryWrapper.lambda().eq(User::getUserName, operationType);
//            }

//            if (createTime != null) {
//                LocalDateTime localMin = LocalDateTime.of(createTime.toLocalDate(), LocalTime.MIN);
//                LocalDateTime localMax = LocalDateTime.of(createTime.toLocalDate(), LocalTime.MAX);
//                UserQueryWrapper.lambda().between(User::getCreateTime, localMin, localMax);
//            }

            IPage iPage = new Page(pageNum, pageSize);
            IPage<Lover> userIPage = iLoverService.page(iPage, userQueryWrapper);
            resultObject = ResultObject.success(userIPage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;

    }

    @ApiOperation(value = "用户新增或者修改", notes = "用户新增或者修改")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = false)
    }
    )
    public ResultObject saveOrUpdate(@RequestBody Lover Lover) {

        ResultObject resultObject = ResultObject.failure();

        try {
            boolean b = iLoverService.saveOrUpdate(Lover);
            resultObject = ResultObject.success(b);

        } catch (Exception e) {
            resultObject.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return resultObject;

    }
    
    /**
     * 查询用户详情
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "查询用户", notes = "查询用户详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "id", value = "用户ID", required = false),
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = false)
    }
    )
    public ResultObject detail(@RequestParam(value = "id") Integer id) {

        ResultObject resultObject = ResultObject.failure();
        try {

            if (id != null) {
                Lover lover = iLoverService.getById(id);
                resultObject = ResultObject.success(lover);

            }else{
                resultObject.setMsg("参数不能为空");
            }

        } catch (Exception e) {
            resultObject.setMsg("查询失败");
            e.printStackTrace();
        }

        return resultObject;
    }
    
}
