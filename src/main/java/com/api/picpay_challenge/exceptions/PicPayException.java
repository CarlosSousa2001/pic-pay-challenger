package com.api.picpay_challenge.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PicPayException extends RuntimeException{


    public ProblemDetail toProblemDetails(){
            var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

            pb.setTitle("Picpay internal server Error");

            return pb;
    }
}
