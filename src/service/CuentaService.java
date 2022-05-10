package service;

import model.Cuenta;
import repository.CuentaRepository;
import java.util.List;

public class CuentaService {

    //Utilizar Singleton de Cuenta Repository para hacer uso de sus métodos e implementar todos los métodos faltantes
    CuentaRepository cuentaRepository;

    public CuentaService() {
        cuentaRepository = CuentaRepository.getInstance();
    }

    public void depositar(int index, double monto){
        Cuenta cuenta = cuentaRepository.obtenerCuentaPorIndice(index);
        double currentBalance = cuenta.getMonto();
        double balanceAfterDeposit = currentBalance + monto;
        cuentaRepository.actualizarMontoDeCuenta(index, balanceAfterDeposit);
    }

    public void retirar(int index, double monto,double comision){
        retirar(index, monto + comision);
    }

    public void retirar(int index, double monto){
        Cuenta cuenta = cuentaRepository.obtenerCuentaPorIndice(index);
        double montoActual = cuenta.getMonto();
        double nuevoMonto = montoActual - monto;
        if(nuevoMonto < 0)
            System.out.println("No se puede retirar esa cantidad");
        else{
            cuentaRepository.actualizarMontoDeCuenta(index, nuevoMonto);
        }
    }

    public Cuenta agregarCuenta(String nombre, String banco, String numeroCuenta){
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setBanco(banco);
        nuevaCuenta.setNombre(nombre);
        nuevaCuenta.setNumeroDeCuenta(numeroCuenta);
        cuentaRepository.agregarCuenta(nuevaCuenta);
        return nuevaCuenta;
    }

    public int getIndiceCuenta(String numeroDeCuenta) {
        return cuentaRepository.obtenerIndicePorNumeroCuenta(numeroDeCuenta);
    }

    public Cuenta getCuenta(int indice) {
        return cuentaRepository.obtenerCuentaPorIndice(indice);
    }

    public int countCuentas() {
        return cuentaRepository.countCuentas();
    }

    public String[] getCuentas(){
        return cuentaRepository.getCuentas();
    }


}
