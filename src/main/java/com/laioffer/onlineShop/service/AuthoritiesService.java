package com.laioffer.onlineShop.service;

import com.laioffer.onlineShop.entity.Authorities;
import com.laioffer.onlineShop.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    public Authorities getAuthoritiesByEmailId(String emailId) {
        return authoritiesRepository.findById(emailId).get();
    }

    public Boolean saveAnAuthorities(Authorities authorities) {
        if(isEmailIdAlreadyRegistered(authorities)) {
            return false;
        }
        authoritiesRepository.save(authorities);
        return true;
    }

    public Boolean isEmailIdAlreadyRegistered(Authorities authorities) {
        return authoritiesRepository.findById(authorities.getEmailId()).isPresent();
    }

    public Authorities updateAnAuthorities(Authorities authorities, String level) {
        Authorities authoritiesToUpdate = authoritiesRepository.getOne(authorities.getEmailId());
        authoritiesToUpdate.setAuthorities(level);
        authoritiesRepository.save(authoritiesToUpdate);
        return getAuthoritiesByEmailId(authorities.getEmailId());
    }

    public String getAuthoritiesLevel(String emailId) {
        return getAuthoritiesByEmailId(emailId).getAuthorities();
    }

}
