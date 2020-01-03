package agh.dp.facade;

import agh.dp.database.PermissionRepository;
import agh.dp.database.RoleRepository;
import agh.dp.models.Permission;
import agh.dp.models.Role;
import agh.dp.models.RoleWithPermissions;

public class RoleWithPermissionsFacade {

    public static final RoleWithPermissionsFacade INSTANCE = new RoleWithPermissionsFacade();

    PermissionRepository permissionRepository;
    RoleRepository roleRepository;

    private RoleWithPermissionsFacade() {}

    public void setPermissionRepository(PermissionRepository permissionRepository){
        this.permissionRepository = permissionRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public void saveRoleWithPermissions(RoleWithPermissions roleWithPermissions){
        if (roleRepository == null || permissionRepository == null){
            //TODO: obsługa wyjątków
            return;
        }

        Role savedRole = roleRepository.save(roleWithPermissions.getRole());

        if (savedRole != null) {
            for (Permission permission : roleWithPermissions.getPermissions()) {
                permission.setRoleId(savedRole.getId());
                permissionRepository.save(permission);
            }
        }
    }
}