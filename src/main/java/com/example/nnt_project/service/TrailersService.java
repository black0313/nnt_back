package com.example.nnt_project.service;

import com.example.nnt_project.entity.Trailers;
import com.example.nnt_project.mapper.TrailersMapper;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.TrailersDto;
import com.example.nnt_project.repository.TrailersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrailersService {

    private final TrailersMapper trailersMapper;
    private final TrailersRepository trailersRepository;

    public ApiResponse getTrailersById(UUID id) {
        Optional<Trailers> optionalTrailers = trailersRepository.findById(id);
        return optionalTrailers.map(trailers -> new ApiResponse("found", true, trailersMapper.toTrailersDto(trailers))).orElseGet(() -> new ApiResponse("not found"));
    }

    public ApiResponse saveTrailers(TrailersDto trailersDto) {
        trailersRepository.save(trailersMapper.toTrailers(trailersDto));
        return new ApiResponse("Successfully saved trailers", true);
    }

    public void deleteTrailers(UUID id) {
        trailersRepository.deleteById(id);
    }

    public ApiResponse getAllTrailers() {
        List<Trailers> all = trailersRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse("not found", false);
        }
        return new ApiResponse("found", true, trailersMapper.toTrailersDto(all));
    }

    public ApiResponse updateTrailers(UUID id, TrailersDto trailersDto) {
        Optional<Trailers> optionalTrailers = trailersRepository.findById(id);
        if (optionalTrailers.isEmpty()) {
            return new ApiResponse("not found", false);
        }
        Trailers trailers = optionalTrailers.get();
        trailersMapper.updateAddressFromDto(trailersDto, trailers);
        trailersRepository.save(trailers);
        return new ApiResponse("Successfully updated trailers", true);
    }
}
