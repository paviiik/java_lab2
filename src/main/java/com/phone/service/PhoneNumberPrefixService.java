package com.phone.service;

import com.phone.dto.PrefixDtoRequest;
import com.phone.dto.PrefixDtoResponse;
import com.phone.mapper.PrefixMapper;
import com.phone.repository.PhoneNumberPrefixRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhoneNumberPrefixService {

    private final PhoneNumberPrefixRepository repository;
    private final PrefixMapper mapper;

    public PhoneNumberPrefixService(PhoneNumberPrefixRepository repository, PrefixMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PrefixDtoResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<PrefixDtoResponse> getByCountry(String countryCode) {
        return repository.findByCountryCode(countryCode)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
    public PrefixDtoResponse getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    public PrefixDtoResponse save(PrefixDtoRequest request) {
        if (repository.existsByPrefixAndCountryCode(request.getPrefix(), request.getCountryCode())) {
            throw new IllegalArgumentException("Префикс '" + request.getPrefix() + "' уже существует для страны '" + request.getCountryCode() + "'");
        }
        return mapper.toDto(repository.save(mapper.toEntity(request)));
    }

    public PrefixDtoResponse update(Long id, PrefixDtoRequest request) {
        return repository.findById(id).map(existing -> {
            existing.setPrefix(request.getPrefix());
            existing.setRegionName(request.getRegionName());
            return mapper.toDto(repository.save(existing));
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
