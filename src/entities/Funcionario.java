package entities;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static DecimalFormat moneyFormatter = new DecimalFormat("###,###,###,###.00");
	private BigDecimal salario;
	private String funcao;

	public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		this.salario = salario;
		this.funcao = funcao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome: " + getNome() + " - ");
		sb.append("Data Nascimento: " + getDataNascimento().format(formatter) + " - ");
		sb.append("Salário: " + moneyFormatter.format(getSalario()) + " - ");
		sb.append("Função: " + getFuncao());
		return sb.toString();
	}
}
