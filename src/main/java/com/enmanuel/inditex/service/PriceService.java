package com.enmanuel.inditex.service;

import com.enmanuel.inditex.dto.PriceDTO;
import com.enmanuel.inditex.entity.Brand;
import com.enmanuel.inditex.entity.Price;
import com.enmanuel.inditex.mapper.PriceMapper;
import com.enmanuel.inditex.repository.BrandRepository;
import com.enmanuel.inditex.repository.PriceRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final BrandRepository brandRepository;

    public PriceService(PriceRepository priceRepository, BrandRepository brandRepository) {
        this.priceRepository = priceRepository;
        this.brandRepository = brandRepository;
    }

    public Mono<PriceDTO> findPrice(Long brandId, Long productId, LocalDateTime date) {
        return priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        brandId, productId, date, date)
                .next()
                .map(PriceMapper.INSTANCE::toDTO)
                .switchIfEmpty(Mono.empty());
    }

    @EventListener(ApplicationReadyEvent.class)
    private void loadSampleData() {
        Brand brand = new Brand();
        brand.setName("ZARA");
        brandRepository.save(brand).block();

        Price price = new Price();
        price.setBrandId(1L);
        price.setProductId(35455L);
        price.setCurrency("EUR");
        price.setPriceList(1);
        price.setPrice(50.0);
        price.setPriority(1);
        price.setStartDate(LocalDateTime.parse("2023-11-07T20:00:00"));
        price.setEndDate(LocalDateTime.parse("2023-11-27T20:00:00"));
        priceRepository.save(price).block();
    }
}