package br.ufes.inf.ppgi.plataformaPpgi.service;

import org.springframework.stereotype.Service;

import br.ufes.inf.utils.mensageria.MensagemAPI;
import br.ufes.inf.utils.mensageria.MensagemFactory;
import br.ufes.inf.utils.mensageria.Mensagem;

@Service
public class MensagemService {
    
    private MensagemAPI mensagemAPI = MensagemFactory.obtemInstancia().obtemAPI();    
 	
	public void Erro(String titulo, String conteudo) {	    
	    mensagemAPI.exibirMensagem(Mensagem.Tipo.ERRO, titulo, conteudo);
	}
	
	public void FatalErro(String titulo, String conteudo) {
	    mensagemAPI.exibirMensagem(Mensagem.Tipo.ERRO, titulo, conteudo);     
	}
	
	public void Informacao(String titulo, String conteudo) {
	    mensagemAPI.exibirMensagem(Mensagem.Tipo.INFORMACAO, titulo, conteudo);     
	}
	
	public void Advertencia(String titulo, String conteudo) {
	    mensagemAPI.exibirMensagem(Mensagem.Tipo.ALERTA, titulo, conteudo);     
	}
}
