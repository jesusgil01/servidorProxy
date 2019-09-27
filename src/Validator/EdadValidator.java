/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jesus
 */
public class EdadValidator implements Validator<NewUserInfo> {

    @Override
    public List<String> validate(NewUserInfo info) {
        List<String> errors = new ArrayList<>();
        int actual = yearActual();
        int nacimiento = obtenerYear(info.getFechanaci());
        int resta = actual - nacimiento;
        if (resta < 18) {
            errors.add("El usuario tiene que ser mayor de 18 años.");
        }

        return errors;
    }

    private int yearActual() {
        int actual = 0;
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
        String convertido = fecha.format(date).substring(0,4);
        actual = Integer.parseInt(convertido);
        return actual;
    }

    private int obtenerYear(String fechaNacimiento) {
        int year = 0;
        String yearString = fechaNacimiento.substring(0, 4);
        year = Integer.parseInt(yearString);
        return year;
    }

}
