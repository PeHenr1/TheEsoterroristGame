package peu.example.theesoterroristgame;

import android.widget.ImageView;

public class Arma {
    private String nome;
    private String dano;
    private ImageView img;

    public Arma(String nome, String dano, ImageView img) {
        this.nome = nome;
        this.dano = dano;
        this.img = img;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public String getDano() {
        return dano;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDano(String dano) {
        this.dano = dano;
    }
}
