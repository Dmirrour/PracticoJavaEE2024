package org.example.Dto.ServiciosExternos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClaimReview {
    private Publisher publisher;
    private String url;
    private String title;
    private String reviewDate;
    private String textualRating;
    private String languageCode;
}
