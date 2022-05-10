package test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertNotNull;
import model.Cuenta;
import service.CuentaService;
public class SistemTest {
    private CuentaService cuentaService;

    @Test
    public void crearCuenta() {
        String nombre = "Eduardo";
        String banco = "C";
        String numeroCuenta = "123";
        Cuenta cuenta = cuentaService.agregarCuenta(nombre, banco, numeroCuenta);
        Cuenta cuentaTest = cuentaService.getCuenta(1);
        assertEquals(cuenta, cuentaTest);
    }

    @Test
    public void depositar() {
        String nombre = "Eduardo";
        String banco = "C";
        String numeroCuenta = "123";
        Cuenta cuenta = cuentaService.agregarCuenta(nombre, banco, numeroCuenta);
        double deposito = 100;
        cuentaService.depositar(1,deposito);
        Cuenta cuentaTest = cuentaService.getCuenta(1);
        assertEquals(100, cuentaTest.getMonto());
    }

    @Test
    public void retirar() {
        String nombre = "Eduardo";
        String banco = "C";
        String numeroCuenta = "123";
        Cuenta cuenta = cuentaService.agregarCuenta(nombre, banco, numeroCuenta);
        double deposito = 100;
        cuentaService.depositar(1,deposito);
        cuentaService.retirar(1,50);
        Cuenta cuentaTest = cuentaService.getCuenta(1);
        assertEquals(50, cuentaTest.getMonto());
    }



}
