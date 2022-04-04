package visualizacao;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import modelo.Fabrica;

public class EntradaSaida {

	public static int solicitaOpcao() {
		String[] opcoes = { "Fabricar carro", "Vender carro","Ver estoque", "Sair do programa" }; //passar comprar para vender (CORRIGIDO)

		JComboBox<String> menu = new JComboBox<String>(opcoes); // cria o objeto e recebe como parametro opcoes
		int confirmacao = JOptionPane.showConfirmDialog(null, menu, "Selecione a opção desejada",
				JOptionPane.OK_CANCEL_OPTION); // chama o JComboBox dentro de uma caixa com o texto
		if (confirmacao == 0) {
			return menu.getSelectedIndex();
		} else {
			return -1;
		}
	}

	public static int solicitaQtdeCarros() {
		int qtdeCarros = Integer.parseInt(JOptionPane.showInputDialog("Informe quantos carros desejas fabricar: "));
		return qtdeCarros;
	}

	public static String solicitaModelo() {
		return JOptionPane.showInputDialog("Informe o modelo:");
	}

	public static String solicitaCor() {
		return JOptionPane.showInputDialog("Informe a cor:");
	}

	public static int solicitaAno() {
		return Integer.parseInt(JOptionPane.showInputDialog("Informe o ano do carro:"));
	}

	public static void exibeInfo(String informacoes) {
		JOptionPane.showMessageDialog(null, informacoes, "Estoque de carros: ", JOptionPane.INFORMATION_MESSAGE);
	}

	public static int solicitaModeloVenda(Fabrica fabrica) {
		int qtdeCarros = fabrica.getListaDeCarros().size();
		String[] carros = new String[qtdeCarros];

		for (int i = 0; i < qtdeCarros; i++) { // mostrar os carros de acordo com a fabricação
			carros[i] = fabrica.getListaDeCarros().get(i).getModelo() + ", "
					+ fabrica.getListaDeCarros().get(i).getCor() + ", " + fabrica.getListaDeCarros().get(i).getAno();
		}
		String msg = "Escolha o carro que deseja comprar";
		JComboBox exibicaoEstoque = new JComboBox(carros);
		int confirmacao = JOptionPane.showConfirmDialog(null, exibicaoEstoque, msg, JOptionPane.OK_CANCEL_OPTION);

		if (confirmacao == 0) {
			return exibicaoEstoque.getSelectedIndex();
		} else {
			return -1;
		}
	}

	public static void exibeMsgVenda() {
		JOptionPane.showMessageDialog(null,"Nenhuma venda foi realizada!");
	}

	public static void exibeMsgEstoque() {
		JOptionPane.showMessageDialog(null,"Nenhum carro disponível no estoque!");
	}

}
