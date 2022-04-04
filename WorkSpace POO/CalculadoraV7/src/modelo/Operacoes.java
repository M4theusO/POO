package modelo;

public abstract class Operacoes implements IOperacao{ //pq deu certo se n tem o metodo calcula()?
	
	protected double num1; //modificador protected para poder compartilhar com outras classes do mesmo pacote ou por herança
	protected double num2;
	
	public double getNum1() {
		return this.num1;
	}
	
	public void setNum1(double num1) {
		this.num1=num1;
	}
	
	public double getNum2() {
		return this.num2;
	}
	
	public void setNum2(double num2) {
		this.num2=num2;
	}


}
