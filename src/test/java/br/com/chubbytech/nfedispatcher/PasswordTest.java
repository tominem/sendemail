package br.com.chubbytech.nfedispatcher;

import org.junit.Assert;
import org.junit.Test;

import br.com.chubbytech.nfedispatcher.service.EncryptDecrypt;

public class PasswordTest {
	
	@Test
	public void encryptDecryptTest() throws Exception {
		
		EncryptDecrypt password = new EncryptDecrypt();
		
		String encrypt = password.encrypt("teste");
		
		String decrypt = password.decrypt(encrypt);
		
		Assert.assertEquals(decrypt, "teste");
		
		//teste
		
	}
	
}
