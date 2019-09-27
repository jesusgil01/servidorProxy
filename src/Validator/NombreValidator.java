/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jesus
 */
public class NombreValidator implements Validator<NewUserInfo>{

    @Override
    public List<String> validate(NewUserInfo info) {
        List<String> errors = new ArrayList<>();
        String nombre = info.getNombre();
        String apellido = info.getApellido();
        String username = info.getUsername();
        if (nombre.isEmpty()) {
            errors.add("No pusiste nombre carnal, pero que tal la telenovela?");
        }
        if (apellido.isEmpty()) {
            errors.add("Se te fué el apellido o te estas haciendo pendejo?");
        }
        if (username.isEmpty()) {
            errors.add("Dejaste el nombre de usuario en blanco mijo");
        }
        return errors;
    }

}
