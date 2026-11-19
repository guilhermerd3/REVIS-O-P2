import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("1. SISTEMA DE GERENCIAMENTO DE BIBLIOTECA");
        System.out.println("==========================================");
        BibBiblioteca biblioteca = new BibBiblioteca();
        BibLivro l1 = new BibLivro("Clean Code", "Robert C. Martin");
        BibLivro l2 = new BibLivro("Java Efetivo", "Joshua Bloch");
        biblioteca.adicionarLivro(l1);
        biblioteca.adicionarLivro(l2);

        BibMembro m1 = new BibMembro("Ana Silva");
        biblioteca.cadastrarMembro(m1);
        biblioteca.realizarEmprestimo(m1, l1);
        biblioteca.listarLivros();
        biblioteca.realizarDevolucao(m1, l1);

        System.out.println("\n==========================================");
        System.out.println("2. SISTEMA DE CONTROLE DE ESTOQUE");
        System.out.println("==========================================");
        EstEstoque estoque = new EstEstoque();
        EstProduto pEst1 = new EstProduto("P01", "Teclado Mecanico", 10, 250.0);
        EstProduto pEst2 = new EstProduto("P02", "Mouse Sem Fio", 15, 120.0);
        estoque.adicionarProduto(pEst1);
        estoque.adicionarProduto(pEst2);
        pEst1.removerEstoque(3);
        pEst2.adicionarEstoque(5);
        estoque.listarProdutos();
        System.out.printf("Valor total em estoque: %.2f%n", estoque.valorTotalEstoque());

        System.out.println("\n==========================================");
        System.out.println("3. SISTEMA DE GERENCIAMENTO DE ESCOLA");
        System.out.println("==========================================");
        EscTurma turma = new EscTurma("TP1-A", "Tecnicas de Programacao 1");
        EscAluno a1 = new EscAluno("202601", "Lucas Almeida");
        a1.adicionarNota(8.5);
        a1.adicionarNota(7.5);

        EscAluno a2 = new EscAluno("202602", "Beatriz Costa");
        a2.adicionarNota(5.0);
        a2.adicionarNota(6.0);

        turma.matricularAluno(a1);
        turma.matricularAluno(a2);
        turma.listarBoletim();

        System.out.println("\n==========================================");
        System.out.println("4. SISTEMA DE E-COMMERCE");
        System.out.println("==========================================");
        EcomProduto pEcom1 = new EcomProduto("E01", "Fone Bluetooth", 199.90);
        EcomProduto pEcom2 = new EcomProduto("E02", "Cabo USB-C", 39.90);

        EcomCarrinho carrinho = new EcomCarrinho();
        carrinho.adicionarProduto(pEcom1, 1);
        carrinho.adicionarProduto(pEcom2, 2);

        EcomPedido pedido = new EcomPedido("Carla Mendes", carrinho);
        pedido.imprimirResumo();

        System.out.println("\n==========================================");
        System.out.println("5. SISTEMA DE GESTAO DE TAREFAS");
        System.out.println("==========================================");
        TarGerenciador gerenciador = new TarGerenciador();
        TarTarefa t1 = new TarTarefa("Estudar POO", "Listas e associacoes em Java", "Alta");
        TarTarefa t2 = new TarTarefa("Revisar conceitos", "Encapsulamento e metodos", "Media");
        gerenciador.adicionarTarefa(t1);
        gerenciador.adicionarTarefa(t2);
        t1.marcarConcluida();
        gerenciador.listarPendentes();
        gerenciador.listarConcluidas();

        System.out.println("\n==========================================");
        System.out.println("6. SISTEMA DE FINANCAS PESSOAIS");
        System.out.println("==========================================");
        FinCarteira carteira = new FinCarteira("Rafael Souza");
        carteira.registrarTransacao(new FinTransacao("Salario", 2200.0, "RECEITA"));
        carteira.registrarTransacao(new FinTransacao("Transporte", 180.0, "DESPESA"));
        carteira.registrarTransacao(new FinTransacao("Alimentacao", 450.0, "DESPESA"));
        carteira.listarTransacoes();
        System.out.printf("Saldo atual: %.2f%n", carteira.calcularSaldo());
    }
}

class BibLivro {
    private String titulo;
    private String autor;
    private boolean disponivel;

    public BibLivro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean emprestar() {
        if (disponivel) {
            disponivel = false;
            return true;
        }
        return false;
    }

