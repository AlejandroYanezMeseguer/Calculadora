public class Operaciones {

    public static int Suma(int Operando1, int Operando2){

        int resutado = Operando1 + Operando2;

        return resutado;
    }

    public static int Resta(int Operando1, int Operando2){

        int resutado = Operando1 - Operando2;

        return resutado;
    }

    public static int Multiplicacion(int Operando1, int Operando2){

        int resutado = Operando1 * Operando2;

        return resutado;
    }

    public static int Division(int Operando1, int Operando2){

        int resutado = Operando1 / Operando2;

        return resutado;
    }

    public static int Potencia(int Operando1, int Operando2){
        int resultado = 1;
        for (int i = 0; i < Operando2; i++) {
            resultado *= Operando1;
        }
        return resultado;
    }



}

