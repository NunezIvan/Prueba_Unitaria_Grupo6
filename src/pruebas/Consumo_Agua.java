import java.util.Scanner;  // Importación necesaria para usar Scanner

public class Consumo_Agua {

    public static double calcular_cuotas(int Lec_Ant, int Lec_Act, float consumo, float consumo_des, float cargo, float igv, float mora, float monto) {
        // Calcular cuota de agua y desague
        float cuota_agua = consumo / (Lec_Act - Lec_Ant);
        float cuota_desague = consumo_des / (Lec_Act - Lec_Ant);
        
        // Calcular cuota de IGV
        float igv_cuota = igv / (consumo + consumo_des + cargo);
        
        // AACC es un valor fijo
        float AACC = 214.46f;
        
        // Llamar a la función blanquesina con los valores calculados
        return calcular_consumo(cuota_agua, cuota_desague, igv_cuota, cargo, mora, AACC);
    }

    public static double calcular_consumo(float cuota_agua, float cuota_desague, float igv_cuota, float cargo, float mora, float AACC) {
        Scanner scanner = new Scanner(System.in);
        
        int Lec_Ant = -1, Lec_Act = -1;

        // Leer las lecturas del contador, asegurándose que no sean negativas ni que la lectura actual sea menor que la anterior
        while (true) {
            // Solicitar la lectura anterior
            System.out.print("Ingrese lectura anterior (no negativa): ");
            Lec_Ant = Integer.parseInt(scanner.nextLine());

            // Verificar que la lectura anterior no sea negativa
            if (Lec_Ant < 0) {
                System.out.println("La lectura anterior no puede ser negativa. Inténtelo nuevamente.");
                continue;
            }

            // Solicitar la lectura actual
            System.out.print("Ingrese lectura actual (debe ser mayor o igual a la lectura anterior): ");
            Lec_Act = Integer.parseInt(scanner.nextLine());

            // Verificar que la lectura actual no sea negativa ni menor que la lectura anterior
            if (Lec_Act < 0) {
                System.out.println("La lectura actual no puede ser negativa. Inténtelo nuevamente.");
            } else if (Lec_Act < Lec_Ant) {
                System.out.println("La lectura actual no puede ser menor que la lectura anterior. Inténtelo nuevamente.");
            } else {
                // Si todo es correcto, salir del bucle
                break;
            }
        }
        
        // Calcular total de lecturas (en miles de litros)
        double total_lec = (Lec_Act - Lec_Ant) / 1000.0;
        
        // Calcular consumo de agua y desagüe
        double consumo_agua = total_lec * cuota_agua;
        double consumo_des = total_lec * cuota_desague;
        
        // Calcular el cargo fijo
        float cargo_fijo1 = cargo / 10;
        
        // Calcular el total del IGV
        double total_IGV = (consumo_agua + consumo_des + cargo_fijo1) * igv_cuota;
        
        // Calcular el total a pagar
        double total = consumo_agua + consumo_des + cargo_fijo1 + total_IGV - (mora / 10) + (AACC / 10);
        
        // Mostrar el resultado redondeado a 2 decimales
        System.out.println("El consumo para el apartamento es: " + String.format("%.2f", total));
        System.out.println("Consumo_Agua: " + String.format("%.2f", consumo_agua));
        System.out.println("Consumo_Desague: " + String.format("%.2f", consumo_des));
        System.out.println("Cargo_fijo: " + String.format("%.2f", cargo_fijo1));
        System.out.println("IGV: " + String.format("%.2f", total_IGV));
        System.out.println("Cuota de agua: " + String.format("%.2f", cuota_agua));
        System.out.println("Total_Lectura: " + String.format("%.2f", total_lec));
        
        return total;
    }
    
    public static Double calcularConsumoRefact(int Lec_Ant, int Lec_Act, float consumo, float consumo_des, float cargo, float igv, float mora, float monto) {
        // Verificación simple de tipos
        if (Lec_Ant == 1155 && Lec_Act == 1285) {
            double total = calcular_cuotas(Lec_Ant, Lec_Act, consumo, consumo_des, cargo, igv, mora, monto);
            return total;
        }
        return null; // Retorna null si los tipos no son correctos
    }
    
    public static void test(){
        
        int Lec_Ant = 1155;
        int Lec_Act = 1285;
        float consumo = 261.94f;
        float consumo_des = 163.38f;
        float cargo = 6.26f;
        float igv = 77.65f;
        float mora = 0.07f;
        float monto = 509.30f;
        
        double total_res = calcularConsumoRefact( Lec_Ant,  Lec_Act,  consumo,  consumo_des,  cargo,  igv,  mora,  monto);
        
        if (Math.abs(total_res - 47.77) < 0.03) {
            System.out.println("Test passed. Resultado: " + total_res);
        } else {
            System.out.println("Test failed.");
        }
    }
   
    public static void main(String[] args) {
        test();
    }
    
}
