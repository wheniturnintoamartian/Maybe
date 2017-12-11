public class Main {

    public static void main(String[] args) throws Exception {
        final B b1 = new B("asdf", 3);
        final B b2 = new B(null, null);
        final A a1 = new A(null, null);
        final A a2 = new A(b1, 3);
        final A a3 = new A(b1, 4);
        final Maybe<String> a = Maybe.of(a3).bind(x -> Maybe.of(x.getB())).bind(x -> Maybe.of(x.getC()));
        if (a instanceof Maybe.Nothing) {
            System.out.println("nothing");
        } else if (a instanceof Maybe.Just) {
            System.out.println(a.val());
        }
    }

}

class A {
    private final B b;
    private final Integer a;

    public A(B b, Integer a) {
        this.b = b;
        this.a = a;
    }

    public B getB() {
        return b;
    }
}

class B {
    private final String c;
    private final Integer d;

    B(String c, Integer d) {
        this.c = c;
        this.d = d;
    }

    public String getC() {
        return c;
    }
}