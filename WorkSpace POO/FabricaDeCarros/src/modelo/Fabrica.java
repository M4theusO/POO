package modelo;

import java.util.ArrayList;

import visualizacao.EntradaSaida;

public class Fabrica {

	private ArrayList<Carro> listaDeCarros = new ArrayList<Carro>();

	public ArrayList<Carro> getListaDeCarros() {
		return listaDeCarros;
	}

	public ArrayList<Carro> setListaDeCarros(ArrayList<Carro> listaDeCarros) {
		return this.listaDeCarros = listaDeCarros;
	}

	public String geraInfo() {
		String informacoes = "";
		for (Carro carro : listaDeCarros) {
			informacoes += "Modelo: " + carro.getModelo() + "\nCor: " + carro.getCor() + "\nAno: " + carro.getAno()+ "\n\n";
		}
		return informacoes;
	}

	public void venderCarro(int posicao) {
		this.listaDeCarros.remove(posicao);
	}

	public void fabricarCarro(int qtdeCarros) {

		for (int i = 0; i < qtdeCarros; i++) {
			Carro carro = new Carro(); // (CORRIGIDO)
			carro.setModelo(EntradaSaida.solicitaModelo());
			carro.setCor(EntradaSaida.solicitaCor());
			carro.setAno(EntradaSaida.solicitaAno());
			this.listaDeCarros.add(carro);
		}
	}
}
