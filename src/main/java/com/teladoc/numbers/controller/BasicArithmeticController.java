package com.teladoc.numbers.controller;

import com.teladoc.numbers.api.request.BasicArithmeticRequestDTO;
import com.teladoc.numbers.api.response.BasicArithmeticResponseDTO;
import com.teladoc.numbers.service.BasicArithmeticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <h2>BasicArithmeticOperationController</h2>
 * <p>
 * REST Controller for Basic Arithmetic Operations
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/basic/arithmetic")
public class BasicArithmeticController {

    private final BasicArithmeticService basicArithmeticService;

    @Autowired
    public BasicArithmeticController(final BasicArithmeticService basicArithmeticService) {

        this.basicArithmeticService = basicArithmeticService;
    }

    @PostMapping("/sum")
    public ResponseEntity<BasicArithmeticResponseDTO> sum(final @RequestBody @Valid BasicArithmeticRequestDTO requestDTO) {
        final BasicArithmeticResponseDTO result = this.basicArithmeticService.sum(requestDTO);
        return ResponseEntity.ok(result);
    }

}
