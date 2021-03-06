package com.mercadolibre.control.controller;

import com.mercadolibre.control.dto.StatisticDTO;
import com.mercadolibre.control.entity.Statistic;
import com.mercadolibre.control.service.StatisticService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: brianfroschauer
 * Date: 19/10/2019
 */
@CrossOrigin(origins = {
        "http://localhost:8080",
        "http://localhost:4200"
})
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    private final StatisticService statisticService;
    private final ModelMapper mapper;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
        this.mapper = new ModelMapper();
    }

    @GetMapping
    public ResponseEntity<List<StatisticDTO>> findAll() {

        final List<Statistic> statistics = statisticService.findAll();

        final List<StatisticDTO> response = statistics.stream()
                .map(statistic -> mapper.map(statistic, StatisticDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StatisticDTO> create(@RequestBody @Valid StatisticDTO statisticDTO) {

        final Statistic statistic = statisticService.create(mapper.map(statisticDTO, Statistic.class));

        final StatisticDTO response = mapper.map(statistic, StatisticDTO.class);

        return ResponseEntity.ok(response);
    }
}
