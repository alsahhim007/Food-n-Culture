package com.dalhousie.foodnculture.apifacade;

import java.util.List;

import com.dalhousie.foodnculture.models.Authentication;

public interface IAuthenticationOperation extends ICrudOperation<Authentication, Integer> {
    public List<Authentication> getOTPByUserId(Integer userId);
}
