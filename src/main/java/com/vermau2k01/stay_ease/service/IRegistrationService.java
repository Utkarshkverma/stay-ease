package com.vermau2k01.stay_ease.service;

import com.vermau2k01.stay_ease.entity.MyUsers;
import com.vermau2k01.stay_ease.request.RegistrationRequest;

public interface IRegistrationService {

    MyUsers registerUser(RegistrationRequest registrationRequest);
}
