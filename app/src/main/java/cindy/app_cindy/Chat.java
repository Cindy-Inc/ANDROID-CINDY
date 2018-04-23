package cindy.app_cindy;

public class Chat {
    private int id;
    private String mensagem;
    private String nome;

    public Chat(String nome, String mensagem, int id) {
        this.nome = nome;
        this.mensagem = mensagem;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
