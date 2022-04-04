
public class Soma implements IOperacao{ //para implementar a interface criada, ou seja nada diferente da interface será aceito
	
	private double num1; //atributo
	private double num2;
	
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
	
	public double calcula() { //metodos dinamicos

		double soma = num1 + num2;
		return soma;
	}

}
