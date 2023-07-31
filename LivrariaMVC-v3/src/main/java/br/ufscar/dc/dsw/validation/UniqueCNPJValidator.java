package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Locacao;

@Component
public class UniqueCNPJValidator implements ConstraintValidator<UniqueCNPJ, String> {

	@Autowired
	private ILocadoraDAO dao;

	@Override
	public boolean isValid(String CNPJ, ConstraintValidatorContext context) {
		if (dao != null) {
			Locacao editora = dao.findByCNPJ(CNPJ);
			return editora == null;
		} else {
			// Durante a execução da classe LivrariaMvcApplication
			// não há injeção de dependência
			return true;
		}

	}
}