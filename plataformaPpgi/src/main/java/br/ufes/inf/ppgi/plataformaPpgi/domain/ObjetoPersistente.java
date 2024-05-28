package br.ufes.inf.ppgi.plataformaPpgi.domain;

public abstract class ObjetoPersistente {
	
	public abstract Integer getId();
	
	public abstract int hashCode();
	
	public abstract boolean equals(Object obj);
	
	public Boolean isPersistente(){
		return (getId() != null);
	}

}
