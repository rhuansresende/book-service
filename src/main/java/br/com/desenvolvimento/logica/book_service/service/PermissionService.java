package br.com.desenvolvimento.logica.book_service.service;

import br.com.desenvolvimento.logica.book_service.model.Permission;
import br.com.desenvolvimento.logica.book_service.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findByDescription(String description){
        return permissionRepository.findPermissionByDescription(description);
    }
}
