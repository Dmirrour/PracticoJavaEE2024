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
public class ClaimsResponse {
    private List<Claim> claims;
    private String nextPageToken;
}
