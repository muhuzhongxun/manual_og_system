package ltd.muhuzhongxun.web.service.impl;

import ltd.muhuzhongxun.web.entity.Message;
import ltd.muhuzhongxun.web.mapper.MessageMapper;
import ltd.muhuzhongxun.web.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-03-13
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
