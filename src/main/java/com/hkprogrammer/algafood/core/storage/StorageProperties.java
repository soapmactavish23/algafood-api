package com.hkprogrammer.algafood.core.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {
	
	private Local local = new Local();
	private S3 s3 = new S3();
	
	@Getter
	@Setter
	public class Local {
		private String diretorioFoto;
	}
	
	@Getter
	@Setter
	public class S3 {
		private String idChaveAcesso;
		private String chaveAcessoSecreta;
		private String bucket;
		private Regions regiao;
		private String diretorioFotos;
	}
	
	

}
