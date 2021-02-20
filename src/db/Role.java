package db;

import db.entity.Users;

public enum Role {
    MANAGER, MASTER, CUSTOMER;

    public static Role getRole(Users users) {
        int roleId = users.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
