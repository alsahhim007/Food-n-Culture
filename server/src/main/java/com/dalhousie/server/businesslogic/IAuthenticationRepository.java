package com.dalhousie.server.businesslogic;

import java.util.List;

import com.dalhousie.server.model.Authentication;

public interface IAuthenticationRepository extends ICrudRepository <Authentication, Integer> {
    public List<Authentication> getOTPByUserId(Integer userId);
}
