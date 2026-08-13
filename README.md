# Simulador Bancario V1

Simulador de un sistema bancario por consola, desarrollado en Java como
proyecto de práctica de **estructuras de control** (nivel principiante,
sin Programación Orientada a Objetos).

## Descripción general

El programa simula el uso de un cajero/sistema bancario simple: permite
iniciar sesión con una clave, consultar el saldo, retirar dinero y
depositar dinero. Todo el flujo corre en consola mediante menús numerados.

## Funcionalidades

| Función | Descripción |
|---|---|
| Iniciar sesión | Solicita una clave de acceso y la compara con la clave correcta (`12345`). |
| Límite de intentos | Permite hasta 3 intentos fallidos; al llegar al límite, muestra un mensaje de bloqueo y **cierra el programa por completo** (simulando un tiempo de espera, por ejemplo 59s, sin implementarlo realmente). |
| Consultar saldo | Muestra el saldo actual de la cuenta en COP. |
| Retirar dinero | Descuenta un monto del saldo, validando que sea positivo y que no supere el saldo disponible. |
| Depositar dinero | Suma un monto al saldo, validando que sea positivo. |
| Cerrar sesión | Regresa al menú principal. |
| Apagar el sistema | Finaliza la ejecución del programa. |

## Flujo del programa

```
Menú Principal
├── 1. Iniciar Sesión
│     └── ¿Clave correcta?
│           ├── Sí → Menú Interno
│           │        ├── 1. Consultar saldo
│           │        ├── 2. Retirar dinero
│           │        ├── 3. Depositar dinero
│           │        └── 4. Cerrar sesión → vuelve al Menú Principal
│           └── No → cuenta el intento fallido (máx. 3)
│                     └── Si llega al límite → cierra el programa (return)
└── 2. Apagar el sistema → termina el programa
```

## Datos del sistema (v1)

- **Clave de acceso:** `12345` (fija en el código)
- **Saldo inicial:** `$800.000 COP` (fijo en el código)
- **Usuarios:** uno solo, sin identificación (no se pide usuario/nombre, solo clave)

## Limitaciones conocidas de esta versión

Estas limitaciones son intencionales: forman parte del aprendizaje progresivo
del curso, antes de introducir POO.

1. **Sin clases propias**: todo el código vive dentro de `main()`. No existen
   objetos como `Usuario`, `Cuenta` o `Banco`.
2. **Un solo usuario**: la clave y el saldo están escritos directamente en el
   código (hardcodeados), no hay una base de datos ni lista de usuarios.
3. **Sin manejo de excepciones**: si se ingresa texto donde se espera un
   número (por ejemplo, en el menú o al retirar/depositar), el programa se
   detiene abruptamente con un error (`InputMismatchException`).
4. **Bloqueo simulado, no real**: al superar los 3 intentos fallidos, el
   programa se cierra por completo con `return` (simula, por ejemplo, un
   tiempo de espera de 59s). No es un bloqueo temporal de verdad: no hay
   temporizador ni forma de reintentar sin volver a ejecutar el programa
   desde cero.
5. **Sin transferencias**: al haber un único usuario, no tiene sentido (ni es
   posible) transferir dinero a otra cuenta.

## Próximos pasos (Versión 2 — con POO)

La versión 2 reemplazará este enfoque "todo en `main()`" por un diseño
orientado a objetos, agregando:

- Clases como `Usuario`, `Cuenta` y `Banco` (o similar), separando
  responsabilidades.
- Soporte para **múltiples usuarios**, cada uno con su propia clave y saldo.
- **Transferencias de dinero entre usuarios**.
- **Manejo de excepciones** (`try/catch`) para evitar que el programa se
  detenga ante entradas inválidas, con mensajes de error controlados.
- Posiblemente, persistencia de datos (por ejemplo, guardar usuarios en un
  archivo o estructura de datos más robusta).

## ▶️ Cómo ejecutar

1. Compilar: `javac SimuladorBancarioV1.java`
2. Ejecutar: `java simuladorbancariov1.SimuladorBancarioV1`
3. Seguir las instrucciones en consola (clave de acceso: `12345`).
