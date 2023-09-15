package com.example.tarefa1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tarefa1.model.Categoria;
import com.example.tarefa1.model.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CategoriaRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Categoria inserirEAtualizarCategoria(Categoria categoria){
        entityManager.merge(categoria);
        return categoria;
    }
    
    public List<Categoria> selecionarTodasCategorias(){
        return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
    }
    
    public Categoria obterCategoriaPorId(Long id){
        String jpql = "select c from Categoria c where c.id like :id";
        TypedQuery<Categoria> query = entityManager.createQuery(jpql, Categoria.class);
        query.setParameter("id", "%" + id + "%");
        return query.getSingleResult();
    }
    
    @Transactional
    public void excluirCategoriaPorId(Long id){
        Categoria categoriaAntiga = entityManager.find(Categoria.class, id);
        this.entityManager.remove(categoriaAntiga);
    }
    
}
