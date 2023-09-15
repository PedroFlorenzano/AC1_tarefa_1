package com.example.tarefa1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prod_nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "prod_qtd", length = 200, nullable = false)
    private Integer quantidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name =  "categoriaProdutos_id")
    private Categoria categoriaProdutos;

    public Produto(String nome, Integer quantidade){
        this.nome = nome;
        this.quantidade = quantidade;
    }
}
