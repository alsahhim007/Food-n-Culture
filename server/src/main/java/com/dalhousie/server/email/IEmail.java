package com.dalhousie.server.email;

import com.dalhousie.server.model.EmailDetails;

public interface IEmail {
    boolean sendMail(EmailDetails emailDetails);
}
