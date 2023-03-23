package recursiontest;

public class Main {

    public static void main(String[] args) {
//        System.out.println( evalExp("14+4-3+10") );
//        System.out.println(match("??????snake", "rattle?????"));
        System.out.println(fib(6));
    }


    static int evalExp (String exp) {

        int plus = exp.lastIndexOf("+");
        int minus = exp.lastIndexOf("-");

        if (plus == -1 && minus == -1) {
            return Integer.parseInt(exp);
        }

        if (minus == -1 || plus > minus) {
            return evalExp(exp.substring(0, plus)) + Integer.parseInt(exp.substring(plus+1));
        }
        return evalExp(exp.substring(0, minus)) + Integer.parseInt(exp.substring(minus+1));

    }

    static boolean match(String a, String b) {

        a = a.toLowerCase();
        b = b.toLowerCase();

        if (a.length() != b.length()) return false;
        if (a.length() == 0) return true;

        if (a.charAt(0) == '?' || b.charAt(0) == '?') return match(a.substring(1), b.substring(1));
        return ( (a.charAt(0) == b.charAt(0)) && match(a.substring(1), b.substring(1)) );
    }

    static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }
}
