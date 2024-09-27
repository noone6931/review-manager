package com.chengming.reviewmanager.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    PARTNER(1),
    HOLD(2),
    REVIEWER(4),
    RECORDER(8),
    ABSENT(16);

    private final int code;

    public static int combineRoles(Role... roles) {
        int result = 0;
        for (Role role : roles) {
            result |= role.getCode();
        }
        return result;
    }

    public static boolean hasRole(int combinedRoles, Role role) {
        return (combinedRoles & role.getCode()) != 0;
    }
}
