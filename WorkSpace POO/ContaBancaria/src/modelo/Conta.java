package modelo;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import visualizacao.InterfaceGrafica;

public class Conta {

	private String titularDaConta;
	private int tipo; // 1-conta poupança, 2-conta corrente.
	private double saldo;
	private ArrayList<Movimentacao> listaDeMovimentacao = new ArrayList<Movimentacao>();

	public String getTitularDaConta() {
		return titularDaConta;
	}

	public void setTitularDaConta(String titularDaConta) {
		this.titularDaConta = titularDaConta;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public ArrayList<Movimentacao> getListaDeMovimentacao() {
		return listaDeMovimentacao;
	}

	public void setListaDeMovimentacao(ArrayList<Movimentacao> listaDeMovimentacao) {
		this.listaDeMovimentacao = listaDeMovimentacao;
	}

	public void depositar() {
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setTipo(2);
		movimentacao.setValor(InterfaceGrafica.solicitarInformacoesDeposito());
		this.saldo += movimentacao.getValor();
		movimentacao.setData(InterfaceGrafica.gerarData());// CORRIGIDO
		this.listaDeMovimentacao.add(movimentacao);
	}

	public void sacar() {
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setTipo(1);
		movimentacao.setValor(InterfaceGrafica.solicitarInformacoesSaque());
		this.saldo = saldo - movimentacao.getValor();
		if (this.saldo < -1000) {
			JOptionPane.showMessageDialog(null,
					"Seu saldo já atingiu ou irá atingir o limite de R$1000,00 negativos com este saque!");
			this.saldo = saldo + movimentacao.getValor(); // para devolver o valor original ao atributo
		} else {
			movimentacao.setData(InterfaceGrafica.gerarData());
			this.listaDeMovimentacao.add(movimentacao);
		} // CORREÇÃO IF ELSE, SE -1000 NAO CONTINUAR RECEBENDO DATA E VALORES
			// CORRIGIDO//
	}

	public double consultarSaldo() {
		double saldo = getSaldo();
		return saldo;
	}

	public String gerarExtrato() {
		String extrato = "";
		String tipo = "";
		for (Movimentacao movimentacao : listaDeMovimentacao) {
			String valorFormatado = NumberFormat.getCurrencyInstance().format(movimentacao.getValor());
			if (movimentacao.getTipo() == 1) {
				tipo = "Saque";
			} else {
				tipo = "Depósito";
			}
			extrato += "Tipo da operação: " + tipo + "\nValor movimentado: " + valorFormatado
					+ "\nData da movimentação: " + movimentacao.getData() + "\n\n";
		}
		// extrato += "\nSaldo final disponível: " + this.getSaldo();
		return extrato;// FAZER VALIDAÇÃO PARA VER SE VARIAVEL RETORNA VAZIA PARA USAR COMO VALIDAÇÃO
						// NA CONTROLADORA
	}

	public String gerarExtratoDepositos() {
		String extrato = "";
		for (Movimentacao movimentacao : listaDeMovimentacao) {
			String valorFormatado = NumberFormat.getCurrencyInstance().format(movimentacao.getValor());
			if (movimentacao.getTipo() == 2) {
				extrato += "Tipo da operação: Depósito" + "\nValor movimentado: " + valorFormatado
						+ "\nData da movimentação: " + movimentacao.getData() + "\n\n";
			}
		}
		return extrato;
	}

	public String gerarExtratoSaques() {
		// String valorFormatado =
		// NumberFormat.getCurrencyInstance().format(movimentacao.getValor());
		String extrato = "";
		for (Movimentacao movimentacao : listaDeMovimentacao) {
			String valorFormatado = NumberFormat.getCurrencyInstance().format(movimentacao.getValor());
			if (movimentacao.getTipo() == 1) {
				extrato += "Tipo da operação: Saque" + "\nValor movimentado: " + valorFormatado
						+ "\nData da movimentação: " + movimentacao.getData() + "\n\n";
			}
		}
		return extrato;
	}
}
