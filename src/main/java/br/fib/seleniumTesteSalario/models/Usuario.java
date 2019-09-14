package br.fib.seleniumTesteSalario.models;

public class Usuario {

	private int id;
	private String nome;
	private String dependentes;
	private String horaTrabalhada;
	private String salarioHora;

	public Usuario(String nome, String dependentes, String d, String e) {
		this.nome = nome;
		this.dependentes = dependentes;
		this.horaTrabalhada = d;
		this.salarioHora = e;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDependentes() {
		return dependentes;
	}

	public void setDependentes(String dependentes) {
		this.dependentes = dependentes;
	}

	public String getHoraTrabalhada() {
		return horaTrabalhada;
	}

	public void setHoraTrabalhada(String horaTrabalhada) {
		this.horaTrabalhada = horaTrabalhada;
	}

	public String getSalarioHora() {
		return salarioHora;
	}

	public void setSalarioHora(String salarioHora) {
		this.salarioHora = salarioHora;
	}

}