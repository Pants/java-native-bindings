package wtf.pants.bindings.util;

public class StringUtils {
    /**
     * Converts camelCase to snake_case
     *
     * @param camelCase A camelCase string
     * @return Returns camelCase as snake_case
     */
    public static String camelToSnake(final String camelCase) {
        final StringBuilder snake_case = new StringBuilder();

        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            if (i != 0 && Character.isUpperCase(c)) {
                snake_case.append("_").append(Character.toLowerCase(c));
            } else {
                snake_case.append(Character.toLowerCase(c));
            }
        }

        return snake_case.toString();
    }

    /**
     * Converts a bytecode type into a JNI type (eg: jint, jlong)
     * In the case of 'V' (void), just return void.
     *
     * @param bytecodeType The type to convert (eg: F, Z, D)
     * @return Returns JNI type as a string
     */
    public static String getJNITypeFromBytecode(String bytecodeType) {
        switch (bytecodeType.charAt(0)) {
            case 'Z':
                return "jboolean";
            case 'B':
                return "jbyte";
            case 'C':
                return "jchar";
            case 'S':
                return "jshort";
            case 'I':
                return "jint";
            case 'J':
                return "jlong";
            case 'F':
                return "jfloat";
            case 'D':
                return "jdouble";
            case '[':
                return getJNITypeFromBytecode(bytecodeType.substring(1)) + "Array";
            case 'V':
                return "void";
            case 'L':
            default:
                return "jobject";
        }
    }
}
