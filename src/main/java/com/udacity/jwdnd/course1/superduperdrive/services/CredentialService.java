package com.udacity.jwdnd.course1.superduperdrive.services;

import com.udacity.jwdnd.course1.superduperdrive.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.superduperdrive.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    public void createCredential(Credential credential) {
        this.credentialMapper.insertCredential(credential);
    }

    public void updateCredential(Credential credential) {
        this.credentialMapper.updateCredential(credential);
    }

    public List<Credential> getCredentials(Long userId) {
        return this.credentialMapper.findCredentials(userId);
    }

    public void deleteCredential(Long credentialId) {
        this.credentialMapper.deleteCredential(credentialId);
    }
}
