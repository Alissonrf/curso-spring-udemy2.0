package com.alisson.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.alisson.cursomc.domain.Cliente;
import com.alisson.cursomc.domain.enums.TipoCliente;
import com.alisson.cursomc.dto.ClienteNewDTO;
import com.alisson.cursomc.repositories.ClienteRepository;
import com.alisson.cursomc.resources.exception.FieldMessage;
import com.alisson.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Invalido!"));
		}
		if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Invalido!"));
		}
		
		Cliente aux = repo.findByEmail(objDTO.getEmail());
		
		if(aux != null) {
			list.add(new FieldMessage("email", "E-mail ja existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}