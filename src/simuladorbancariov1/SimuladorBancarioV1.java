package simuladorbancariov1;

import java.util.Scanner;

/**
 * SimuladorBancarioV1
 * -------------------------------------------------------------------------
 * Simulador básico de un sistema bancario por consola (nivel principiante).
 *
 * Funcionalidades:
 *   - Inicio de sesión con clave de acceso (con límite de intentos).
 *   - Consulta de saldo.
 *   - Retiro de dinero (con validación de saldo suficiente).
 *   - Depósito de dinero.
 *   - Cierre de sesión y apagado del sistema.
 *
 * Limitaciones de esta versión (a propósito, por ser la v1):
 *   - Un solo usuario, con clave y saldo fijos (hardcodeados en el código).
 *   - Todo el programa vive dentro de main(): no hay clases propias
 *     (Usuario, Cuenta, Banco, etc.) ni separación en métodos.
 *   - No hay manejo de excepciones (try/catch): si se ingresa un valor
 *     no numérico donde se espera un número, el programa se cae con
 *     InputMismatchException.
 *   - Al superar el límite de intentos fallidos, el programa se cierra
 *     por completo (simula un "bloqueo temporal" de forma simplificada,
 *     sin manejar un tiempo de espera real).
 *   - No permite transferencias entre usuarios (solo hay un usuario).
 *
 * Estas limitaciones se resolverán en la v2, aplicando Programación
 * Orientada a Objetos (clases separadas, múltiples usuarios, transferencias
 * entre cuentas y manejo de excepciones).
 *
 * @author  Olver Jayariyu
 * @version 1.1
 */
public class SimuladorBancarioV1 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // ---- Datos "hardcodeados" del único usuario del sistema ----
        String claveCorrecta = "12345"; // Clave de acceso válida
        String claveIngresada;          // Clave que el usuario escribe en cada intento
        long saldoActual = 800000;      // Saldo inicial de la cuenta (COP)
        
        // Variables auxiliares para las operaciones bancarias
        long cantidadRetirar, cantidadDepositar;
        
        // Variables de control de los menús
        int opcionPrincipal, opcionInterna;
        
        // Control de intentos fallidos de inicio de sesión
        int limiteIntento = 3;
        int contador = 0;

        // =========================================================
        // MENÚ PRINCIPAL (se repite hasta que el usuario elija "2. Apagar")
        // =========================================================
        do {
            System.out.println("---------- SIMULADOR BANCARIO V1 ----------");
            System.out.println("1. Iniciar Sesión\n"
                    + "2. Apagar el sistema\n"
                    + "Por favor, seleccione una opción: ");
            opcionPrincipal = entrada.nextInt();
            System.out.println("-----------------------------------------");

            switch (opcionPrincipal) {
                
                // ---------- OPCIÓN 1: Iniciar sesión ----------
                case 1:
                    System.out.println("Ingrese su clave de acceso: ");
                    claveIngresada = entrada.next();
                    System.out.println("-----------------------------------------");

                    if (claveIngresada.equals(claveCorrecta)) {
                        
                        // =================================================
                        // MENÚ INTERNO (solo accesible tras autenticarse)
                        // Se repite hasta que el usuario elija "4. Cerrar Sesión"
                        // =================================================
                        do {
                            System.out.println("1. Consultar saldo\n"
                                    + "2. Retirar dinero\n"
                                    + "3. Depositar dinero\n"
                                    + "4. Cerrar Sesión\n"
                                    + "Por favor, seleccione una opción: ");
                            opcionInterna = entrada.nextInt();
                            System.out.println("-----------------------------------------");

                            switch (opcionInterna) {
                                
                                // ---- Consultar saldo ----
                                case 1:
                                    System.out.println("Su saldo disponible es: $" + saldoActual + " COP.");
                                    System.out.println("-----------------------------------------");
                                    break;
                                    
                                // ---- Retirar dinero ----
                                case 2:
                                    System.out.println("Ingrese la cantidad que desea retirar: ");
                                    cantidadRetirar = entrada.nextLong();

                                    if (cantidadRetirar > 0 && cantidadRetirar <= saldoActual) {
                                        // Retiro válido: hay saldo suficiente
                                        saldoActual = saldoActual - cantidadRetirar;
                                        System.out.println("Retiro exitoso. Retire su dinero.");
                                    } else if (cantidadRetirar <= 0) {
                                        // Monto inválido (cero o negativo)
                                        System.out.println("Error: la cantidad a retirar debe ser mayor que 0 COP.");
                                    } else {
                                        // Monto mayor al saldo disponible
                                        System.out.println("Transacción rechazada. Saldo insuficiente.");
                                    }
                                    break;
                                    
                                // ---- Depositar dinero ----
                                case 3:
                                    System.out.println("Ingrese la cantidad que desea depositar: ");
                                    cantidadDepositar = entrada.nextLong();

                                    if (cantidadDepositar > 0) {
                                        saldoActual = saldoActual + cantidadDepositar;
                                        System.out.println("Depósito exitoso. Su saldo ha sido actualizado.");
                                    } else {
                                        System.out.println("Error: El monto a depositar debe ser mayor a 0 COP.");
                                    }
                                    break;
                                
                                // ---- Cerrar sesión (vuelve al menú principal) ----
                                case 4:
                                    System.out.println("Cerrando sesión de forma segura...");
                                    System.out.println("Sesión finalizada.");
                                    break;
                                
                                // ---- Opción no reconocida dentro del menú interno ----
                                default:
                                    System.out.println("Error: Opción inválida. Intente de nuevo.");
                                    break;
                            }
                        } while (opcionInterna != 4); // Repite el menú interno hasta cerrar sesión
                    } else {
                         // Clave incorrecta: se incrementa el contador de intentos fallidos
                        contador++;
                        if (contador >= limiteIntento) {
                            System.out.println("Acceso bloqueado. Ha superado el límite máximo de intentos permitidos.");
                            
                             // return: corta la ejecución de main() de inmediato.
                            // Aquí se usa para SIMULAR un bloqueo temporal (por ejemplo,
                            // "espere 59s para volver a intentar"): en lugar de manejar
                            // un tiempo de espera real, el programa simplemente se cierra
                            // por completo y el usuario tendría que volver a ejecutarlo.
                            return;
                        } else {
                            System.out.println("Contraseña incorrecta. Intente iniciar sesión nuevamente.");
                        }
                    }
                    break;
                 
                 // ---------- OPCIÓN 2: Apagar el sistema ----------
                case 2:
                    System.out.println("Cerrando el sistema... Apagado completado de forma correcta.");
                    break;
                    
                // ---------- Opción no reconocida en el menú principal ----------
                default:
                    System.out.println("Error: Opción inválida. Seleccione una opción del menú.");
                    break;
            }
        } while (opcionPrincipal != 2);  // Repite el menú principal hasta apagar el sistema
    }
}