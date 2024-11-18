package com.ondc.ulip.Controller;

import com.ondc.ulip.DTO.SarathiRequest;
import com.ondc.ulip.Service.UlipIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sarathi")
public class ULIPController {

    @Autowired
    private UlipIntegrationService ulipService;

    @PostMapping("/validateDL")
    public ResponseEntity<String> validateDrivingLicense(@RequestBody SarathiRequest request) {
        // TODO validate request
        String result = ulipService.postSarathiDetails(request);
        // TODO validate response
        return ResponseEntity.ok(result);
    }
}
