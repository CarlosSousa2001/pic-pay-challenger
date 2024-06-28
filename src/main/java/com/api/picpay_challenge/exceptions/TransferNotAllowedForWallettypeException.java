package com.api.picpay_challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedForWallettypeException extends PicPayException{

    @Override
    public ProblemDetail toProblemDetails() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("This wallet type is not allowed to transfer");

        return pb;
    }
}
