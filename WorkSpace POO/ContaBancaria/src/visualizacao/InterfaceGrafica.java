package visualizacao;

import java.text.NumberFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import modelo.Conta;

public class InterfaceGrafica {

	public static double solicitarInformacoesDeposito() {
		double infoDeposito = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor do depósito: "));
		while (infoDeposito <= 0) {
			JOptionPane.showMessageDialog(null, "O valor deve ser maior que R$0,00!");
			infoDeposito = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor do depósito: "));
		}
		return infoDeposito;
	}

	public static double solicitarInformacoesSaque() {
		double infoSaque = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor do saque: "));
		while (infoSaque <= 0) {
			JOptionPane.showMessageDialog(null, "O valor deve ser maior que R$0,00!");
			infoSaque = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor do saque: "));
		}
		return infoSaque;
	}

	public static void exibirSaldo(double saldo) { // APRIMORAR FORMA DE MOSTRAR VALORES FINANCEIROS
		String valorFormatado = NumberFormat.getCurrencyInstance().format(saldo);
		System.out.println(valorFormatado);
		JOptionPane.showMessageDialog(null, "Atualmente você possui um saldo de " + valorFormatado,
				"Saldo disponível em Conta", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void exibirDadosDaConta(Conta conta) {
		// int validaTipo = conta.getTipo();
		String valorFormatado = NumberFormat.getCurrencyInstance().format(conta.getSaldo());
		String tipo;
		if (conta.getTipo() == 1) {
			tipo = "Poupança";
		} else {
			tipo = "Corrente";
		}
		JOptionPane.showMessageDialog(null,
				"TITULAR: " + conta.getTitularDaConta() + "\nTIPO DE CONTA: " + tipo + "\nSALDO: " + valorFormatado,
				"Dados da Conta", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void exibirExtratoCompleto(String extrato) {
		JOptionPane.showMessageDialog(null, extrato, "Extrato Completo", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void exibirExtratoDeDepositos(String extratoDepositos) {
		JOptionPane.showMessageDialog(null, extratoDepositos, "Extrato Depósitos", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void exibirExtratoDeSaques(String extratoSaques) {
		JOptionPane.showMessageDialog(null, extratoSaques, "Extrato Saques", JOptionPane.INFORMATION_MESSAGE);
	}

	public static String solicitarNomeTitular() {
		String nomeTitular = JOptionPane.showInputDialog(null, "Informe o nome do titular: ", "Dados da Conta!",
				JOptionPane.INFORMATION_MESSAGE);
		return nomeTitular;
	}

	public static int solicitarTipoConta() {
		int tipoConta = Integer.parseInt(
				JOptionPane.showInputDialog(null, "Informe o tipo da conta (1-Conta Poupança ou 2-Conta Corrente): ",
						"Dados da Conta!", JOptionPane.INFORMATION_MESSAGE));
		return tipoConta;
	}

	public static int solicitaOpcao() {
		String[] opcoes = { "Depositar", "Sacar", "Consultar saldo", "Dados da conta", "Extrato Completo",
				"Extrato de Depósitos", "Extrato de Saques", "Sair" }; // passar comprar para vender (CORRIGIDO)

		JComboBox<String> menu = new JComboBox<String>(opcoes); // cria o objeto e recebe como parametro opcoes
		int confirmacao = JOptionPane.showConfirmDialog(null, menu, "Selecione a opção desejada",
				JOptionPane.OK_CANCEL_OPTION); // chama o JComboBox dentro de uma caixa com o texto
		if (confirmacao == 0) {
			return menu.getSelectedIndex();
		} else {
			return -1;
		}
	}

	public static String gerarData() {
		Calendar hoje = Calendar.getInstance();
		int dia = hoje.get(Calendar.DAY_OF_MONTH); // PEGAR A DATA JA FORMATADA DA CLASSE INTERFACEGRAFICA
		int mes = hoje.get(Calendar.MONTH) + 1; // para aparecer o mes certo pois no java os meses começam em 0(Janeiro)
		int ano = hoje.get(Calendar.YEAR);
		String data = dia + "/" + mes + "/" + ano;
		return data;
	}

}
