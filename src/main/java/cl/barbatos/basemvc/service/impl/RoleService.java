package cl.barbatos.basemvc.service.impl;

import cl.barbatos.basemvc.model.dto.RoleDTO;
import cl.barbatos.basemvc.model.entity.Role;
import cl.barbatos.basemvc.repository.RoleRepository;
import cl.barbatos.basemvc.service.IRoleService;
import cl.barbatos.basemvc.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return convertToPersonDTOs(roles);

    }

    private List<RoleDTO> convertToPersonDTOs(List<Role> roles) {
        return roles.stream()
                .map(role -> DtoConverter.convertToDto(role, RoleDTO.class))
                .collect(Collectors.toList());
    }
}
