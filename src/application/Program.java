package application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Funcionario;


public class Program {

	public static void main(String[] args) {
		
		//3.1
		List<Funcionario> funcionarios = inserirFuncionarios();
		List<String> funcao = funcionarios.stream().distinct().map(func -> func.getFuncao()).toList();
		Map<String, List<Funcionario>> mapFuncionarios = new HashMap<>();
		
		System.out.println("** COM JOÃO **");
		funcionarios.stream().forEach(func -> System.out.println(func.toString()));
		
		separar();
		
		//3.2
		System.out.println("** REMOVE FUNCIONARIO **");	
		funcionarios.removeIf(func -> func.getNome().equals("João"));
		
		separar();
		
		//3.3
		System.out.println("** SEM JOÃO **");	
		funcionarios.stream().forEach(func -> System.out.println(func.toString()));
		
		//3.4
		funcionarios.stream().forEach(func -> {
			BigDecimal ajusteSalario = func.getSalario().multiply(new BigDecimal(0.1));
			
			func.setSalario(func.getSalario().add(ajusteSalario));
		});
		
		separar();
		
		//3.5
		funcao.stream().forEach(func -> mapFuncionarios.put(func, funcionarios.stream()
				.filter(x -> x.getFuncao().equals(func)).toList()));
		
		//3.6
		mapFuncionarios.forEach((funcaoFunc, funcionariosList) -> {
			System.out.println("Função: " + funcaoFunc.toUpperCase());
			funcionariosList.forEach(func -> System.out.println(func));
			System.out.println();
		});
		
		separar();
		
		//3.8
		funcionarios.stream().filter(func -> 
			(func.getDataNascimento().getMonthValue() == 10 || func.getDataNascimento().getMonthValue() == 12))
			.toList().forEach(funcs -> System.out.println("Aniversariante: "+ funcs.getNome()));
		
		separar();
		
		//3.9
		Funcionario funcMaisVelho = funcionarios.stream()
				.min(Comparator.comparing(Funcionario::getDataNascimento)).get();
		System.out.println("Mais velho: " 
		+ funcMaisVelho.getNome() 
		+ " Idade: " 
		+ Period.between(funcMaisVelho.getDataNascimento(), LocalDate.now()).getYears());
		
		separar();
		
		//3.10
		funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome))
			.toList().forEach(func -> System.out.println(func));
		
		separar();
		
		//3.11
		BigDecimal total = funcionarios.stream().map(func -> func.getSalario()).reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println("Total Salário: " + String.format("%.2f", total));
		
		separar();
		
		//3.12
		funcionarios.stream().sorted(Comparator.comparing(Funcionario::getSalario)).forEach(func -> {
			BigDecimal salarioMinimo = func.getSalario().divide(new BigDecimal(1212.0), 0,RoundingMode.DOWN);
			System.out.println(func.getNome() + " - Qtd. Salario Min: " + salarioMinimo);
		});

	}
	
	public static List<Funcionario> inserirFuncionarios() {
		List<Funcionario> func = new ArrayList<>();
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		func.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatterDate), new BigDecimal(2009.44), "Operador"));
		func.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatterDate), new BigDecimal(2284.38), "Operador"));
		func.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatterDate), new BigDecimal(9836.14), "Coordenador"));
		func.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatterDate), new BigDecimal(19199.88), "Diretor"));
		func.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatterDate), new BigDecimal(1582.72), "Recepcionista"));
		func.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatterDate), new BigDecimal(4071.84), "Operador"));
		func.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatterDate), new BigDecimal(4071.84), "Contador"));
		func.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatterDate), new BigDecimal(4071.84), "Gerente"));
		func.add(new Funcionario("Heloisa", LocalDate.parse("24/05/2003", formatterDate), new BigDecimal(4071.84), "Eletricista"));
		func.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatterDate), new BigDecimal(4071.84), "Gerente"));
		return func;
	}
	
	public static void separar() {
		System.out.println("-------------------------------------------------------");
	}
}
