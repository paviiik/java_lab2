package com.phone.service;


import com.phone.dto.CountryDtoRequest;
import com.phone.dto.CountryDtoResponse;
import com.phone.mapper.CountryMapper;
import com.phone.model.Country;
import com.phone.repository.CountryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepository repository;
    private final CountryMapper mapper;

    public CountryService(CountryRepository repository, CountryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CountryDtoResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public CountryDtoResponse getByCode(String code) {
        return repository.findById(code).map(mapper::toDto).orElse(null);
    }

    public CountryDtoResponse save(CountryDtoRequest request) {
        if (repository.existsById(request.getCode())) {
            throw new IllegalArgumentException("Страна с кодом '" + request.getCode() + "' уже существует");
        }
        Country saved = repository.save(mapper.toEntity(request));
        return mapper.toDto(saved);
    }

    public CountryDtoResponse update(String code, CountryDtoRequest request) {
        return repository.findById(code)
                .map(existing -> {
                    existing.setName(request.getName());
                    existing.setPhoneCode(request.getPhoneCode());
                    return mapper.toDto(repository.save(existing));
                })
                .orElse(null);
    }

    public void delete(String code) {
        repository.deleteById(code);
    }

    public CountryDtoResponse lookup(String value) {
        return repository.findAll().stream()
                .filter(c -> c.getCode().equalsIgnoreCase(value)
                        || c.getName().equalsIgnoreCase(value)
                        || c.getPhoneCode().equals(value))
                .map(mapper::toDto)
                .findFirst()
                .orElse(null);
    }
}
