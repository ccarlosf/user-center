package com.ccarlos.usercenter.rocketmq;

import com.ccarlos.usercenter.dao.bonus.BonusEventLogMapper;
import com.ccarlos.usercenter.dao.user.UserMapper;
import com.ccarlos.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.ccarlos.usercenter.domain.entity.bonus.BonusEventLog;
import com.ccarlos.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBonusMsgDTO message) {
        // 1. 为用户增加积分
        Integer userId = message.getUserId();
        Integer bonus = message.getBonus();
        User user = this.userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + bonus);
        this.userMapper.updateByPrimaryKeySelective(user);

        // 2. 记录增加积分日志
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .event("CONTRIBUTE")
                        .createTime(new Date())
                        .description("投稿加积分..")
                        .build()
        );

        log.info("积分添加完毕...");
    }
}
