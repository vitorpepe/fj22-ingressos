package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import junit.framework.Assert;

public class GerenciadorDeSessaoTest {
private Filme rogueOne;
private Sala sala3d;
private Sessao sessaoDasDez;
private Sessao sessaoDasTreze;
private Sessao sessaoDasDezoito;

@Before(value = "")
public void preparaSessoes(){
	this.rogueOne= new Filme("Rogue One" , Duration.ofMinutes(120), "SCI-FI", BigDecimal.ONE);
	this.sala3d = new Sala("Sala 3D", BigDecimal.TEN);
	
	this.sessaoDasDez= new Sessao (LocalTime.parse("10:00:00"), rogueOne, sala3d);
	this.sessaoDasTreze=new Sessao (LocalTime.parse("13:00:00"), rogueOne, sala3d);
	this.sessaoDasDezoito=new Sessao (LocalTime.parse("18:00:00"), rogueOne, sala3d);
}
@Test
public void garanteQueNaoDevePermitirSessaoNoMesmoHorario(){
	List<Sessao> sessoes =Arrays.asList(sessaoDasDez);
	GerenciadorDeSessao gerenciador = new GerenciadorDeSessao (sessoes);
	Assert.assertFalse(gerenciador.cabe(sessaoDasDez));
}
@Test	
public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoMesmoHorarioDeUmaSessaoJaExistente(){
	List<Sessao> sessoes =Arrays.asList(sessaoDasDez);
	Sessao sessao=new Sessao(sessaoDasDez.getHorario().minusHours(1),rogueOne,sala3d);
	GerenciadorDeSessao gerenciador = new GerenciadorDeSessao (sessoes);
	Assert.assertFalse(gerenciador.cabe(sessao));
	
}
@Test
public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoMesmoHorarioDeUmaSessaoJaExistente(){
	List<Sessao> sessoesDaSala =Arrays.asList(sessaoDasDez);
	GerenciadorDeSessao gerenciador=new GerenciadorDeSessao(sessoesDaSala);
	Sessao sessao = new Sessao (sessaoDasDez.getHorario().plusHours(1), rogueOne, sala3d);
	Assert.assertFalse(gerenciador.cabe(sessao));
}
public void garanteQueNaoDevePermitirUmaInsercaoEntreDoisFilmes(){
	List<Sessao> sessoes =Arrays.asList(sessaoDasDez, sessaoDasDezoito);
	GerenciadorDeSessao gerenciador = new GerenciadorDeSessao (sessoes);
	Assert.assertTrue(gerenciador.cabe(sessaoDasTreze));
}
}
