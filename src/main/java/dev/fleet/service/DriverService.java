package dev.fleet.service;

import dev.fleet.dto.request.CreateDriverRequest;
import dev.fleet.dto.request.UpdateDriverRequest;
import dev.fleet.dto.response.DriverResponse;
import dev.fleet.entity.Driver;
import dev.fleet.exception.DriverNotFoundException;
import dev.fleet.mapper.DriverMapper;
import dev.fleet.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Transactional
    public DriverResponse createDriver(CreateDriverRequest request) {
        Driver driver = driverMapper.toEntity(request);
        Driver savedDriver = driverRepository.save(driver);

        return driverMapper.toResponse(savedDriver);
    }

    @Transactional
    public DriverResponse updateDriver(Long id, UpdateDriverRequest request) {
        Driver driver = getDriver(id);

        driver.update(
                request.firstName(),
                request.lastName(),
                request.licenseNumber(),
                request.isActive()
        );

        return driverMapper.toResponse(driver);
    }

    @Transactional
    public void deleteDriver(Long id) {
        Driver driver = getDriver(id);

        driverRepository.delete(driver);
    }

    private Driver getDriver(Long id) {
        return driverRepository.findById(id).orElseThrow(() -> new DriverNotFoundException(id));
    }
}
