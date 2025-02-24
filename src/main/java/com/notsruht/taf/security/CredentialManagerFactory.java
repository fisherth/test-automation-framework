package com.notsruht.taf.security;

import com.notsruht.taf.util.TAFProperties;

/**
 * Factory class for obtaining a Credential Manager.
 */
public class CredentialManagerFactory {
	private static final String CREDENTIAL_MANAGER_TYPE = "testautomationframework.credentialManager.provider";
	
	/**
	 * Retrieves an implementation of CredentialManager specified by the <b>testautomationframework.credentialManager.provider</b> 
	 * property in the <b>testautomationframework</b> properties.
	 * 
	 * @return The CredentialManager
	 */
	public static CredentialManager getCredentialManager() {
		String type = TAFProperties.getInstance().getProperty(CREDENTIAL_MANAGER_TYPE);
		CredentialManagerProvider credentialManagerType = null;
		if(type != null && !type.isBlank()) {
			credentialManagerType = CredentialManagerProvider.valueOf(type.trim().toUpperCase());
		}
		
		return getCredentialManager(credentialManagerType);
	}
	
	/**
	 * Retrieves an implementation of CredentialManager
	 * @param provider The provider
	 * @return The CredentialManager
	 */
	public static CredentialManager getCredentialManager(CredentialManagerProvider provider) {
		if(provider == null) {
			return new WindowsCredentialManager();
		}
		
		switch(provider) {
		case WINDOWS:
			return new WindowsCredentialManager();
		default:
			return new WindowsCredentialManager();
		}
	}	
}