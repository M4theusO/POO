package controle;

import java.text.NumberFormat;

import javax.swing.JOptionPane;

import modelo.Conta;
import visualizacao.InterfaceGrafica;

public class Controladora {

	private Conta conta = new Conta();

	public void exibirMenu() {

		conta.setTitularDaConta(InterfaceGrafica.solicitarNomeTitular());
		conta.setTipo(InterfaceGrafica.solicitarTipoConta()); // validação para usuário informar apenas 1-Poupança ou
																// 2-Corrente
		while (conta.getTipo() != 1 && conta.getTipo() != 2) {
			conta.setTipo(InterfaceGrafica.solicitarTipoConta());
		}
		int opcao;

		do {
			opcao = InterfaceGrafica.solicitaOpcao();
			if (opcao == -1) {
				System.exit(0);
			}
			switch (opcao) {
			case 0:
				conta.depositar(); // para poder usar como verificação para mostrar o extrato solicitado
				// conta.depositar(); // realizar o depósito
				System.out.println(conta.getSaldo());
				break;
			case 1:
				conta.sacar(); // para poder usar como verificação para mostrar o extrato solicitado
				// conta.sacar(); // realizar o saque
				System.out.println(conta.getSaldo());
				break;
			case 2:
				double saldo = conta.consultarSaldo();
				InterfaceGrafica.exibirSaldo(saldo);
				break;
			case 3:
				InterfaceGrafica.exibirDadosDaConta(conta);
				break;
			case 4:
				String extrato = conta.gerarExtrato();
				if (extrato != "") {
					String valorFormatado = NumberFormat.getCurrencyInstance().format(conta.getSaldo());
					extrato += "\nSaldo final disponível: " + valorFormatado;
					InterfaceGrafica.exibirExtratoCompleto(extrato);
				} else {
					JOptionPane.showMessageDialog(null, "Não houve nenhuma movimentação!");
				}
				break;
			case 5:
				String extratoDepositos = conta.gerarExtratoDepositos(); // verificação para mostrar o extrato de
				if (extratoDepositos != "") { // depositos
					InterfaceGrafica.exibirExtratoDeDepositos(extratoDepositos);
				} else {
					JOptionPane.showMessageDialog(null, "Não houve nenhum depósito!");
				}
				break;
			case 6:
				String extratoSaques = conta.gerarExtratoSaques(); // verificação para mostrar o extrato de saques
				if (extratoSaques != "") {
					InterfaceGrafica.exibirExtratoDeSaques(extratoSaques);
				} else {
					JOptionPane.showMessageDialog(null, "Não houve nenhum saque!");
				}
				break;
			}
		} while (opcao != 7);

	}
}
