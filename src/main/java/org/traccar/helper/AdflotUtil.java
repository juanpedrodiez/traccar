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

    private static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        output.reverse();

        return output.toString();
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

}