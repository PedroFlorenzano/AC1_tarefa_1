package com.example.tarefa1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tarefa1.model.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ProdutoRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Produto inserirEAtualizarProduto(Produto produto){
        entityManager.merge(produto);
        return produto;
    }

    public List<Produto> selecionarTodosProdutos(){
        return entityManager.createQuery("from Produto", Produto.class).getResultList();
    }

    public Produto obterProdutoPorId(Long id){
        String jpql = "select c from Produto c where c.id like :id";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("id", "%" + id + "%");
        return query.getSingleResult();
    }

    @Transactional
    public void excluirProdutoPorId(Long id){
        Produto produtoAntigo = entityManager.find(Produto.class, id);
        this.entityManager.remove(produtoAntigo);
    }
}
