package com.notsruht.taf.security;

import com.microsoft.credentialstorage.model.StoredCredential;
import com.microsoft.credentialstorage.SecretStore;
import com.microsoft.credentialstorage.StorageProvider;
import com.microsoft.credentialstorage.StorageProvider.SecureOption;

/**
 * Windows Credential Manager
 */
public class WindowsCredentialManager implements CredentialManager {
	private SecretStore<StoredCredential> credentialStorage;

	@Override
	public Credential getCredential(String key) {
		credentialStorage = StorageProvider.getCredentialStorage(true, SecureOption.REQUIRED);

        if (credentialStorage == null) {
            throw new RuntimeException("No secure credential storage available.");
        }
        
        StoredCredential storedCredential = credentialStorage.get(key);
        if (storedCredential == null) {
        	throw new RuntimeException("Credentials for key " + key + " not found.");
        }
        
        Credential credential = new Credential();
        credential.setKey(key);
        credential.setUsername(storedCredential.getUsername());
        credential.setPassword(String.valueOf(storedCredential.getPassword()));
        
        return credential;        
	}

	@Override
	public CredentialManagerProvider getProvider() {
		return CredentialManagerProvider.WINDOWS;
	}
}
