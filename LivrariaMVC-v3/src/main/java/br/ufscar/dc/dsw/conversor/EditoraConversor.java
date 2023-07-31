package br.ufscar.dc.dsw.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Component
public class EditoraConversor implements Converter<String, Locacao>{

	@Autowired
	private ILocadoraService service;
	
	@Override
	public Locacao convert(String text) {
		
		if (text.isEmpty()) {
		 return null;	
		}
		
		Long id = Long.valueOf(text);	
		return service.buscarPorId(id);
	}
}
