package Validator;

import java.util.List;

public interface Validator<T> {

    List<String> validate(T info);
}
