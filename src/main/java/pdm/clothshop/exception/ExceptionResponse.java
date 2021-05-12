package pdm.clothshop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    public static enum ERROR_CODE {
        BRAND_NOT_EXISTS(4041),COLOR_NOT_EXISTS(4042),
        PRODUCT_NOT_EXISTS(4043),CATALOG_IS_EMPTY(4044),
        PRODUCT_NAME_CONFLICT(40931),PRODUCT_IMAGE_FILENAME_CONFILCT(40932),
        FILE_EMPTY(4045);
        private int value;

        ERROR_CODE(int value) {
            this.value = value;
        }
    }
    private ERROR_CODE errorCode;
    private String message;
    private LocalDateTime dateTime;

//    public ExceptionResponse(ERROR_CODE errorCode, String message, LocalDateTime dateTime) {
//        this.errorCode = errorCode;
//        this.message = message;
//        this.dateTime = dateTime;
//    }
}
