package com.maybank.exam.service.service.impl;
import org.apache.commons.codec.binary.Base64;

import com.maybank.exam.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public Boolean verifyUserAuthentication(String authString) {
        //Bearer Token dWFQUmlVeFJJOEVHbm04eUt1MUtYckIyaVlPa2NueFA6dzdwR2pPSE80R3B1cWF6ZA==
        byte[] byteArray = Base64.decodeBase64(authString.getBytes());
        String decodedString = new String(byteArray);
        if (decodedString.equalsIgnoreCase("uaPRiUxRI8EGnm8yKu1KXrB2iYOkcnxP:w7pGjOHO4Gpuqazd")) {
            return true;
        } else {
            return false;
        }
    }
}
