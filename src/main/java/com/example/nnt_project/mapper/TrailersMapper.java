package com.example.nnt_project.mapper;

import com.example.nnt_project.entity.Trailers;
import com.example.nnt_project.payload.TrailersDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrailersMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Trailers toTrailers(TrailersDto trailersDto){
        return modelMapper.map(trailersDto, Trailers.class);
    }

    public TrailersDto toTrailersDto(Trailers trailers){
        return modelMapper.map(trailers, TrailersDto.class);
    }

    public List<TrailersDto> toTrailersDto(List<Trailers> trailersList){
        return trailersList.stream()
                .map(this::toTrailersDto)
                .collect(Collectors.toList());
    }
    public void updateAddressFromDto(TrailersDto trailersDto, Trailers trailers) {
        modelMapper.map(trailersDto, trailers);
    }
}
