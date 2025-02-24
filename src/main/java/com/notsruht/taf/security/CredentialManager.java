package com.notsruht.taf.security;

/**
 * Place where login credentials are stored securely
 */
public interface CredentialManager {
	/**
	 * The Credential Manager Provider
	 * @return the CredentialManagerProvider
	 */
	public CredentialManagerProvider getProvider();
	
	/**
	 * Retrieves the credentials for the specified key
	 * @param key The key used to look up the credential
	 * @return the Credential
	 */
	public Credential getCredential(String key);
}
