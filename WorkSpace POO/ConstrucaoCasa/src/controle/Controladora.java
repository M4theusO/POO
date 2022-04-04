package controle;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import visualizacao.EntradaSaida; //importar as classe para poder relaciona-las
import modelo.*;

public class Controladora {
	
	private Casa casa = null;
	
	public void exibeMenu() {
		
		int opcao;		
		do {
			opcao = EntradaSaida.solicitaOpcao();
			if(casa==null) {
				while(opcao == 1 || opcao == 2) {//validação para q os opções do menu so possam ser acessadas depois de construir a casa
					opcao = EntradaSaida.solicitaOpcao();
				}
			}	
			switch(opcao) {
				case 0:
					JOptionPane.showMessageDialog(null, "Construir casa");
					this.casa = new Casa();
					String descricao = EntradaSaida.solicitaDescricao("casa",0);
					String cor = EntradaSaida.solicitaCor();					
					
					int qtdePortas = EntradaSaida.solicitaQtdeAberturas("portas");
					while(qtdePortas<=0) {
						qtdePortas = EntradaSaida.solicitaQtdeAberturas("portas");
					}
					int qtdeJanelas = EntradaSaida.solicitaQtdeAberturas("janelas");
					while(qtdeJanelas<=0) {
						qtdeJanelas = EntradaSaida.solicitaQtdeAberturas("janelas");
					}
					
					ArrayList<Aberturas> listaDePortas = new ArrayList<Aberturas>();//adiciona à listaDePortas objetos tipo aberturas
					
					for (int i=0; i<qtdePortas; i++) {
						Porta porta = new Porta();
						porta.setDescricao(EntradaSaida.solicitaDescricao("porta",(i+1)));
						porta.setEstado(EntradaSaida.solicitaEstado("porta"));
						listaDePortas.add(porta); //adiciono o objeto porta (com os devidos atributos já setados) na listaDePortas
					}
					
					ArrayList<Aberturas> listaDeJanelas = new ArrayList<Aberturas>();
					
					for (int i=0; i<qtdeJanelas; i++) {
						Janela janela = new Janela();
						janela.setDescricao(EntradaSaida.solicitaDescricao("janela",(i+1)));
						janela.setEstado(EntradaSaida.solicitaEstado("janela"));
						listaDeJanelas.add(janela);//vai pra classe casa
					}					
					
					this.casa.constroiCasa(descricao, cor, listaDePortas, listaDeJanelas);
					System.out.println("Descrição da casa: "+ casa.getDescricao()+"\n");
					System.out.println("Cor da casa: "+ casa.getCor()+"\n");
					
					for(Aberturas porta : casa.getListaDePortas()) {//usa objeto que recebeu os dados : pelo método get, 
					//for each = para cada							//pega a lista que está na classe casa na ordem informada 
																    //anteriormente (descrição-estado)
						System.out.println("Descrição da porta: "+ porta.getDescricao()+"\n");
						System.out.println("Estado da porta: "+ porta.getEstado()+"\n");					
					}
					for(Aberturas janela : casa.getListaDeJanelas()) {
						System.out.println("Descrição da Janela: "+ janela.getDescricao()+"\n");
						System.out.println("Estado da janela: "+ janela.getEstado()+"\n");
					}
				break;
				case 1:
					JOptionPane.showMessageDialog(null, "Movimentar portas ou janelas");
					String tipoAbertura = EntradaSaida.solicitaTipoAbertura();
					
					ArrayList<Aberturas> listaDeAberturas = new ArrayList<Aberturas>();
					
					if(tipoAbertura.equals("porta")) {
						listaDeAberturas = this.casa.getListaDePortas();
					}else {
						listaDeAberturas = this.casa.getListaDeJanelas();
					}
					
					int posicao = EntradaSaida.solicitaAberturaMover(listaDeAberturas);
					int novoEstado = 0;
					
					if(posicao!=-1) {
						novoEstado = EntradaSaida.solicitaEstado(tipoAbertura);
						Aberturas abertura = this.casa.retornaAbertura(posicao, tipoAbertura);
						this.casa.moverAbertura(abertura, novoEstado);
						System.out.println("Novo estado da "+tipoAbertura+": "+abertura.getEstado());
					}else {
						EntradaSaida.exibeMsgAbertura();
					}					
				break;
				case 2:
					JOptionPane.showMessageDialog(null, "Ver informações da casa");
					String informacoes=this.casa.geraInfoCasa();
					EntradaSaida.exibeInfoCasa(informacoes);
				break;	
			}
				
		}while(opcao!=3);
		
		EntradaSaida.exibeMsgEncerraPrograma();
		System.exit(0);
		
	}
	
}
