package br.ufes.inf.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    public static String formataDataSimples(Date data) {
        DateFormat diaFormat = new SimpleDateFormat("dd/MM/yyyy");
        return diaFormat.format(data);
    }
    
    public static String formataHoraSimples(Date data) {
        DateFormat horarioFormat = new SimpleDateFormat("HH:mm");
        return horarioFormat.format(data);
    }
    
}
