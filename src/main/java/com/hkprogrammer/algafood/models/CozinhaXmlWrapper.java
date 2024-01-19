package com.hkprogrammer.algafood.models;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NonNull;

@Data
@XmlRootElement
public class CozinhaXmlWrapper {

	@NonNull
	private List<Cozinha> cozinhas;
	
}
