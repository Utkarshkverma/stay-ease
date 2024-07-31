package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.MyUsers;
import com.vermau2k01.stay_ease.request.LoginForm;
import com.vermau2k01.stay_ease.request.RegistrationRequest;
import com.vermau2k01.stay_ease.response.RegistrationResponse;

public interface IRegistrationService {

    MyUsers registerUser(RegistrationRequest registrationRequest);
    RegistrationResponse login(LoginForm loginRequest);
}
