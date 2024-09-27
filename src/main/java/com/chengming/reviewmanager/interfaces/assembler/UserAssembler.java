package com.chengming.reviewmanager.interfaces.assembler;

import com.chengming.reviewmanager.domain.user.entity.User;
import com.chengming.reviewmanager.domain.user.enums.Role;
import com.chengming.reviewmanager.infrastructure.enums.BaseStatusEnum;
import com.chengming.reviewmanager.interfaces.dto.command.CodeReviewCmd;

public class UserAssembler {
    public static User codeReviewCmd2User(CodeReviewCmd cmd) {
        User user = new User();
        user.setStatus(BaseStatusEnum.NORMAL.getCode());
        user.setRole(Role.HOLD.getCode());
        user.setUsername(cmd.getHoldUserName());
        return user;
    }
}
