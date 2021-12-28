package org.traccar.helper;

public class AdflotUtil {

    public static final String GALILEO_PROTOCOL = "galileo";

    public static String getVIN(Integer a, Integer b, Integer c, Integer d, Integer e) {
        StringBuilder sb = new StringBuilder();

        sb.append(hexToAscii(Integer.toHexString(a)));
        sb.append(hexToAscii(Integer.toHexString(b)));
        sb.append(hexToAscii(Integer.toHexString(c)));
        sb.append(hexToAscii(Integer.toHexString(d)));
        sb.append(hexToAscii(Integer.toHexString(e)));

        return sb.toString();
    }

    public static String getVIN(Long a, Long b, Long c, Long d, Long e) {
        StringBuilder sb = new StringBuilder();

        sb.append(hexToAscii(Long.toHexString(a)));
        sb.append(hexToAscii(Long.toHexString(b)));
        sb.append(hexToAscii(Long.toHexString(c)));
        sb.append(hexToAscii(Long.toHexString(d)));
        sb.append(hexToAscii(Long.toHexString(e)));

        return sb.toString();
    }

    public static double getKphDesdeNudos(double nudos) {
        return UnitsConverter.kphFromKnots(nudos);
    }

    public static int getTemperaturaMotor(String protocolo, Integer temperatura) {

        int temperaturaCalculada = 0;

        switch (protocolo) {
            case GALILEO_PROTOCOL:
                temperaturaCalculada = temperatura - 40;
                break;
        }
        return temperaturaCalculada;
    }

    public static double getLitrosCombustible(String protocolo, Integer litros) {

        double litrosCalculados = 0.0;

        switch (protocolo) {
            case GALILEO_PROTOCOL:
                litrosCalculados = litros / 2;
                break;
        }
        return litrosCalculados;
    }


    public static double getAceleracionLineal(String protocolo, Long aceleraciones) {
        double aceleracion = 0;

        switch (protocolo) {
            case GALILEO_PROTOCOL:
                aceleracion = getAceleracion(aceleraciones, 7).doubleValue() / 100d;
                break;
        }
        return aceleracion;
    }

    public static double getAceleracionCentripeta(String protocolo, Long aceleraciones) {
        double aceleracion = 0;

        switch (protocolo) {
            case GALILEO_PROTOCOL:
                aceleracion = getAceleracion(aceleraciones, 3).doubleValue() / 100d;
                break;
        }
        return aceleracion;
    }


    public static double getAceleracionFrenado(String protocolo, Long aceleraciones) {
        double aceleracion = 0;

        switch (protocolo) {
            case GALILEO_PROTOCOL:
                aceleracion = getAceleracion(aceleraciones, 5).doubleValue() / 100d;
                break;
        }
        return aceleracion;
    }

    public static double getAceleracionHorizontal(String protocolo, Long aceleraciones) {
        double aceleracion = 0;

        switch (protocolo) {
            case GALILEO_PROTOCOL:
                aceleracion = getAceleracion(aceleraciones, 1).doubleValue() / 100d;
                break;
        }
        return aceleracion;

    }

    public static double getAceleracionLineal(String protocolo, Integer aceleraciones) {
        return getAceleracionLineal(protocolo, aceleraciones.longValue());
    }

    public static double getAceleracionCentripeta(String protocolo, Integer aceleraciones) {
        return getAceleracionCentripeta(protocolo, aceleraciones.longValue());
    }

    public static double getAceleracionFrenado(String protocolo, Integer aceleraciones) {
        return getAceleracionFrenado(protocolo, aceleraciones.longValue());
    }

    public static double getAceleracionHorizontal(String protocolo, Integer aceleraciones) {
        return getAceleracionHorizontal(protocolo, aceleraciones.longValue());
    }

    private static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        output.reverse();

        return output.toString();
    }

    private static Integer getAceleracion(Long aceleraciones, Integer inicio) {
        return hexToInteger(padIzquierdaCeros(longToHex(aceleraciones), 8), inicio);
    }

    private static String longToHex(Long valor) {
        return Long.toHexString(valor);
    }

    private static Integer hexToInteger(String hex, Integer posicion) {
        return Integer.parseInt(hex.substring(posicion - 1, posicion + 1), 16);
    }

    private static String padIzquierdaCeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }
}