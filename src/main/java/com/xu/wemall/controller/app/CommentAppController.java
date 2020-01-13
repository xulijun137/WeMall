package com.xu.wemall.controller.app;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.wemall.commons.ResultObject;
import com.xu.wemall.entry.Comment;
import com.xu.wemall.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xu.wemall.controller.common.BaseController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RonnieXu
 * @since 2019-12-25
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Api(value = "用户评论集合", tags = "用户评论集合")
@RestController
@RequestMapping("/comment")
public class CommentAppController extends BaseController {

    @Autowired
    private ICommentService iCommentService;

    /**
     * 评论分页列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "评论分页列表", notes = "获取评论分页列表")
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
            QueryWrapper<Comment> CommentQueryWrapper = new QueryWrapper<>();
            //CommentQueryWrapper.lambda().eq(Comment::getStatus,StatusEnum.ON_LINE.getValue());
            CommentQueryWrapper.lambda().orderByDesc(Comment::getCreateTime);

            if (!StringUtils.isEmpty(keyword)) {
                CommentQueryWrapper.lambda().like(Comment::getContens, keyword);
            }


            IPage iPage = new Page(pageNum, pageSize);
            IPage<Comment> CommentIPage = iCommentService.page(iPage, CommentQueryWrapper);
            resultObject = ResultObject.success(CommentIPage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;

    }

    @ApiOperation(value = "评论新增或者修改", notes = "评论新增或者修改")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = false)
    }
    )
    public ResultObject saveOrUpdate(Comment Comment) {

        ResultObject resultObject = ResultObject.failure();

        try {
            boolean b = iCommentService.saveOrUpdate(Comment);
            resultObject = ResultObject.success(b);

        } catch (Exception e) {
            resultObject.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return resultObject;

    }

    /**
     * 查询评论详情
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "查询评论", notes = "查询评论详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "id", value = "评论ID", required = false),
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = false)
    }
    )
    public ResultObject detail(@RequestParam(value = "id") Integer id) {

        ResultObject resultObject = ResultObject.failure();
        try {

            if (id != null) {
                Comment Comment = iCommentService.getById(id);
                resultObject = ResultObject.success(Comment);

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
