package Validator;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator implements Validator<NewUserInfo> {

    int mayusculas = 0;
    int especiales = 0;
    int numericos = 0;

    @Override
    public List<String> validate(NewUserInfo info) {
        List<String> errors = new ArrayList<>();

        String password = info.getPassword();

        if (password.isEmpty()) {
            errors.add("La contraseña está vacía");
        } else {
            if (password.length() < 8) {
                errors.add("La contraseña debe contener mínimo 8 caracteres");
            } 
            if (enforcedPassword(password) == false) {
                errors.add("La contraseña debe contener al menos una mayúscula"
                        + "dos caracteres numéricos y dos caracteres especiales");
            }
        }
        return errors;
    }

    private boolean enforcedPassword(String password) {

        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                if (Character.isUpperCase(password.charAt(i))) {
                    mayusculas++;
                }
            } else {
                if (Character.isDigit(password.charAt(i))) {
                    numericos++;
                } else {
                    especiales++;
                }
            }
        }      
        if (mayusculas >= 1 && numericos >= 2 && especiales >= 2) {
            return true;
        }
        return false;
    }

}
