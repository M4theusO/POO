package visualizacao;

import java.text.NumberFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import modelo.Loja;

public class EntradaSaida {

	public static int solicitaOpcao() {
		String[] opcoes = { "Cadastrar produtos", "Produtos cadastrados", "Entrada de produtos no estoque",
				"Vender produto", "Produtos em estoque", "Visualizar cupons gerados", "Valor total dos Cupons gerados",
				"Sair" };

		JComboBox<String> menu = new JComboBox<String>(opcoes);
		int confirmacao = JOptionPane.showConfirmDialog(null, menu, "Selecione a op??o desejada:",
				JOptionPane.OK_CANCEL_OPTION); // chama o JComboBox dentro de uma caixa com o texto
		if (confirmacao == 0) {
			return menu.getSelectedIndex();
		} else {
			return -1;
		}
	}

	public static int solicitaQtdeCadastrar() {
		int qtde = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe quantos produtos deseja cadastrar: ",
				"Cadastro", JOptionPane.INFORMATION_MESSAGE));
		return qtde;
	}

	public static String solicitaDescricao() {
		String descricao = JOptionPane.showInputDialog(null, "Informe a descri??o do produto: ", "Cadastro",
				JOptionPane.INFORMATION_MESSAGE);
		return descricao;
	}

	public static double solicitaPreco() {
		double valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do produto: "));
		while (valor <= 0) { // valida??o para o valor de qualquer produto ser maior q 0
			JOptionPane.showMessageDialog(null, "O valor deve ser maior que 'R$0,00'!", "Entrada no estoque: ",
					JOptionPane.INFORMATION_MESSAGE);
			valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do produto: "));
		}
		return valor;

	}

	public static void exibeListaCadastrados(String cadastrados) {
		JOptionPane.showMessageDialog(null, cadastrados, "Lista dos Produtos Cadastrados: ",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static int escolheCodigo(Loja loja) {
		int qtdeCadastros = loja.getListaDeCadastrados().size();
		String[] codigos = new String[qtdeCadastros];

		for (int i = 0; i < qtdeCadastros; i++) {
			codigos[i] = loja.getListaDeCadastrados().get(i).getCodigo() + ", "
					+ loja.getListaDeCadastrados().get(i).getDescricao();
		}

		JComboBox<String> menu = new JComboBox<String>(codigos);
		int confirmacao = JOptionPane.showConfirmDialog(null, menu, "Selecione o c?digo do produto:",
				JOptionPane.OK_CANCEL_OPTION); // chama o JComboBox dentro de uma caixa com o texto
		if (confirmacao == 0) {
			return menu.getSelectedIndex();
		} else {
			return -1;
		}
	}

	public static int solicitaQtde() {
		int quantidade = Integer
				.parseInt(JOptionPane.showInputDialog("Informe a quantidade que dar? entrada no estoque: "));
		while (quantidade <= 0) { // valida??o para qtde que dar? entrada no estoque ser maior q 0
			JOptionPane.showMessageDialog(null, "A quantidade deve ser maior que '0'!", "Entrada no estoque: ",
					JOptionPane.INFORMATION_MESSAGE);
			quantidade = Integer
					.parseInt(JOptionPane.showInputDialog("Informe a quantidade que dar? entrada no estoque: "));
		}
		return quantidade;
	}

	public static void exibeListaEstocados(String estocados) {
		JOptionPane.showMessageDialog(null, estocados, "Lista dos Produtos Estocados: ",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static int exibeMenuVender(Loja loja) {
		int qtdeProdutos = loja.getListaDeEstoque().size();
		String[] produtos = new String[qtdeProdutos];

		for (int i = 0; i < qtdeProdutos; i++) {
			produtos[i] = loja.getListaDeEstoque().get(i).getCodigo() + ", "
					+ loja.getListaDeEstoque().get(i).getDescricao();
		}
		String msg = "Escolha o produto que deseja vender:";
		JComboBox exibicaoProdutos = new JComboBox(produtos);
		int confirmacao = JOptionPane.showConfirmDialog(null, exibicaoProdutos, msg, JOptionPane.OK_CANCEL_OPTION);

		if (confirmacao == 0) {
			return exibicaoProdutos.getSelectedIndex();
		} else {
			return -1;
		}
	}

	public static String geraDataVenda() {
		Calendar data = Calendar.getInstance();
		int dia = data.get(Calendar.DAY_OF_MONTH);
		int mes = data.get(Calendar.MONTH) + 1;
		int ano = data.get(Calendar.YEAR);
		String dataFormatada = dia + "/" + mes + "/" + ano;
		return dataFormatada;
	}

	public static int solicitaQtdeVender() {
		int qtdeVendida = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade de produtos a serem vendidos: ",
						"Dados da Venda!", JOptionPane.INFORMATION_MESSAGE));
		return qtdeVendida;
	}

	public static void exibirCupom(String cupons) {
		JOptionPane.showMessageDialog(null, cupons, "Lista de Cupons: ", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void exibeTotalVendas(double totalVendas) {
		String valorFormatado = NumberFormat.getCurrencyInstance().format(totalVendas);
		JOptionPane.showMessageDialog(null, "O valor total dos cupons de todas as vendas ? " + valorFormatado,
				"Valor Total das Vendas: ", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void exibeMsgErro(String msgErro) {
		JOptionPane.showMessageDialog(null, msgErro, "AVISO!", JOptionPane.INFORMATION_MESSAGE);
	}

}
