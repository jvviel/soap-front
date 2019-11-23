package br.edu.fema.soap.front.bean;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.edu.fema.soap.front.model.Produto;
import br.edu.fema.soap.front.model.Produtos;
import br.edu.fema.soap.front.service.IProdutoService;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private Produto produto;

	private IProdutoService prod;
	private Long id;

	public Produto getProduto() {
		return produto;
	}

	public void novo() {

		produto = new Produto();
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@PostConstruct
	public List<Produto> listarProdutos() throws MalformedURLException {

		this.produto = new Produto();

		if (prod == null) {
			URL url = new URL("http://127.0.0.1:9876/prod?wsdl");
			QName qname = new QName("http://service.soap.fema.edu.br/", "ProdutoServiceService");
			Service ws = Service.create(url, qname);
			prod = ws.getPort(IProdutoService.class);
		}

		Produtos produtos = this.prod.findAll();

		return produtos.getProdutos();

	}

	public void salvarProduto() throws MalformedURLException {

		if (prod != null) {
			
			if(this.id != null) {
				prod.update(this.produto, this.id);
			} else {
				prod.save(produto);
			}

			produto = new Produto();
			listarProdutos();
			this.id = null;
		}

	}

	public void cancelar() throws MalformedURLException {
		this.produto = new Produto();
		listarProdutos();
	}

	public void excluirProduto(Produto produto) throws MalformedURLException {

		prod.delete(produto.getId());

		listarProdutos();
	}

	public void selecionaProduto(Produto produto) {
		this.id = produto.getId();
		this.produto = produto;
	}

}
