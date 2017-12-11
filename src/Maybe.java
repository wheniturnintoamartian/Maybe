import java.util.function.Function;

public abstract class Maybe<T> {

    public static <T> Maybe<T> of(T t) {
        if (t == null) {
            return createNothing();
        } else {
            return createJust(t);
        }
    }

    private static <T> Maybe<T> createJust(T t) {
        return new Just<>(t);
    }

    private static <T> Maybe<T> createNothing() {
        return new Nothing<>();
    }

    public static class Nothing<T> extends Maybe<T> {

        private Nothing() {}

        @Override
        public <S> Maybe<S> bind(Function<T, Maybe<S>> fromTToMaybeS) {
            return new Nothing<>();
        }

        @Override
        public T val() throws Exception {
            throw new Exception();
        }

    }

    public static class Just<T> extends Maybe<T> {

        private final T value;

        private Just(T t) {
            value = t;
        }

        @Override
        public <S> Maybe<S> bind(Function<T, Maybe<S>> fromTToMaybeS) {
            return fromTToMaybeS.apply(value);
        }

        @Override
        public T val() {
            return value;
        }


    }

    public abstract <S> Maybe<S> bind(Function<T, Maybe<S>> fromTToMaybeS);

    public abstract T val() throws Exception;

}
