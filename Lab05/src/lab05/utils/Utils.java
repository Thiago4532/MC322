package lab05.utils;

import java.util.UUID;

public class Utils {
    public static int generateRandomId() {
        // Usar esse método gera um ID positivo, usar a funçao randomUUID utiliza números aleatórios,
        // seguros, embora pra esse problema isso não faça diferença.
        return Math.abs(UUID.randomUUID().hashCode());
    }
}
