package modelo;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import visualizacao.EntradaSaida;

public class Loja {

	private int codigo;
	private ArrayList<Produto> listaDeCadastrados = new ArrayList<Produto>();
	private ArrayList<Produto> listaDeEstoque = new ArrayList<Produto>();
	private ArrayList<Cupom> listaDeCupons = new ArrayList<Cupom>();

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ArrayList<Produto> getListaDeCadastrados() {
		return listaDeCadastrados;
	}

	public void setListaDeCadastrados(ArrayList<Produto> listaDeCadastrados) {
		this.listaDeCadastrados = listaDeCadastrados;
	}

	public ArrayList<Produto> getListaDeEstoque() {
		return listaDeEstoque;
	}

	public void setListaDeEstoque(ArrayList<Produto> listaDeEstoque) {
		this.listaDeEstoque = listaDeEstoque;
	}

	public ArrayList<Cupom> getListaDeCupons() {
		return listaDeCupons;
	}

	public void setListaDeCupons(ArrayList<Cupom> listaDeCupons) {
		this.listaDeCupons = listaDeCupons;
	}

	public void cadastrarProduto(String descricao, double preco) {
		// for (int i = 0; i < qtde; i++) { //pedir as informa��es de acordo com a qtde
		// de produtos //FOR NA CONTROLADORA PARA O METODO
		Produto produto = new Produto();
		produto.setCodigo(produto.retornaSequencia());
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		this.listaDeCadastrados.add(produto); // SEMPRE CADASTRAR UM S� PRODUTO
		// }
	}

	public String geraListaCadastrados() {
		String cadastrados = "";
		for (Produto produto : listaDeCadastrados) {
			String valorFormatado = NumberFormat.getCurrencyInstance().format(produto.getPreco());
			cadastrados += "C�digo: " + produto.getCodigo() + "\nDescri��o: " + produto.getDescricao() + "\nPre�o: "
					+ valorFormatado + "\n\n";
		}
		return cadastrados;
	}

	public void entrarNoEstoque(int entradaPosicao, int qtdeEntraEstoque) {
		boolean validacao = true;
		for (Produto produto : listaDeCadastrados) {
			if (produto.getCodigo() == entradaPosicao + 1) {
				for (int i = 0; i < listaDeEstoque.size(); i++) {
					if (listaDeEstoque.get(i).getCodigo() == entradaPosicao + 1) {// se o codigo ja estiver cadastrado
						System.out.println("Passou");
						produto.setQuantidade(produto.getQuantidade() + qtdeEntraEstoque);
						validacao = false;
					}
				}
				if (validacao == true) { // se o codigo n estiver cadastrado
					produto.setQuantidade(qtdeEntraEstoque);// no produto, para gerar a lista de estocados
					this.listaDeEstoque.add(produto);
				}
			}
		}
	}

	public String geraListaEstocados() {
		String estocados = "";
		for (Produto produto : listaDeEstoque) {
			String valorFormatado = NumberFormat.getCurrencyInstance().format(produto.getPreco());
			estocados += "C�digo: " + produto.getCodigo() + "\nDescri��o: " + produto.getDescricao() + "\nPre�o: "
					+ valorFormatado + "\nQuantidade: " + produto.getQuantidade() + "\n\n";
		}
		return estocados;
	}

	public boolean verificaEstoque() {
		boolean valida = false;
		// Produto produto = new Produto();
		for (Produto produto : listaDeEstoque) {
			if (produto.getQuantidade() > 0) {
				valida = true;
			} else {
				this.listaDeEstoque.remove(produto); // remove o produto pelo indice "i"
			}
		}
		return valida;
	}

	public void vender(int posicaoVenda, int qtdeVender, int qtdeRestante) {// TIRAR ARRAYLIST **OK**
		if (qtdeRestante < 1) {
			listaDeEstoque.remove(posicaoVenda);
		} else {
			this.listaDeEstoque.get(posicaoVenda).setQuantidade(qtdeRestante);
		}
	}

	public void recebeDadosCupom(int posicaoVenda, int qtdeVender, String data) {
		Cupom cupom = new Cupom();
		cupom.setQtdeVendida(qtdeVender);
		cupom.setDataVenda(data);
		cupom.setDescricao(listaDeEstoque.get(posicaoVenda).getDescricao());
		cupom.setValorTotal(cupom.getQtdeVendida() * listaDeEstoque.get(posicaoVenda).getPreco());
		listaDeCupons.add(cupom);
	}

	public String gerarCupom() { // (CORRIGIDO) PASSAR OS METODOS DE CUPOM PARA CLASSE ESTOQUE JUNTO COM A LISTA

		String dados = "";
		for (Cupom cupom : listaDeCupons) {
			String valorFormatado = NumberFormat.getCurrencyInstance().format(cupom.getValorTotal());
			dados += "DATA: " + cupom.getDataVenda() + "\nDESCRI��O: " + cupom.getDescricao() + "\nQUANTIDADE VENDIDA: "
					+ cupom.getQtdeVendida() + "\nVALOR TOTAL: " + valorFormatado + "\n\n";

		}
		return dados;
	}

	public double geraTotalVendas() {
		double totalVendas = 0;
		for (Cupom cupom : listaDeCupons) {
			totalVendas += cupom.getValorTotal();
		}
		return totalVendas;
	}

}
