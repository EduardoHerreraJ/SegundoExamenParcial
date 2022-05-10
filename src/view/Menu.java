package view;
import java.util.Scanner;

import model.Cuenta;
import service.CuentaService;
import repository.CuentaRepository;
import java.util.List;

public class Menu {

    private Scanner sc;
    private CuentaService cuentaService;
    CuentaRepository cuentaRepository;

    public Menu() {
        sc = new Scanner(System.in);
        cuentaService = new CuentaService();
    }

    public void initializeApp() {
        System.out.println("Bienvenido al Sistema Financiero Nacional");
        int opcion = 0;
        while(opcion != 4) {
            displayMenu();
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion){
                case(1):
                    createAccount();
                    initializeApp();
                case(2):
                    buscarCuentaPorIndice();
                case(3):
                    buscarPorNumeroDeCuenta();
                default: {
                    System.out.println("Opcion incorrecta");
                }
            }
        }
    }

    private void displayMenu() {
        System.out.println("------Sistema Bancario-------\n Seleccione una opción \n 1) Crear Cuenta \n 2)Buscar Cuenta \n 3)Depositar \n 4) Retirar");
    }

    private void createAccount() {
        System.out.print("A nombre de quén estará la cuenta: ");
        String nombre = sc.nextLine();
        System.out.print("¿A que banco pertenece la cuenta? \n A: Banco A\n B: Banco B\n C: Banco C\n");
        String banco = sc.nextLine();
        System.out.print("Escriba el núnmero de cuenta");
        String numeroCuenta = sc.nextLine();
        Cuenta nuevaCuenta = cuentaService.agregarCuenta(nombre, banco, numeroCuenta);
        System.out.println("Se ha creado la cuenta " + nuevaCuenta.getNumeroDeCuenta()+ " a nombre de "+ nombre + " del banco "+ banco);
        abrirMenuCuenta(cuentaService.countCuentas()-1);
    }
    //TODO mostrar todas las cuentas
    private void buscarCuentaPorIndice() {
        int totalCuentas = cuentaService.countCuentas();
        /*List<Cuenta> cuentas = cuentaRepository.getCuentas();
        for(Cuenta cuenta:cuentas){
            System.out.println(cuenta.getNumeroDeCuenta());
        }*/
        System.out.print("Ingrese el indice de la cuenta[1-" + totalCuentas + "]: ");
        int indiceCuenta = Integer.parseInt(sc.nextLine()) - 1;
        abrirMenuCuenta(indiceCuenta);
    }

    private void buscarPorNumeroDeCuenta() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = sc.nextLine();
        int indiceCuenta = cuentaService.getIndiceCuenta(numeroCuenta);
        abrirMenuCuenta(indiceCuenta);
    }

    

    private void abrirMenuCuenta(int indiceCuenta) {
        int input = 0;
        while(input != 3) {
            printSaldo(cuentaService.getCuenta(indiceCuenta));
            printMenuCuenta();
            input = Integer.parseInt(sc.nextLine());
            switch (input){
                case (1): 
                    hacerDeposito(indiceCuenta);
                case (2):
                    hacerRetiro(indiceCuenta);
                default:  {
                    System.out.println("Revisa la informacion de la cuenta");
                }
            }
        }
    }

    private void printSaldo(Cuenta c) {
        System.out.println("El saldo actual de la cuenta " + c.getNumeroDeCuenta() + " es de");
        System.out.println(c.getMonto() + " solidos pesos");
    }

    private void printMenuCuenta() {
        System.out.println("Que acción desea hacer?");
        System.out.println("1. Depositar");
        System.out.println("2. Retirar");
        System.out.println("3. Regresar");
        System.out.print("Ingrese una opcion: ");
    }

    private void hacerDeposito(int indice) {
        System.out.print("Ingresa el monto que deseas depositar: ");
        double monto = Double.parseDouble(sc.nextLine());
        cuentaService.depositar(indice, monto);
    }

    private void hacerRetiro(int indice) {
        System.out.print("Ingresa el monto que deseas retirar: ");
        double monto = Double.parseDouble(sc.nextLine());
        cuentaService.retirar(indice, monto);
    }

}