    public void devolver() {
        disponivel = true;
    }

    @Override
    public String toString() {
        return titulo + " (" + autor + ") - " + (disponivel ? "Disponivel" : "Emprestado");
    }
}

class BibMembro {
    private String nome;
    private List<BibLivro> livrosEmprestados;

    public BibMembro(String nome) {
        this.nome = nome;
        this.livrosEmprestados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public boolean pegarLivro(BibLivro livro) {
        if (livro != null && livro.emprestar()) {
            livrosEmprestados.add(livro);
            return true;
        }
        return false;
    }

    public boolean devolverLivro(BibLivro livro) {
        if (livro != null && livrosEmprestados.remove(livro)) {
            livro.devolver();
            return true;
        }
        return false;
    }
}

class BibBiblioteca {
    private List<BibLivro> livros;
    private List<BibMembro> membros;

    public BibBiblioteca() {
        this.livros = new ArrayList<>();
        this.membros = new ArrayList<>();
    }

    public void adicionarLivro(BibLivro livro) {
        if (livro != null) {
            livros.add(livro);
        }
    }

    public void cadastrarMembro(BibMembro membro) {
        if (membro != null) {
            membros.add(membro);
        }
    }

    public void realizarEmprestimo(BibMembro membro, BibLivro livro) {
        if (membro != null && livro != null && membro.pegarLivro(livro)) {
            System.out.println("Emprestimo realizado: '" + livro.getTitulo() + "' para " + membro.getNome());
        } else {
            System.out.println("Emprestimo nao realizado.");
        }
    }

    public void realizarDevolucao(BibMembro membro, BibLivro livro) {
        if (membro != null && livro != null && membro.devolverLivro(livro)) {
            System.out.println("Devolucao realizada: '" + livro.getTitulo() + "' por " + membro.getNome());
        } else {
            System.out.println("Devolucao nao realizada.");
        }
    }

    public void listarLivros() {
        for (BibLivro livro : livros) {
            System.out.println(" - " + livro);
        }
    }
}

class EstProduto {
    private String codigo;
    private String nome;
    private int quantidade;
    private double precoUnitario;

    public EstProduto(String codigo, String nome, int quantidade, double precoUnitario) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = Math.max(0, quantidade);
        this.precoUnitario = Math.max(0.0, precoUnitario);
    }

    public String getCodigo() {
        return codigo;
    }

    public void adicionarEstoque(int qtd) {
        if (qtd > 0) {
            quantidade += qtd;
        }
    }

    public boolean removerEstoque(int qtd) {
        if (qtd > 0 && quantidade >= qtd) {
            quantidade -= qtd;
            return true;
        }
        return false;
    }

    public double calcularValorTotal() {
        return quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Qtd: %d | Subtotal: %.2f", codigo, nome, quantidade, calcularValorTotal());
    }
}

class EstEstoque {
    private List<EstProduto> produtos;

    public EstEstoque() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(EstProduto produto) {
        if (produto != null) {
            produtos.add(produto);
        }
    }

    public double valorTotalEstoque() {
        double total = 0.0;
        for (EstProduto p : produtos) {
            total += p.calcularValorTotal();
        }
        return total;
    }

    public void listarProdutos() {
        for (EstProduto p : produtos) {
            System.out.println(" - " + p);
        }
    }
}

class EscAluno {
    private String matricula;
    private String nome;
    private List<Double> notas;

    public EscAluno(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
        this.notas = new ArrayList<>();
    }

    public void adicionarNota(double nota) {
        if (nota >= 0.0 && nota <= 10.0) {
            notas.add(nota);
        }
    }

    public double calcularMedia() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double soma = 0.0;
        for (double n : notas) {
            soma += n;
        }
        return soma / notas.size();
    }

    public boolean isAprovado() {
        return calcularMedia() >= 7.0;
    }

    @Override
    public String toString() {
        return String.format("%s - %s | Media: %.2f (%s)",
                matricula, nome, calcularMedia(), isAprovado() ? "Aprovado" : "Reprovado");
    }
}

class EscTurma {
    private String codigo;
    private String disciplina;
    private List<EscAluno> alunos;

    public EscTurma(String codigo, String disciplina) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.alunos = new ArrayList<>();
    }

    public void matricularAluno(EscAluno aluno) {
        if (aluno != null) {
            alunos.add(aluno);
        }
    }

    public void listarBoletim() {
        System.out.println("Turma: " + codigo + " - " + disciplina);
        for (EscAluno aluno : alunos) {
            System.out.println(" - " + aluno);
        }
    }
}

