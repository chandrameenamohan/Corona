package org.embryyo.corona.service.repo;

import org.embryyo.corona.service.model.Otp;
import org.springframework.data.repository.CrudRepository;

public interface OtpRepository extends CrudRepository<Otp, Integer> {
    public Otp findByMobileNumber(String mobileNumber);
}
