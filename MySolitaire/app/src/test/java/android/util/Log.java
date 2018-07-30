package android.util;

/**
 * Mock for Android calls to the Log.x() throughout the app.
 */
public class Log {
    
    private final static int MOCK_RETURN_VALUE = 0;
    
    public static int v(String tag, String msg) {

        return MOCK_RETURN_VALUE;
    }

    public static int v(String tag, String msg, Throwable tr) {
        return MOCK_RETURN_VALUE;
    }

    public static int d(String tag, String msg) {
        return MOCK_RETURN_VALUE;
    }

    public static int d(String tag, String msg, Throwable tr) {
        return MOCK_RETURN_VALUE;
    }

    public static int i(String tag, String msg) {

        return MOCK_RETURN_VALUE;
    }

    public static int i(String tag, String msg, Throwable tr) {
        return MOCK_RETURN_VALUE;
    }

    public static int w(String tag, String msg) {
        return MOCK_RETURN_VALUE;
    }

    public static int w(String tag, String msg, Throwable tr) {
        return MOCK_RETURN_VALUE;
    }

    public static int w(String tag, Throwable tr) {
        return MOCK_RETURN_VALUE;
    }

    public static int e(String tag, String msg) {
        return MOCK_RETURN_VALUE;
    }

    public static int e(String tag, String msg, Throwable tr) {
        return MOCK_RETURN_VALUE;
    }

    public static int wtf(String tag, String msg) {
        
        return MOCK_RETURN_VALUE;
    }

    public static int wtf(String tag, Throwable tr) {
        return MOCK_RETURN_VALUE;
    }

    public static int wtf(String tag, String msg, Throwable tr) {
        return MOCK_RETURN_VALUE;
    }
}
