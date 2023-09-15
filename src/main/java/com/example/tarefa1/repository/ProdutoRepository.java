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
public class ProdutoRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Produto inserirProduto(Produto produto) {
        entityManager.merge(produto);
        return produto;
    }

    @Transactional
    public List<Produto> buscarTodosProdutos() {
        return entityManager.createQuery("from Produto", Produto.class).getResultList();
    }

    @Transactional
    public Produto buscarProdutoPorNome(String nome) {
        String jpql = "select c from Produto c where c.nome like :nome";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getSingleResult();
    }

    @Transactional
    public Produto buscarProdutoPorId(Long id) {
        return entityManager.find(Produto.class, id);
    }

    @Transactional
    public Produto atualizarProduto(Produto produto, Long id) {
        Produto produtoAntigo = this.buscarProdutoPorId(id);
        produtoAntigo.setNome(produto.getNome());
        produtoAntigo.setQuantidade(produto.getQuantidade());
        return this.entityManager.merge(produtoAntigo);
    }

    @Transactional
    public Produto vinculaProdutoEmCategoria(Categoria categoria, Long id){
        Produto produtoAntigo = this.buscarProdutoPorId(id);
        produtoAntigo.setCategoriaProdutos(categoria);
        return entityManager.merge(produtoAntigo);
    }

    @Transactional
    public void excluirProdutoPorId(Long id) {
        Produto produtoAntigo = entityManager.find(Produto.class, id);
        this.entityManager.remove(produtoAntigo);
    }
}
