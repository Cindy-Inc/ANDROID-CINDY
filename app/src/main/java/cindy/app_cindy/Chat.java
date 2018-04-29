package cindy.app_cindy;

public class Chat {
    private int id;
    private String mensagem;
    private int sizeText;

    public Chat(String mensagem, int id, int sizeText) {
        this.mensagem = mensagem;
        this.id = id;
        this.sizeText = sizeText;
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

    public int getSizeText() {
        return sizeText;
    }

    public void setSizeText(int sizeText) {
        this.sizeText = sizeText;
    }
}
