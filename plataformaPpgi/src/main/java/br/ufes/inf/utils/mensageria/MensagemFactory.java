package br.ufes.inf.utils.mensageria;

import br.ufes.inf.utils.mensageria.infrastructure.MensagemFaces;

public class MensagemFactory {      
    
    private static MensagemFactory instancia;
    
    private MensagemFactory() {}
    
    public static MensagemFactory obtemInstancia() {
        if(instancia==null) {
            instancia = new MensagemFactory();
        }
        return instancia;
    }
    
    public MensagemAPI obtemAPI() {
        return new MensagemFaces();
    }
}
