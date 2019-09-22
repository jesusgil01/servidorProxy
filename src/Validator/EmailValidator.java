/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.util.ArrayList;
import java.util.List;

public class EmailValidator implements Validator<NewUserInfo> {

    int i = 0;
    
    @Override
    public List<String> validate(NewUserInfo info) {
        List<String> errors = new ArrayList<>();
        String email = info.getEmail();
        for (int j = 0; j < email.length(); j++) {
            if (email.charAt(i) == '@') {
                i++;
            }
        }
        if (i != 1) {
            errors.add("El email no es válido");
        }

        return errors;
    }


}
