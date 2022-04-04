package controle;

import modelo.Fabrica;
import visualizacao.EntradaSaida;

public class Controladora {

	private Fabrica fabrica = new Fabrica();// instanciar aqui (CORRIGIDO)

	public void exibeMenu() {

		int opcao;// criar opcao ver estoque no switch (CORRIGIDO)

		do {
			opcao = EntradaSaida.solicitaOpcao();

			if (opcao == -1) {
				System.exit(0);
			}
			switch (opcao) {
			case 0:
				// pedir a qtde de carros aqui(CORRIGIDO)
				int qtdeCarros = EntradaSaida.solicitaQtdeCarros();

				fabrica.fabricarCarro(qtdeCarros);

				String informacoes = this.fabrica.geraInfo();
				EntradaSaida.exibeInfo(informacoes);
				break;
			case 1:
				if (fabrica.getListaDeCarros().size() == 0) { // Validar se tem carros no estoque para então nem passar
																// pela solicitação do modelo requerido
					EntradaSaida.exibeMsgEstoque();
				} else {
					informacoes = this.fabrica.geraInfo();
					EntradaSaida.exibeInfo(informacoes);
					int posicao = EntradaSaida.solicitaModeloVenda(fabrica);
					if (posicao != -1) {
						fabrica.venderCarro(posicao);
					} else {
						EntradaSaida.exibeMsgVenda();
					}
				}
				break;
			case 2:
				if (fabrica.getListaDeCarros().size() == 0) { // Validar se tem carros no estoque para então nem passar
																// pela solicitação do modelo requerido
					EntradaSaida.exibeMsgEstoque();
				} else {
					informacoes = this.fabrica.geraInfo();
					EntradaSaida.exibeInfo(informacoes);
				}
				break;
			}
		} while (opcao != 3);

	}

}
