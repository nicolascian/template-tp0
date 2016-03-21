package ar.fiuba.tdd.template.tp0;

/**
 * Created by nicolas on 19/03/16.
 */
public class Token {
    private String candidatos;
    //private int cantidad;

    public Token(String posibles/*, int cuantificador*/) {
        this.candidatos = posibles;
        //this.cantidad = cuantificador;
    }

    public String getResult() {
//        // Cuantificador dice cuantas veces tiene que generar el caracter
//        StringBuffer buf = new StringBuffer();
//        int it1 = 0;
//        while (it1 < cantidad) {
//            it1 += 1;
//            // generar() emite un string
//            buf.append(generar());
//        }
//        String resultado = buf.toString();
//        return resultado;

        int rand = (int)(Math.random() * candidatos.length());
        return candidatos.substring(rand, rand + 1);
    }

//    private String generar() {
//        int rand = (int)(Math.random() * candidatos.length());
//        return candidatos.substring(rand, rand + 1);
//    }

}
