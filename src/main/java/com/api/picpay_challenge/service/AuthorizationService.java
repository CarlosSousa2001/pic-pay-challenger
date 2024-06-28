package com.api.picpay_challenge.service;

import com.api.picpay_challenge.client.AuthorizationClient;
import com.api.picpay_challenge.dto.TransferDTO;
import com.api.picpay_challenge.entities.Transfer;
import com.api.picpay_challenge.exceptions.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean IsAuthorized(TransferDTO transfer){

        var resp = authorizationClient.isAuthorized();

        if(resp.getStatusCode().isError()){
                throw  new PicPayException();
        }

        return resp.getBody().authorized();
    }
}
