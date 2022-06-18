package developer.com.mr.olx.abstractClasses;

public abstract class AddProduct<T> {


    private AddProduct() {
    }

    public static final class Success<T> extends AddProduct<T> {
        public T data;

        public Success(T data) {
            this.data = data;
        }
    }

    public static final class Error<T> extends AddProduct<T> {
        public Exception exception;

        public Error(Exception exception) {
            this.exception = exception;
        }
    }


}
