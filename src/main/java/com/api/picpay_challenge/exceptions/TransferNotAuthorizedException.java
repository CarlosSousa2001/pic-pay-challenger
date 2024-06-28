package com.api.picpay_challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException  extends PicPayException{

    @Override
    public ProblemDetail toProblemDetails() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Transfer not authorized");

        return pb;
    }
}
