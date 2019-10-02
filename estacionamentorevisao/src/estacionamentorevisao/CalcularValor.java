package estacionamentorevisao;

public class CalcularValor {
	
	private final Acesso acesso;
	private int horaEntrada;
	private int minutosEntrada;
	private int horaSaida;
	private int minutosSaida;
	
	CalcularValor(Acesso source, int horaEntradaArg, int minutosEntradaArg, int horaSaidaArg, int minutosSaidaArg){
		acesso = source;
		horaEntrada = horaEntradaArg;
		minutosEntrada = minutosEntradaArg;
		horaSaida = horaSaidaArg;
		minutosSaida = minutosSaidaArg;
	}
	
	public float calculo() { 
		int quantidadeHoras = horaSaidaMenosEntrada(); 
		int quantidadeMinutos; 
		
		if (horaSaida == horaEntrada)
			quantidadeMinutos = minutosSaidaMenosEntrada();
		else if (horaSaidaMaiorEntrada() && minutosEntrada == minutosSaida){
			quantidadeMinutos = 0;
			quantidadeHoras = horaSaidaMenosEntrada();
		}
		else if (horaSaidaMaiorEntrada() && minutosEntradaMaiorSaida()) 
			quantidadeMinutos = minutosSaidaMenosEntrada();
		else if (horaSaidaMaiorEntrada() && minutosEntradaMaiorSaida()){
			quantidadeMinutos = minutosSaida + (60 - minutosEntrada);
			quantidadeHoras = horaSaidaMenosEntrada() - 1;
		}
		else {
			quantidadeHoras = 0;
			quantidadeMinutos = 0;
		}
		
		float valorTotal = 0, valorHorasTotal = 0, valorFracaoTotal = 0; 
		
		valorHorasTotal += quantidadeHoras * acesso.VALOR_HORA;
		valorFracaoTotal += Math.ceil(quantidadeMinutos / 15.0) * acesso.VALOR_FRACAO;		
		
		valorTotal = valorFracaoTotal += valorHorasTotal;
		
		if (quantidadeHoras >=9)
			return acesso.VALOR_DIARIA;
		else 
			return valorTotal;
	}

	private boolean minutosEntradaMaiorSaida() {
		return minutosEntrada > minutosSaida;
	}

	private boolean horaSaidaMaiorEntrada() {
		return horaSaida > horaEntrada;
	}

	private int minutosSaidaMenosEntrada() {
		return minutosSaida - minutosEntrada;
	}

	private int horaSaidaMenosEntrada() {
		return horaSaida - horaEntrada;
	}
	
}
