package dev.fleet.mapper;

import dev.fleet.dto.request.CreateTransportRequest;
import dev.fleet.dto.response.TransportRequestResponse;
import dev.fleet.entity.TransportRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransportRequestMapper {

    TransportRequest toEntity(CreateTransportRequest request);

    TransportRequestResponse toResponse(TransportRequest transportRequest);

    List<TransportRequestResponse> toResponseList(List<TransportRequest> transportRequests);
}