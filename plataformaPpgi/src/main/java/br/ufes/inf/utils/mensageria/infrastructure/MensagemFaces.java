package br.ufes.inf.utils.mensageria.infrastructure;

import br.ufes.inf.utils.mensageria.Mensagem.Tipo;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.ufes.inf.utils.mensageria.Mensagem;
import br.ufes.inf.utils.mensageria.MensagemAPI;

public class MensagemFaces implements MensagemAPI{

    @Override
    public void exibirMensagem(Tipo tipo, String titutlo, String descricao) {
        FacesMessage message = new FacesMessage(mapeiaTipo(tipo), titutlo, descricao);
        FacesContext.getCurrentInstance().addMessage(null, message);        
    }
    
    private FacesMessage.Severity mapeiaTipo(Mensagem.Tipo tipo){
        switch(tipo) {
            
            case ERRO:
                return FacesMessage.SEVERITY_ERROR;
            case ALERTA:
                return FacesMessage.SEVERITY_WARN;            
            case SUCESSO:
            case INFORMACAO:
            default:
               return FacesMessage.SEVERITY_INFO;
           
        }
    }
}