class EcomProduto {
    private String id;
    private String nome;
    private double preco;

    public EcomProduto(String id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = Math.max(0.0, preco);
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}

class EcomItemCarrinho {
    private EcomProduto produto;
    private int quantidade;

    public EcomItemCarrinho(EcomProduto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = Math.max(1, quantidade);
    }

    public EcomProduto getProduto() {
        return produto;
    }

    public void adicionarQuantidade(int qtd) {
        if (qtd > 0) {
            quantidade += qtd;
        }
    }

    public double calcularSubtotal() {
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return String.format("%s x%d -> %.2f", produto.getNome(), quantidade, calcularSubtotal());
    }
}

class EcomCarrinho {
    private List<EcomItemCarrinho> itens;

    public EcomCarrinho() {
        this.itens = new ArrayList<>();
    }

    public List<EcomItemCarrinho> getItens() {
        return itens;
    }

    public void adicionarProduto(EcomProduto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            return;
        }
        for (EcomItemCarrinho item : itens) {
            if (item.getProduto().getId().equalsIgnoreCase(produto.getId())) {
                item.adicionarQuantidade(quantidade);
                return;
            }
        }
        itens.add(new EcomItemCarrinho(produto, quantidade));
    }

    public double calcularTotal() {
        double total = 0.0;
        for (EcomItemCarrinho item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }
}

class EcomPedido {
    private String cliente;
    private List<EcomItemCarrinho> itens;
    private double total;

    public EcomPedido(String cliente, EcomCarrinho carrinho) {
        this.cliente = cliente;
        this.itens = new ArrayList<>(carrinho.getItens());
        this.total = carrinho.calcularTotal();
    }

    public void imprimirResumo() {
        System.out.println("Cliente: " + cliente);
        for (EcomItemCarrinho item : itens) {
            System.out.println(" - " + item);
        }
        System.out.printf("Total do Pedido: %.2f%n", total);
    }
}

class TarTarefa {
    private String titulo;
    private String descricao;
    private String prioridade;
    private boolean concluida;

    public TarTarefa(String titulo, String descricao, String prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.concluida = false;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void marcarConcluida() {
        this.concluida = true;
    }

    @Override
    public String toString() {
        return (concluida ? "[CONCLUIDA] " : "[PENDENTE] ") + titulo + " (" + prioridade + ") - " + descricao;
    }
}

class TarGerenciador {
    private List<TarTarefa> tarefas;

    public TarGerenciador() {
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(TarTarefa tarefa) {
        if (tarefa != null) {
            tarefas.add(tarefa);
        }
    }

    public void listarPendentes() {
        System.out.println("Pendentes:");
        for (TarTarefa t : tarefas) {
            if (!t.isConcluida()) {
                System.out.println(" - " + t);
            }
        }
    }

    public void listarConcluidas() {
        System.out.println("Concluidas:");
        for (TarTarefa t : tarefas) {
            if (t.isConcluida()) {
                System.out.println(" - " + t);
            }
        }
    }
}

class FinTransacao {
    private String descricao;
    private double valor;
    private String tipo;

    public FinTransacao(String descricao, double valor, String tipo) {
        this.descricao = descricao;
        this.valor = Math.max(0.0, valor);
        this.tipo = tipo != null ? tipo.toUpperCase() : "DESPESA";
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f", tipo, descricao, valor);
    }
}

class FinCarteira {
    private String titular;
    private List<FinTransacao> transacoes;

    public FinCarteira(String titular) {
        this.titular = titular;
        this.transacoes = new ArrayList<>();
    }

    public void registrarTransacao(FinTransacao transacao) {
        if (transacao != null) {
            transacoes.add(transacao);
        }
    }

    public double calcularSaldo() {
        double saldo = 0.0;
        for (FinTransacao t : transacoes) {
            if ("RECEITA".equals(t.getTipo())) {
                saldo += t.getValor();
            } else if ("DESPESA".equals(t.getTipo())) {
                saldo -= t.getValor();
            }
        }
        return saldo;
    }

    public void listarTransacoes() {
        System.out.println("Carteira de " + titular);
        for (FinTransacao t : transacoes) {
            System.out.println(" - " + t);
        }
    }
}