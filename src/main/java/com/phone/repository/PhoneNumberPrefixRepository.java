package com.phone.repository;

import com.phone.model.PhoneNumberPrefix;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhoneNumberPrefixRepository extends JpaRepository<PhoneNumberPrefix, Long> {
    List<PhoneNumberPrefix> findByCountryCode(String countryCode);
    boolean existsByPrefixAndCountryCode(String prefix, String countryCode);
}
