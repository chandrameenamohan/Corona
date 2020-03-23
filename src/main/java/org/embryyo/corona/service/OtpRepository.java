package org.embryyo.corona.service;

import org.springframework.data.repository.CrudRepository;

public interface OtpRepository extends CrudRepository<Otp, Integer> {

    public Otp findByMobileNumber(String mobileNumber);
}
