
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

// JOKER: Clase principal de la calculadora
public class Calculadora {

    // JOKER: Variables globales
    static HttpServer se;
    static String ht;
    static String bo;
    static String[] ps;
    static String[] kv;
    static String v1 = "";
    static String v2 = "";
    static String op = "";
    static String va;
    static String me;
    static int a;
    static int b;
    static int i;
    static double r;
    static byte[] bt;
    static OutputStream os;

    /**
     * JOKER: Realiza una división entre dos números.
     * Comprueba que el divisor no sea cero.
     * @param a primer número
     * @param b segundo número
     * @return resultado de la división
     */
    static double di(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        return a / b;
    }

    // JOKER: Realiza una suma
    static int su(int a, int b) {
        return a + b;
    }

    // JOKER: Realiza una resta
    static int re(int a, int b) {
        return a - b;
    }

    // JOKER: Realiza una multiplicación
    static int mu(int a, int b) {
        return a * b;
    }

    // JOKER: Calcula una potencia
    static double po(int a, int b) {
        return Math.pow(a, b);
    }

    // JOKER: Procesa las peticiones del formulario
    static void pr(HttpExchange ex) throws IOException {

        ht = Files.readString(Path.of("index.html"));

        if (ex.getRequestMethod().equals("POST")) {

            bo = new String(
                ex.getRequestBody().readAllBytes(),
                StandardCharsets.UTF_8
            );

            v1 = "";
            v2 = "";
            op = "";

            ps = bo.split("&");

            for (i = 0; i < ps.length; i++) {
                kv = ps[i].split("=", 2);

                if (kv.length == 2) {
                    va = URLDecoder.decode(
                        kv[1],
                        StandardCharsets.UTF_8
                    );

                    if (kv[0].equals("n1")) {
                        v1 = va;
                    }

                    if (kv[0].equals("n2")) {
                        v2 = va;
                    }

                    if (kv[0].equals("op")) {
                        op = va;
                    }
                }
            }

            me = "";

            if (v1.matches("-?[0-9]{1,5}") &&
                v2.matches("-?[0-9]{1,5}")) {

                a = Integer.parseInt(v1);
                b = Integer.parseInt(v2);

                if (op.equals("sum")) {
                    r = su(a, b);
                } else if (op.equals("res")) {
                    r = re(a, b);
                } else if (op.equals("mul")) {
                    r = mu(a, b);
                } else if (op.equals("pot")) {
                    r = po(a, b);
                } else if (op.equals("div")) {
                    try {
                        r = di(a, b);
                    } catch (ArithmeticException er) {
                        me = er.getMessage();
                    }
                }

                if (me.isEmpty()) {
                    me = "Resultado: " + r;
                }

            } else {
                me = "Introduce dos números enteros de hasta 5 cifras.";
            }

            ht = ht.replace(
                "<!--RESULTADO-->",
                "<h2>" + me + "</h2>"
            );
        }

        bt = ht.getBytes(StandardCharsets.UTF_8);

        ex.getResponseHeaders().set(
            "Content-Type",
            "text/html; charset=UTF-8"
        );

        ex.sendResponseHeaders(200, bt.length);

        os = ex.getResponseBody();
        os.write(bt);
        os.close();
    }

    // JOKER: Inicia el servidor
    public static void main(String[] ar) throws IOException {

        se = HttpServer.create(
            new InetSocketAddress(8080),
            0
        );

        se.createContext("/", ex -> pr(ex));

        se.start();

        System.out.println(
            "Calculadora iniciada en http://localhost:8080"
        );
    }
}
