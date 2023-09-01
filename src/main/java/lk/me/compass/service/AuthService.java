package lk.me.compass.service;

import lk.me.compass.dto.req.AuthReqDTO;
import lk.me.compass.dto.req.UserDTO;
import lk.me.compass.util.ResponseUtil;

public interface AuthService {

    //login verification
    public ResponseUtil authenticationLogin(AuthReqDTO authReqDTO) throws Exception;

    //save new user
    public ResponseUtil saveUser(UserDTO userDTO) throws Exception;
    
}
