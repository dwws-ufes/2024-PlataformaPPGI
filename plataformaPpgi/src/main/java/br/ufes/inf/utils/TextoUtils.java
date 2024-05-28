package br.ufes.inf.utils;

public class TextoUtils {
    
    public static String trataNomePessoa(String nome) {
        if(nome == null || nome.trim().equals("")) {
            return nome;
        }
        nome = nome.trim();
        nome = nome.toLowerCase();     
        
        String[] palavras = nome.split(" ");
        String resultado = "";
        
        
        for(String palavra: palavras) {      
            if(palavra.length() <= 1) {
                String espacoEmBrancoNoFinal = " ";  
                resultado = resultado.concat(palavra.toUpperCase().concat(espacoEmBrancoNoFinal));    
            }else {
                String primeiraLetra = palavra.substring(0,1);
                String restoDaPalavra = palavra.substring(1);
                String espacoEmBrancoNoFinal = " ";  
                resultado = resultado.concat(primeiraLetra.toUpperCase().concat(restoDaPalavra).concat(espacoEmBrancoNoFinal));
            }                        
        }
        //Remove espaços em branco no meio da palavra
        return resultado.trim().replaceAll(" +", " ");
    }
}