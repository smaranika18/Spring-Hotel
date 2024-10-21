package com.backend.lakesidehotel.service;

import java.util.List;

import com.backend.lakesidehotel.model.Role;
import com.backend.lakesidehotel.model.User;

public interface RoleService {

	List<Role> getRoles();

	Role createRole(Role theRole);

	void deleteRole(Long roleId);

	Role findByName(String name);

	User removeUserFromRole(Long userId, Long roleId);

	User assignRoleToUser(Long userId, Long roleId);

	Role removeAllUsersFromRole(Long roleId);

}
