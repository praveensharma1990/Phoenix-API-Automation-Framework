package com.api.utils;

import java.util.Map;

import io.github.jopenlibs.vault.Vault;
import io.github.jopenlibs.vault.VaultConfig;
import io.github.jopenlibs.vault.VaultException;
import io.github.jopenlibs.vault.VaultImpl;
import io.github.jopenlibs.vault.response.LogicalResponse;

public  class ValtDBConfig {
	private static VaultConfig vaultConfig;
	private static Vault vault;

	static {
		
			try {
				vaultConfig = new VaultConfig()
						.address(System.getenv("VAULT_SERVER"))
						.token(System.getenv("VAULT_TOKEN")).build();
			} catch (VaultException e) {
				System.err.print(e.getMessage());
			}		
		vault = new VaultImpl(vaultConfig);
	}	
	
	public static String getSecretes(String key) {
		LogicalResponse response = null;
		try {
		response =	vault.logical().read("/secret/pheonix/qa/db");
		
		} catch (VaultException e) {
		
			System.err.print(e.getMessage());
			return null;
		}
		Map<String, String>vaultMap = response.getData();
		return vaultMap.get(key);
	}
	}


