public class Util {
    public static int[] arrayStringToInt(String[] arr) throws NumberFormatException {
        int[] ret = new int[arr.length];
        try {
            for (int i = 0; i < arr.length; i++)
                ret[i] = Integer.parseInt(arr[i]);
        } catch (NumberFormatException exception) {
            throw exception;
        }

        return ret;
    }
}
