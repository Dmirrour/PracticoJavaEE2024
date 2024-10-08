package org.example.Dto.ServiciosExternos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
    private String text;
    private List<ClaimReview> claimReview;
}
