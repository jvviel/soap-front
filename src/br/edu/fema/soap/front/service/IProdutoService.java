package br.edu.fema.soap.front.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.edu.fema.soap.front.model.Produto;
import br.edu.fema.soap.front.model.Produtos;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IProdutoService {

	@WebMethod void save(Produto produto);
	@WebMethod void update(Produto produto, Long id);
	@WebMethod void delete(Long id);
	@WebMethod Produtos findAll();
	@WebMethod Produto findById(Long id);
}
