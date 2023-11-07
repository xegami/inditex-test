package com.enmanuel.inditex.mapper;

import com.enmanuel.inditex.dto.PriceDTO;
import com.enmanuel.inditex.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    PriceDTO toDTO(Price price);
}
