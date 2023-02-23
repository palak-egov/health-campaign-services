package org.egov.facility.validator;

import org.egov.common.models.Error;
import org.egov.facility.helper.FacilityBulkRequestTestBuilder;
import org.egov.facility.helper.FacilityTestBuilder;
import org.egov.facility.repository.FacilityRepository;
import org.egov.facility.web.models.Facility;
import org.egov.facility.web.models.FacilityBulkRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RowVersionValidatorTest {

    @InjectMocks
    private FRowVersionValidator fRowVersionValidator;

    @Mock
    private FacilityRepository facilityRepository;


    @Test
    @DisplayName("should add to error if row version mismatch found")
    void shouldAddToErrorDetailsIfRowVersionMismatchFound() {
        FacilityBulkRequest request = FacilityBulkRequestTestBuilder.builder().withFacilityId("some-id").withRequestInfo().build();
        request.getFacilities().get(0).setRowVersion(2);
        when(facilityRepository.findById(anyList(), anyBoolean(), anyString()))
                .thenReturn(Collections.singletonList(FacilityTestBuilder.builder().withFacility().withId("some-id").build()));

        Map<Facility, List<Error>> errorDetailsMap = fRowVersionValidator.validate(request);

        assertEquals(1, errorDetailsMap.size());
    }

    @Test
    @DisplayName("should not add to error if row version is similar")
    void shouldNotAddToErrorDetailsIfRowVersionSimilar() {
        FacilityBulkRequest request = FacilityBulkRequestTestBuilder.builder().withFacilityId("some-id").withRequestInfo().build();
        when(facilityRepository.findById(anyList(), anyBoolean(), anyString()))
                .thenReturn(Collections.singletonList(FacilityTestBuilder.builder().withFacility().withId("some-id").build()));

        Map<Facility, List<Error>> errorDetailsMap = fRowVersionValidator.validate(request);

        assertEquals(0, errorDetailsMap.size());
    }
}
