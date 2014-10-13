/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("Validator.RutValidator")
public class RutValidator implements Validator {

    private String rut;

    public RutValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        rut = value.toString();
        if (!validarRut(rut)) {

            FacesMessage msg = new FacesMessage("Rut inválido.", "Invalid Rut format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public static boolean validarRut(String rut) {
        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replaceAll("\\.", "");
            rut = rut.replaceAll("\\-", "");

            if (rut.endsWith("K")) {
                rut = rut.replace("K", "11");
                int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 2));
                int dv = Integer.parseInt(rut.substring(rut.length() - 2, rut.length()));
                int m = 0, s = 1;
                for (; rutAux != 0; rutAux /= 10) {
                    s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
                }
                if (dv == 11) {
                    validacion = true;
                }
            } else {
                int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
                char dv = rut.charAt(rut.length() - 1);
                int m = 0, s = 1;
                for (; rutAux != 0; rutAux /= 10) {
                    s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
                }
                if (dv == (char) (s != 0 ? s + 47 : 75)) {
                    validacion = true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return validacion;
    }
}
