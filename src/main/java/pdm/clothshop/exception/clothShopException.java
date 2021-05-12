package pdm.clothshop.exception;

public class clothShopException extends RuntimeException{

    ExceptionResponse.ERROR_CODE errorCode;
    public clothShopException(ExceptionResponse.ERROR_CODE errorCode, String s) {
        super(s);
        this.errorCode = errorCode;
    }

    public ExceptionResponse.ERROR_CODE getErrorCode() {
        return this.errorCode;
    }
}
