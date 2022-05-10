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
    //Recator (Nombre de la clase de initializeApp to InicializarApp para tener todo en Esp)
    public void inicializarApp() {
        System.out.println("Bienvenido al Sistema Financiero Nacional");
        int opcion = 0;
        while(opcion != 4) {
            desplegarMenu();
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion){
                case(1):
                    crearCuenta();
                    inicializarApp();
                case(2):
                    buscarCuentaMenu();
                    inicializarApp();
                case(3):
                    buscarCuentaMenu();
                    inicializarApp();
                case(4):
                    System.exit(0);
                default: {
                    System.out.println("Opcion incorrecta");
                }
            }
        }
    }

    private void desplegarMenu() {
        System.out.println("------Sistema Bancario-------\n Seleccione una opción \n 1) Crear Cuenta \n 2)Consultar Cuenta \n 3)Depositar/Retirar \n 4) Salir");
    }

    private void crearCuenta() {
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

    private void buscarCuentaMenu(){
        System.out.print("¿De que forma prefiere buscar la cuenta? \n 1) Por Inidice \n 2) Por número de Cuenta \n 3) Regresar\n");
        int metodoDeBusqueda = Integer.parseInt(sc.nextLine());
        while(metodoDeBusqueda != 3){
            switch (metodoDeBusqueda){
                case(1):
                    buscarCuentaPorIndice();
                case(2):
                    buscarPorNumeroDeCuenta();
                default: {
                    System.out.println("Opcion incorrecta");
                }
            }
        }
        
        
    }

    //TODO mostrar todas las cuentas
    private void buscarCuentaPorIndice() {
        /*int cuentasCounter = cuentaService.countCuentas();
        String[]cuentas = cuentaRepository.getCuentas();
        for(String cuenta: cuentas){
            System.out.println(cuenta);
        }*/
        System.out.print("Ingrese el indice de la cuenta: ");
        int indiceCuenta = Integer.parseInt(sc.nextLine()) - 1;
        abrirMenuCuenta(indiceCuenta);
    }

    private void buscarPorNumeroDeCuenta() {
        System.out.print("Ingrese el número de la cuenta que busca: ");
        String numeroCuenta = sc.nextLine();
        int indiceCuenta = cuentaService.getIndiceCuenta(numeroCuenta);
        abrirMenuCuenta(indiceCuenta);
    }

    private void abrirMenuCuenta(int indiceCuenta) {
        int input = 0;
        while(input != 3) {
            Cuenta cuenta = cuentaService.getCuenta(indiceCuenta);
            System.out.println("El saldo actual es de $"+ cuenta.getMonto());
            
            System.out.println("Que acción desea hacer? \n1) Depositar\n2) Retirar\n3) Regresar\n1) Depositar");
            input = Integer.parseInt(sc.nextLine());
            switch (input){
                case (1): 
                    hacerDeposito(indiceCuenta);
                case (2):
                    hacerRetiro(indiceCuenta,cuenta);
                default:  {
                    System.out.println("Revisa la informacion de la cuenta");
                }
            }
        }
    }

    private void hacerDeposito(int indice) {
        System.out.print("Cuanto desea depositar?: ");
        double monto = Double.parseDouble(sc.nextLine());
        cuentaService.depositar(indice, monto);
    }

    private void hacerRetiro(int indice, Cuenta cuenta) {
        System.out.print("Cuanto desea retirar?: ");
        double monto = Double.parseDouble(sc.nextLine());
        System.out.print("Desde que banco está retirando? ");
        String bancoRetiro = sc.nextLine().toLowerCase();
        String bancoCuenta = cuenta.getBanco().toLowerCase();
        if(bancoRetiro == bancoCuenta)
        cuentaService.retirar(indice, monto);
        else if(bancoRetiro == "a")
            cuentaService.retirar(indice, monto, 30);
        else if(bancoRetiro == "b")
            cuentaService.retirar(indice, monto, 15);
        else if(bancoRetiro == "c")
            cuentaService.retirar(indice, monto, 10);
        
        cuentaService.retirar(indice, monto);
    }

}

