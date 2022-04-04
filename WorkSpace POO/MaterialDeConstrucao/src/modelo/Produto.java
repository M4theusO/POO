package modelo;

public class Produto {

	private static int sequencia=1;
	private int codigo;
	private String descricao;
	private double preco;
	private int quantidade;
	
	public int retornaSequencia() {//"RETORNA" **OK**
		return this.codigo=sequencia++;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int código) {
		this.codigo = código;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}	
}
