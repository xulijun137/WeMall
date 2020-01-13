package com.xu.wemall.service.serviceImpl;

import com.xu.wemall.entry.Comment;
import com.xu.wemall.mapper.CommentMapper;
import com.xu.wemall.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RonnieXu
 * @since 2019-12-25
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
