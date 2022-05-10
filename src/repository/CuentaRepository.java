package repository;

import model.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class CuentaRepository {
    
    private static CuentaRepository instance;
    private List<Cuenta> cuentas;


    private CuentaRepository(){
        cuentas = new ArrayList<>();
    }
    
    public static CuentaRepository getInstance() {
        if(instance == null)
            instance = new CuentaRepository();
        return instance;
    }

    public String[] getCuentas(){
        int cuentasCount = countCuentas();
        String[] cuentasArray = new String[cuentasCount];
        for(int i = 0; i<cuentasCount; i++){
            cuentasArray[i]=(cuentas.get(i).getNumeroDeCuenta());
        }
        return cuentasArray;
    }
    

    public Cuenta obtenerCuentaPorIndice(int index){
        return cuentas.get(index);
    }

    public void agregarCuenta(Cuenta c){
        cuentas.add(c);
    }

    public Cuenta obtenerCuentaPorNumeroCuenta(String numeroDeCuenta){
        for (Cuenta c:
             cuentas) {
            if(c.getNumeroDeCuenta().equalsIgnoreCase(numeroDeCuenta)){
                return c;
            }
        }
        return null;
    }

    public void actualizarMontoDeCuenta(int index,double monto){
        Cuenta cuenta = instance.obtenerCuentaPorIndice(index);
        cuenta.setMonto(monto);
    }

    public int countCuentas() {
        return cuentas.size();
    }
    
    public int obtenerIndicePorNumeroCuenta(String numeroDeCuenta) {
        int i = 0;
        for (Cuenta c: cuentas) {
            if(c.getNumeroDeCuenta().equalsIgnoreCase(numeroDeCuenta)){
                return i;
            }
            i++;
        }
        return -1;
    }


}
