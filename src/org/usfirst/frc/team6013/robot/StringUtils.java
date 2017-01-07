package org.usfirst.frc.team6013.robot;

public class StringUtils {
	public static final String TODO_CRLF = "\n\r\000";                // String Constants.
    public static final String TODO_SPACES = "          ";
    public static final String TODO_SPACES_21 = "                     "; // For diagnostic console.
    public static final String TODO_ZEROS = "0000000000";
    public static final String TODO_TRUE  = "True ";
    public static final String TODO_FALSE = "False";
    
    // Formatted String manipulation functions
    // Limitations for all routines is 10.10 (limited by the constants "spaces" and "zeros")
    public static String format(int i, int w) {
        String s;
        s = String.valueOf(i);
        if(i<0){
        s ="-"+ s;
        }
        s = TODO_SPACES + s.trim();
        return s.substring(s.length() - w + 1);
    } // private String format(int

    public static String format(long l, int w) {
        String s;

        s = String.valueOf(l);
        s = TODO_SPACES + s.trim();
        return s.substring(s.length() - w + 1);
    } // private String format(long

    public static String format(float f, int w, int d) {
        long l;
        String s, t;
        double ff;

        ff = Math.abs(f);
        l = (long) ff;                          // Truncate to integer.
        s = String.valueOf(l);
        s = s.trim();
        if (f < 0) {
            s = "-" + s;
        }
        s = TODO_SPACES + s;
        s = s.substring(s.length() - w);

        
        l = (long) ((ff - (double) l) * pow10(d));  // Make the fraction an integer.
        t = String.valueOf(l);
        while (t.length() < d) {
            t = "0" + t;
        }
        t = t.trim() + TODO_ZEROS;
        t = t.substring(0, d);

        return s + "." + t;
    } // private String format(float

    public static String format(double f, int w, int d) {
        long l;
        String s, t;
        double ff;

        ff = Math.abs(f);
        l = (long) ff;                              // Truncate to integer.
        s = String.valueOf(l);
        s = s.trim();
        if (f < 0) {
            s = "-" + s;
        }
        s = TODO_SPACES + s;
        s = s.substring(s.length() - w);

        l = (long) ((ff - (double) l) * pow10(d));  // Make the fraction an integer.
        t = String.valueOf(l);
        while (t.length() < d) {
            t = "0" + t;
        }
        t = t.trim() + TODO_ZEROS;
        t = t.substring(0, d);

        return s + "." + t;
    } // private String format(double

    public static String format (boolean b) {
        if (b) {
            return TODO_TRUE;
        } else {
            return TODO_FALSE;
        }
    } // private String format(int
    
    public static double pow10(int d) {
        double r = 1;
        for (int i = 0; i < d; ++i) {
            r = r * 10.0;
        }
        return r;
    }
} // public class StringUtils {

