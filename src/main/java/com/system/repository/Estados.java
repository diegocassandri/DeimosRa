package com.system.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.system.model.Estado;

public class Estados implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public Estados(EntityManager manager) {
		this.manager = manager;
	}

	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}

	public List<Estado> todos() {
		TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
		return query.getResultList();
	}
	
	public void adicionar(Estado estado) {
		this.manager.merge(estado);
	}
	
	public void excluir(Estado estado) {
		this.manager.remove(estado);
	}
	
	public boolean pesquisaPorNome(Estado estado) {
		Query query = manager.createQuery("From Estado where nome = :nome", Estado.class);
		query.setParameter("nome", estado.getNome());
		List<?> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Estado> porNomeSemelhante(String nome) {
		return manager.createQuery("from Estado where nome like :nome", Estado.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
}
