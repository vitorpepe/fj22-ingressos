package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sessao {
	@Id
	@GeneratedValue
	private Integer id;
	private LocalTime horario;
	
	@ManyToOne
	private Sala sala;
	
	@ManyToOne
	private Filme filme;
	
	@ManyToOne
	private Sessao sessao;
	
	private BigDecimal preco;
	
	
	/** 
	 * @deprecated hiberante only
	 */
	
	public Sessao(){
		
	}
	public Sessao (LocalTime horario, Filme filme, Sala sala){
		this.horario=horario;
		this.filme=filme;
		this.sala=sala;
		this.setPreco(sala.getPreco().add(filme.getPreco()));
		
		
	}
	private void setPreco(BigDecimal preco) {
		this.preco=preco;
	}
	public Sessao getSessao() {
		return sessao;
	}
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	

}
