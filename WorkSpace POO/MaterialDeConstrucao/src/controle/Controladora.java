package controle;

import modelo.Loja;
import visualizacao.EntradaSaida;

public class Controladora {

	Loja loja = new Loja(); // REFATORAR **OK**
	// Cupom cupom = new Cupom(); //TIRAR INSTANCIA POIS VOU CHAMAR OS METODOS PELO
	// ESTOQUE **OK**
	// private ArrayList<Cupom> listaDeCupons = new ArrayList<Cupom>(); //DEIXAR AS
	// TRES LISTAS JUNTAS **OK**

	public void exibeMenu() {

		int opcao;
		String estocados = "";
		String cadastrados = "";
		int posicaoVenda = 0;

		do {
			opcao = EntradaSaida.solicitaOpcao();

			switch (opcao) {
			case 0:
				int qtde = EntradaSaida.solicitaQtdeCadastrar();
				for (int i = 0; i < qtde; i++) {
					String descricao = EntradaSaida.solicitaDescricao();
					double preco = EntradaSaida.solicitaPreco();
					loja.cadastrarProduto(descricao, preco);
				}
				break;
			case 1:

				if (loja.getListaDeCadastrados().size() == 0) {
					EntradaSaida.exibeMsgErro("Nenhum produto foi cadastrado!");// **OK**
				} else {
					cadastrados = loja.geraListaCadastrados();
					EntradaSaida.exibeListaCadastrados(cadastrados);
				}
				break;
			case 2:
				if (loja.getListaDeCadastrados().size() == 0) {
					EntradaSaida
							.exibeMsgErro("N?o ? poss?vel dar entrada no estoque, pois nenhum produto foi cadastrado!");
				} else {
					int entradaPosicao = EntradaSaida.escolheCodigo(loja);
					int qtdeEntraEstoque = EntradaSaida.solicitaQtde();
					loja.entrarNoEstoque(entradaPosicao, qtdeEntraEstoque);
				}
				break;
			case 3:
				if (loja.getListaDeEstoque().size() == 0) {
					EntradaSaida.exibeMsgErro(
							"Nenhum produto foi adicionado ao estoque para que seja poss?vel realizar uma venda!");
				} else {
					posicaoVenda = EntradaSaida.exibeMenuVender(loja);
					if (posicaoVenda != -1) {
						if (loja.getListaDeEstoque().get(posicaoVenda).getQuantidade() == 0) {
							EntradaSaida.exibeMsgErro("Esse produto est? esgotado!");// **OK**
						} else {
							int qtdeVender = EntradaSaida.solicitaQtdeVender();// VALIDAR PARA N PEGAR NUM NEGATIVOS
							int qtdeRestante = loja.getListaDeEstoque().get(posicaoVenda).getQuantidade() - qtdeVender; // **OK**
							while (qtdeVender <= 0 || qtdeRestante < 0) {
								EntradaSaida.exibeMsgErro(
										"A quantidade informada n?o deve ser negativa ou ultrapassar a quantidade dispon?vel em estoque");
								qtdeVender = EntradaSaida.solicitaQtdeVender();
								qtdeRestante = loja.getListaDeEstoque().get(posicaoVenda).getQuantidade() - qtdeVender;
							}
							String data = EntradaSaida.geraDataVenda();
							loja.recebeDadosCupom(posicaoVenda, qtdeVender, data);
							loja.vender(posicaoVenda, qtdeVender, qtdeRestante);							
						}
					}
				}
				break;
			case 4:	
				//loja.verificaEstoque();
				if (loja.getListaDeEstoque().size() == 0) { // FAZER A VALIDA??O PELO TAMANHO **OK**
					EntradaSaida.exibeMsgErro("Nenhum produto foi estocado!");// **OK**
				} else {				
					estocados = loja.geraListaEstocados();
					EntradaSaida.exibeListaEstocados(estocados); // SO EXIBIR A LISTA SE HOUVER PRODUTOS AINDA (criar
																	// metodo para verificar se tem algum produto na
																	// lista e retornar false se tiver, usar na valida??o)
				
				}
				break;
			case 5:
				if (loja.getListaDeCupons().size() == 0) { // CORRIGIDO
					EntradaSaida.exibeMsgErro("Nenhum cupom foi gerado!");// **OK**
				} else {
					String cupons = loja.gerarCupom();
					EntradaSaida.exibirCupom(cupons);
				}
				break;
			case 6:
				if (loja.getListaDeCupons().size() == 0) {
					EntradaSaida.exibeMsgErro("Nenhuma venda foi realizada!"); // **OK**
				} else {
					double totalVendas = loja.geraTotalVendas();
					EntradaSaida.exibeTotalVendas(totalVendas);
				}
				break;
			}

		} while (opcao != 7 && opcao != -1);
		// FECHAR PROGRAMA (SYSTEM) **OK**
	}

}
