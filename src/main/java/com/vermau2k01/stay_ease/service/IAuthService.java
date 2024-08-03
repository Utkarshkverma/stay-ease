package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.request.AuthenticationRequest;
import com.vermau2k01.stay_ease.request.RegistrationRequest;
import com.vermau2k01.stay_ease.response.AuthenticationResponse;

public interface IAuthService {

    void register(RegistrationRequest request);
    AuthenticationResponse login(AuthenticationRequest request);

}
